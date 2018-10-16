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

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.activity.DetailsLogInfoActivity;
import com.jingna.hulu.huluapp.model.LogInfoModel;
import com.jingna.hulu.huluapp.utils.DateUtils;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class ActivityLogInfoAdapter extends RecyclerView.Adapter<ActivityLogInfoAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<LogInfoModel.DataBean> data;
    private List<LogInfoModel.DataBean> mFilterList = new ArrayList<>();

    public ActivityLogInfoAdapter(List<LogInfoModel.DataBean> data) {
        this.data = data;
        this.mFilterList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_log_info, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvLineTitle.setText(mFilterList.get(position).getLmTitle());
        holder.tvName.setText("护路员: "+mFilterList.get(position).getCreateBy());
        holder.tvPhoneNum.setText("联系电话: "+mFilterList.get(position).getTelephome());
        holder.tvDangerNum.setText("隐患事件: "+mFilterList.get(position).getSolveNum()+"件");
        holder.tvReporteNum.setText("上报事件: "+mFilterList.get(position).getEventNum()+"件");
        holder.tvTime.setText(DateUtils.stampToDate(mFilterList.get(position).getCreateDate()+""));
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id", mFilterList.get(position).getId());
                intent.setClass(context, DetailsLogInfoActivity.class);
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
                    List<LogInfoModel.DataBean> filteredList = new ArrayList<>();
//                    for (String str : mSourceList) {
//                        //这里根据需求，添加匹配规则
//                        if (str.contains(charString)) {
//                            filteredList.add(str.);
//                        }
//                    }
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getLmTitle().contains(charString)) {
                            filteredList.add(data.get(i));
                        }
                        if (data.get(i).getCreateBy().contains(charString)) {
                            filteredList.add(data.get(i));
                        }
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
                mFilterList = (List<LogInfoModel.DataBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLineTitle;
        private TextView tvName;
        private TextView tvPhoneNum;
        private TextView tvDangerNum;
        private TextView tvReporteNum;
        private TextView tvTime;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            tvLineTitle = itemView.findViewById(R.id.tv_line_title);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhoneNum = itemView.findViewById(R.id.tv_phone_num);
            tvDangerNum = itemView.findViewById(R.id.tv_danger_num);
            tvReporteNum = itemView.findViewById(R.id.tv_reporte_num);
            tvTime = itemView.findViewById(R.id.tv_time);
            ll = itemView.findViewById(R.id.ll);
        }
    }

}
