package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.model.VideoListModel;
import com.jingna.hulu.huluapp.video.Constents;
import com.jingna.hulu.huluapp.video.IMChattingHelper;
import com.jingna.hulu.huluapp.video.VedioActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2018/9/12.
 */

public class ActivityVideoAdapter extends RecyclerView.Adapter<ActivityVideoAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<VideoListModel.DataBean> data;
    private List<VideoListModel.DataBean> mFilterList = new ArrayList<>();

    public ActivityVideoAdapter(List<VideoListModel.DataBean> data) {
        this.data = data;
        this.mFilterList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_activity_video, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvName.setText(mFilterList.get(position).getPeopleName()+"("+mFilterList.get(position).getUserName()+")");
        holder.tvBumen.setText(mFilterList.get(position).getDepartmentName());

        holder.ivCallVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String callPhone = mFilterList.get(position).getUserName();
                Constents.acceptId = mFilterList.get(position).getId();
                Intent intent = new Intent(context,VedioActivity.class);
                intent.putExtra("name",mFilterList.get(position).getPeopleName());
                intent.putExtra("id",callPhone);
                intent.putExtra(VedioActivity.EXTRA_OUTGOING_CALL,true);
                context.startActivity(intent);
                sendMsg(mFilterList.get(position).getUserName(), mFilterList.get(position).getPeopleName());
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
                    List<VideoListModel.DataBean> filteredList = new ArrayList<>();
//                    for (String str : mSourceList) {
//                        //这里根据需求，添加匹配规则
//                        if (str.contains(charString)) {
//                            filteredList.add(str.);
//                        }
//                    }
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getPeopleName().contains(charString)) {
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
                mFilterList = (List<VideoListModel.DataBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivCallVideo;
        private TextView tvName;
        private TextView tvBumen;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCallVideo = itemView.findViewById(R.id.call_video);
            tvName = itemView.findViewById(R.id.tv_name);
            tvBumen = itemView.findViewById(R.id.tv_bumen);
        }
    }

    private void sendMsg(String username, String peopleName){

        try {
            ECMessage msg = ECMessage.createECMessage(ECMessage.Type.TXT);
            msg.setTo(username);
            ECTextMessageBody msgBody = new ECTextMessageBody(username+","+peopleName);
            msg.setBody(msgBody);

            IMChattingHelper.sendECMessage(msg);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
