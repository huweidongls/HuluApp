package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.activity.EventContentActivity;
import com.jingna.hulu.huluapp.model.BaiduCityModel;
import com.jingna.hulu.huluapp.model.EventListModel;
import com.jingna.hulu.huluapp.model.NewsModel;
import com.jingna.hulu.huluapp.utils.DateUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/14.
 */

public class ActivityEventLeaderAdapter extends RecyclerView.Adapter<ActivityEventLeaderAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<EventListModel.DataBean> data;

    private List<EventListModel.DataBean> mFilterList = new ArrayList<>();

    public ActivityEventLeaderAdapter(List<EventListModel.DataBean> data) {
        this.data = data;
        this.mFilterList = data;
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTitle.setText(mFilterList.get(position).getEventTitle());
        holder.tvLmTitle.setText("巡检线路: "+mFilterList.get(position).getLmTitle());
        holder.tvName.setText("护路员: "+mFilterList.get(position).getPeopleName());
        holder.tvPhoneNum.setText("联系电话: "+mFilterList.get(position).getPhonenum());
        holder.tvTime.setText(DateUtils.stampToDateSecond1(mFilterList.get(position).getCreateDate()+""));
        String a = mFilterList.get(position).getNum2();
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

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id", mFilterList.get(position).getId());
                intent.setClass(context, EventContentActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFilterList == null ? 0 : mFilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            //执行过滤操作
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    //没有过滤的内容，则使用源数据
                    mFilterList = data;
                } else {
                    List<EventListModel.DataBean> filteredList = new ArrayList<>();
//                    for (String str : mSourceList) {
//                        //这里根据需求，添加匹配规则
//                        if (str.contains(charString)) {
//                            filteredList.add(str.);
//                        }
//                    }
                    for (int i = 0; i<data.size(); i++){
                        if(data.get(i).getLmTitle().contains(charString)){
                            filteredList.add(data.get(i));
                        }
//                        if(data.get(i).getPeopleName().contains(charString)){
//                            filteredList.add(data.get(i));
//                        }
                    }

                    mFilterList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilterList;
                return filterResults;
            }
            //把过滤后的值返回出来
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilterList = (List<EventListModel.DataBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvLmTitle;
        private TextView tvName;
        private TextView tvPhoneNum;
        private TextView tvLocation;
        private TextView tvTime;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvLmTitle = itemView.findViewById(R.id.tv_lmtitle);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhoneNum = itemView.findViewById(R.id.tv_phone_num);
            tvLocation = itemView.findViewById(R.id.tv_location);
            tvTime = itemView.findViewById(R.id.tv_time);
            ll = itemView.findViewById(R.id.ll);
        }
    }

}
