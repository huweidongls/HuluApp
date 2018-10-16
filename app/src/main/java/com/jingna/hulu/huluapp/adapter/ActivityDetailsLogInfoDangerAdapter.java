package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.activity.DetailsDangerActivity;
import com.jingna.hulu.huluapp.model.DetailsLogInfoModel;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * Created by Administrator on 2018/10/16.
 */

public class ActivityDetailsLogInfoDangerAdapter extends RecyclerView.Adapter<ActivityDetailsLogInfoDangerAdapter.ViewHolder> {

    private Context context;
    private List<DetailsLogInfoModel.DataBean.PlatformSolvesBean> data;

    public ActivityDetailsLogInfoDangerAdapter(Context context, List<DetailsLogInfoModel.DataBean.PlatformSolvesBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_loginfo_item, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv.setText(data.get(position).getLmTitle());
        holder.rl.setOnClickListener(new View.OnClickListener() {
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
        return data == null?0:data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        private RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            rl = itemView.findViewById(R.id.rl);
        }
    }

}
