package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.model.LogInfoModel;
import com.jingna.hulu.huluapp.utils.DateUtils;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class ActivityLogInfoAdapter extends RecyclerView.Adapter<ActivityLogInfoAdapter.ViewHolder> {

    private Context context;
    private List<LogInfoModel.DataBean> data;

    public ActivityLogInfoAdapter(List<LogInfoModel.DataBean> data) {
        this.data = data;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvLineTitle.setText(data.get(position).getLmTitle());
        holder.tvName.setText("护路员: "+data.get(position).getCreateBy());
        holder.tvPhoneNum.setText("联系电话: "+data.get(position).getTelephome());
        holder.tvDangerNum.setText("隐患事件: "+data.get(position).getSolveNum()+"件");
        holder.tvReporteNum.setText("上报事件: "+data.get(position).getEventNum()+"件");
        holder.tvTime.setText(DateUtils.stampToDate(data.get(position).getCreateDate()+""));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLineTitle;
        private TextView tvName;
        private TextView tvPhoneNum;
        private TextView tvDangerNum;
        private TextView tvReporteNum;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvLineTitle = itemView.findViewById(R.id.tv_line_title);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhoneNum = itemView.findViewById(R.id.tv_phone_num);
            tvDangerNum = itemView.findViewById(R.id.tv_danger_num);
            tvReporteNum = itemView.findViewById(R.id.tv_reporte_num);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
