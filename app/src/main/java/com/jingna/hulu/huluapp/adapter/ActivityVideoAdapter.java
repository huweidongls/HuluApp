package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.video.Constents;
import com.jingna.hulu.huluapp.video.IMChattingHelper;
import com.jingna.hulu.huluapp.video.VedioActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.yuntongxun.ecsdk.ECMessage;
import com.yuntongxun.ecsdk.im.ECTextMessageBody;

import java.util.List;

/**
 * Created by a on 2018/9/12.
 */

public class ActivityVideoAdapter extends RecyclerView.Adapter<ActivityVideoAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public ActivityVideoAdapter(List<String> data) {
        this.data = data;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ivCallVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String callPhone = "123456";
                Intent intent = new Intent(context,VedioActivity.class);
                intent.putExtra("name","啦啦啦啦");
                intent.putExtra("id",callPhone);
                intent.putExtra(VedioActivity.EXTRA_OUTGOING_CALL,true);
                context.startActivity(intent);
                sendMsg();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivCallVideo;

        public ViewHolder(View itemView) {
            super(itemView);
            ivCallVideo = itemView.findViewById(R.id.call_video);
        }
    }

    private void sendMsg(){

        try {
            ECMessage msg = ECMessage.createECMessage(ECMessage.Type.TXT);
            msg.setTo("123456");
            ECTextMessageBody msgBody = new ECTextMessageBody(Constents.id + ",外卖");
            msg.setBody(msgBody);

            IMChattingHelper.sendECMessage(msg);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
