package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.activity.DetailsDangerActivity;
import com.jingna.hulu.huluapp.model.LineDangerModel;
import com.jingna.hulu.huluapp.utils.DateUtils;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvTitle.setText(data.get(position).getLpTitle());
        holder.tvDangerLocation.setText(data.get(position).getNum4());
        holder.tvDangerContent.setText(data.get(position).getLpContent());
        holder.tvDangerType.setText(data.get(position).getIsSolve() == 0 ? "未处理" : "已处理");
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
        private TextView tvDangerType;
        private TextView tvDangerTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDangerLocation = itemView.findViewById(R.id.tv_danger_location);
            tvDangerContent = itemView.findViewById(R.id.tv_danger_content);
            tvDangerType = itemView.findViewById(R.id.tv_danger_type);
            tvDangerTime = itemView.findViewById(R.id.tv_danger_time);
        }
    }

}
