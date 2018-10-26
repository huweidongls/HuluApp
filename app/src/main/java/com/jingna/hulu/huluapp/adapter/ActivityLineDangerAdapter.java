package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.activity.DetailsDangerActivity;
import com.jingna.hulu.huluapp.model.BaiduCityModel;
import com.jingna.hulu.huluapp.model.LineDangerModel;
import com.jingna.hulu.huluapp.utils.DateUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by a on 2018/9/11.
 */

public class ActivityLineDangerAdapter extends RecyclerView.Adapter<ActivityLineDangerAdapter.ViewHolder> {

    private Context context;
    private List<LineDangerModel.DataBean> data;

    public ActivityLineDangerAdapter(List<LineDangerModel.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_line_danger, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvTitle.setText(data.get(position).getLpTitle());
        holder.tvDangerLocation.setText(data.get(position).getNum4());

        String a = data.get(position).getLpCoordinate();
        String aa = a.substring(1, a.length()-1);
        String[] aaaa = aa.split(",");
        String url = "http://api.map.baidu.com/geocoder?output=json&location=" + aaaa[1] + "," + aaaa[0] + "&key=ovbH9tDk74DcpRTv59n1zEOkRrmdSPf2";
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getString("status").equals("OK")) {
                                Gson gson = new Gson();
                                BaiduCityModel model = gson.fromJson(data, BaiduCityModel.class);
//                                                        holder.tvLocation.setText(model.getResult().getFormatted_address());
//                                tvLocation.setText(model.getResult().getFormatted_address());
                                holder.tvDangerLocation.setText(model.getResult().getFormatted_address());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

        holder.tvDangerContent.setText(data.get(position).getLpContent());
        holder.tvDangerTypename.setText(data.get(position).getTypeName());
        holder.tvDangerType.setText(data.get(position).getIsSolve() == 0 ? "未处理" : "已处理");
        if(data.get(position).getIsSolve() == 0){
            holder.tvDangerType.setTextColor(Color.parseColor("#FF0000"));
            holder.tvDangerType.setText("未处理");
        }else if(data.get(position).getIsSolve() == 1){
            holder.tvDangerType.setTextColor(Color.parseColor("#32CC0D"));
            holder.tvDangerType.setText("已处理");
        }
        holder.tvDangerTime.setText(DateUtils.stampToDateSecond(data.get(position).getCreateDate()+""));

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id", data.get(position).getId());
                intent.setClass(context, DetailsDangerActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout ll;
        private TextView tvTitle;
        private TextView tvDangerLocation;
        private TextView tvDangerContent;
        private TextView tvDangerTypename;
        private TextView tvDangerTime;
        private TextView tvDangerType;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDangerLocation = itemView.findViewById(R.id.tv_danger_location);
            tvDangerContent = itemView.findViewById(R.id.tv_danger_content);
            tvDangerTypename = itemView.findViewById(R.id.tv_danger_typename);
            tvDangerTime = itemView.findViewById(R.id.tv_danger_time);
            tvDangerType = itemView.findViewById(R.id.tv_danger_type);
        }
    }

}
