package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.activity.EventContentActivity;
import com.jingna.hulu.huluapp.model.BaiduCityModel;
import com.jingna.hulu.huluapp.model.EventListModel;
import com.jingna.hulu.huluapp.utils.DateUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2018/9/26.
 */

public class ActivityEventListAdapter extends RecyclerView.Adapter<ActivityEventListAdapter.ViewHolder> {

    private Context context;
    private List<EventListModel.DataBean> data;

    public ActivityEventListAdapter(List<EventListModel.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_event_list, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTitle.setText(data.get(position).getEventTitle());
        String a = data.get(position).getNum2();
        String s = a.substring(2, a.length()-2);
        String[] aaaa = s.split(",");
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
                                holder.tvLocation.setText(model.getResult().getFormatted_address());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });
        holder.tvTime.setText(DateUtils.stampToDateSecond1(data.get(position).getCreateDate()+""));

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id", data.get(position).getId());
                intent.setClass(context, EventContentActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvLocation;
        private TextView tvTime;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvTime = itemView.findViewById(R.id.tv_time);
            ll = itemView.findViewById(R.id.ll);
        }
    }

}
