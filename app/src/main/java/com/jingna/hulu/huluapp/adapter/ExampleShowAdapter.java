package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.activity.EventsReportedActivity;
import com.jingna.hulu.huluapp.manager.MediaManager;
import com.jingna.hulu.huluapp.utils.CommonsUtils;
import com.jingna.hulu.huluapp.utils.Constant;
import com.jingna.hulu.huluapp.utils.Record;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

public class ExampleShowAdapter extends RecyclerView.Adapter<ExampleShowAdapter.ViewHolder> {
    private List<String> mRecords;
    private Context mContext;
    private List<AnimationDrawable> mAnimationDrawables;
    private int pos = -1;//标记当前录音索引，默认没有播放任何一个

    public ExampleShowAdapter(Context context, List<String> records) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_example_activity1, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String path = Constant.BASE_URL+mRecords.get(position);
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
                //只要点击就设置为已播放状态（隐藏小红点）
//                record.setPlayed(true);
//                notifyDataSetChanged();
                //这里更新数据库小红点。这里不知道为什么可以强转建议复习复习基础~
//                ((EventsReportedActivity) mContext).getMgr().updateRecord(record);


                final AnimationDrawable animationDrawable = (AnimationDrawable) ieaLlSinger.getBackground();
                //重置动画
                resetAnim(animationDrawable);
                animationDrawable.start();

                //处理点击正在播放的语音时，可以停止；再次点击时重新播放。
//                if (pos == position) {
//                    if (record.isPlaying()) {
//                        record.setPlaying(false);
//                        MediaManager.release();
//                        animationDrawable.stop();
//                        animationDrawable.selectDrawable(0);//reset
//                        return;
//                    } else {
//                        record.setPlaying(true);
//                    }
//                }
//                //记录当前位置正在播放。
//                pos = position;
//                record.setPlaying(true);

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

        public ViewHolder(View itemView) {
            super(itemView);
//            ieaHeadImg = (ImageView) itemView.findViewById(R.id.iea_headImg);
            ieaIvVoiceLine = (ImageView) itemView.findViewById(R.id.iea_iv_voiceLine);
            ieaLlSinger = (LinearLayout) itemView.findViewById(R.id.iea_ll_singer);
            ieaTvVoicetime1 = (TextView) itemView.findViewById(R.id.iea_tv_voicetime1);
            ieaIvRed = (ImageView) itemView.findViewById(R.id.iea_iv_red);
            llContent = itemView.findViewById(R.id.ll_content);
        }
    }

}
