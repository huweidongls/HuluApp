package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
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
 * Created by Administrator on 2018/9/14.
 */

public class ActivityEventLeaderAdapter extends RecyclerView.Adapter<ActivityEventLeaderAdapter.ViewHolder> {

    private Context context;
    private List<EventListModel.DataBean> data;

    public ActivityEventLeaderAdapter(List<EventListModel.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_event_leader, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).getEventTitle());
        holder.tvLmTitle.setText("巡检线路: "+data.get(position).getLmTitle());
        holder.tvName.setText("护路员: "+data.get(position).getPeopleName());
        holder.tvPhoneNum.setText("联系电话: "+data.get(position).getPhonenum());
        holder.tvTime.setText(DateUtils.stampToDateSecond1(data.get(position).getCreateDate()+""));
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
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvLmTitle;
        private TextView tvName;
        private TextView tvPhoneNum;
        private TextView tvLocation;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLmTitle = itemView.findViewById(R.id.tv_lmtitle);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhoneNum = itemView.findViewById(R.id.tv_phone_num);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
