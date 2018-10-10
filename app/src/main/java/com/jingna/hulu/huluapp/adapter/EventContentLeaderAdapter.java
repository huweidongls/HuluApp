package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.manager.MediaManager;
import com.jingna.hulu.huluapp.model.EventContentModel;
import com.jingna.hulu.huluapp.utils.CommonsUtils;
import com.jingna.hulu.huluapp.utils.Constant;
import com.jingna.hulu.huluapp.utils.DateUtils;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

public class EventContentLeaderAdapter extends RecyclerView.Adapter<EventContentLeaderAdapter.ViewHolder> {
    private List<EventContentModel.DataBean> mRecords;
    private Context mContext;
    private List<AnimationDrawable> mAnimationDrawables;
    private int pos = -1;//标记当前录音索引，默认没有播放任何一个

    public EventContentLeaderAdapter(Context context, List<EventContentModel.DataBean> records) {
        this.mContext = context;
        this.mRecords = records;
        mAnimationDrawables = new ArrayList<>();
    }

//    private void resetData() {
//        for (Record record : mRecords) {
//            record.setPlaying(false);//保证在第二次点击该语音栏时当作没有“不是在播放状态”。
//        }
//    }

    private void resetAnim(AnimationDrawable animationDrawable) {
        if (!mAnimationDrawables.contains(animationDrawable)) {
            mAnimationDrawables.add(animationDrawable);
        }
        for (AnimationDrawable ad : mAnimationDrawables) {
            ad.selectDrawable(0);
            ad.stop();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example_activity2, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(!TextUtils.isEmpty(mRecords.get(position).getEventContent())){
            holder.tvContent.setVisibility(View.VISIBLE);
            holder.tvContent.setText(mRecords.get(position).getEventContent());
        }
        holder.tvTime.setText(DateUtils.stampToDateSecond(mRecords.get(position).getCreateDate()+""));
        holder.tvLeaderName.setText(mRecords.get(position).getLeaderName()+": ");
        if(!TextUtils.isEmpty(mRecords.get(position).getEventRecordings())){
            holder.llContent.setVisibility(View.VISIBLE);
            final String path = Constant.BASE_URL+mRecords.get(position).getEventRecordings();
            int second = MediaManager.getSecond(path);
            Log.e("123123", second+"秒");
            //设置显示时长
            holder.ieaTvVoicetime1.setText(second <= 0 ? 1 + "''" : second + "''");
//        if (!record.isPlayed()) {
//            holder.ieaIvRed.setVisibility(View.VISIBLE);
//        } else {
//            holder.ieaIvRed.setVisibility(View.GONE);
//        }
            //更改并显示录音条长度
            RelativeLayout.LayoutParams ps = (RelativeLayout.LayoutParams) holder.ieaIvVoiceLine.getLayoutParams();
            ps.width = CommonsUtils.getVoiceLineWight(mContext, second);
            holder.ieaIvVoiceLine.setLayoutParams(ps); //更改语音长条长度

            //开始设置监听
            final LinearLayout ieaLlSinger = holder.ieaLlSinger;
            holder.ieaIvVoiceLine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AnimationDrawable animationDrawable = (AnimationDrawable) ieaLlSinger.getBackground();
                    //重置动画
                    resetAnim(animationDrawable);
                    animationDrawable.start();

                    //播放前重置。
                    MediaManager.release();
                    //开始实质播放
                    MediaManager.playSound(path,
                            new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    animationDrawable.selectDrawable(0);//显示动画第一帧
                                    animationDrawable.stop();

                                    //播放完毕，当前播放索引置为-1。
                                    pos = -1;
                                }
                            });
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ieaHeadImg;
        ImageView ieaIvVoiceLine;
        LinearLayout ieaLlSinger;
        TextView ieaTvVoicetime1;
        ImageView ieaIvRed;
        LinearLayout llContent;
        TextView tvContent;
        TextView tvTime;
        TextView tvLeaderName;

        public ViewHolder(View itemView) {
            super(itemView);
//            ieaHeadImg = (ImageView) itemView.findViewById(R.id.iea_headImg);
            ieaIvVoiceLine = (ImageView) itemView.findViewById(R.id.iea_iv_voiceLine);
            ieaLlSinger = (LinearLayout) itemView.findViewById(R.id.iea_ll_singer);
            ieaTvVoicetime1 = (TextView) itemView.findViewById(R.id.iea_tv_voicetime1);
            ieaIvRed = (ImageView) itemView.findViewById(R.id.iea_iv_red);
            llContent = itemView.findViewById(R.id.ll_content);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvLeaderName = itemView.findViewById(R.id.tv_leader_name);
        }
    }

}
