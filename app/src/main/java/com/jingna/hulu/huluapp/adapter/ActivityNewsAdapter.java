package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.activity.NewsContentActivity;
import com.jingna.hulu.huluapp.model.NewsModel;
import com.jingna.hulu.huluapp.utils.DateUtils;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * Created by a on 2018/9/12.
 */

public class ActivityNewsAdapter extends RecyclerView.Adapter<ActivityNewsAdapter.ViewHolder> {

    private Context context;
    private List<NewsModel.DataBean> data;

    public ActivityNewsAdapter(List<NewsModel.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_news, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).getNewsTitle());
        Glide.with(context).load(data.get(position).getNewsPic()).into(holder.ivTitle);
        holder.tvTime.setText(DateUtils.stampToDate(data.get(position).getCreateDate()+""));
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsContentActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivTitle;
        private TextView tvTitle;
        private TextView tvTime;
        private RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTitle = itemView.findViewById(R.id.iv_title);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            rl = itemView.findViewById(R.id.rl);
        }
    }

}
