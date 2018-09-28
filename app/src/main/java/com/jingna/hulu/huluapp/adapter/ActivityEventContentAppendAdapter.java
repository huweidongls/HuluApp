package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.model.EventContentModel;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/27.
 */

public class ActivityEventContentAppendAdapter extends RecyclerView.Adapter<ActivityEventContentAppendAdapter.ViewHolder> {

    private Context context;
    private List<EventContentModel.DataBean> data;

    public ActivityEventContentAppendAdapter(List<EventContentModel.DataBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_event_content_append, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvContent.setText(data.get(position).getEventContent());
        LinearLayoutManager manager = new LinearLayoutManager(context){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.rv.setLayoutManager(manager);
        List<String> picList = new ArrayList<>();
        if(!TextUtils.isEmpty(data.get(position).getEventPic())){
            String[] show = data.get(position).getEventPic().split(",");
            for (int i = 0; i<show.length; i++){
                picList.add(show[i]);
            }
            GridLayoutManager manager1 = new GridLayoutManager(context, 3);
            holder.rv.setLayoutManager(manager1);
            ActivityDetailsDangerShowAdapter showAdapter = new ActivityDetailsDangerShowAdapter(picList);
            holder.rv.setAdapter(showAdapter);
        }

        List<String> records = new ArrayList<>();
        if(!TextUtils.isEmpty(data.get(position).getEventRecordings())){
            String[] record = data.get(position).getEventRecordings().split(",");
            for (int i = 0; i<record.length; i++){
                records.add(record[i]);
            }
            LinearLayoutManager managerRecord = new LinearLayoutManager(context){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            holder.rvRecord.setLayoutManager(managerRecord);
            ExampleShowAdapter exampleShowAdapter = new ExampleShowAdapter(context, records);
            holder.rvRecord.setAdapter(exampleShowAdapter);
        }

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvContent;
        private RecyclerView rv;
        private RecyclerView rvRecord;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            rv = itemView.findViewById(R.id.rv_pic);
            rvRecord = itemView.findViewById(R.id.em_lv_recodeList);
        }
    }

}
