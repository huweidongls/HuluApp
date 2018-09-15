package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.model.TelModel;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * Created by a on 2018/9/12.
 */

public class ActivityCallPhoneAdapter extends RecyclerView.Adapter<ActivityCallPhoneAdapter.ViewHolder> {

    private Context context;
    private List<TelModel.DataBean> data;
    private OnCallListener listener;

    public void setOnCallListener(OnCallListener listener){
        this.listener = listener;
    }

    public ActivityCallPhoneAdapter(List<TelModel.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_call_phone, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getTelName());
        holder.tvTelNum.setText(data.get(position).getTelNumber());
        holder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCall(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvTelNum;
        private ImageView ivCall;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTelNum = itemView.findViewById(R.id.tv_tel_num);
            ivCall = itemView.findViewById(R.id.iv_call);
        }
    }

    public interface OnCallListener{
        void onCall(int position);
    }

}
