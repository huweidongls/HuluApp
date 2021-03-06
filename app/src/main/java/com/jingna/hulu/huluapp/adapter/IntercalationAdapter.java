package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.jingna.hulu.huluapp.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.List;

/**
 * Created by Administrator on 2018/7/26.
 */

public class IntercalationAdapter extends RecyclerView.Adapter<IntercalationAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    private static final int TYPE_ADD = 1;
    private static final int TYPE_PIC = 2;
    private static final int MAX_SIZE = 9;

    private OnAddImgListener addListener;
    private OnDeleteImgListener deleteListener;

    public void setDeleteListener(OnDeleteImgListener deleteListener){
        this.deleteListener = deleteListener;
    }

    public void setListener(OnAddImgListener addListener) {
        this.addListener = addListener;
    }

    public IntercalationAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_intercalation_pic, parent, false);
        ScreenAdapterTools.getInstance().loadView(view);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (data.size() >= MAX_SIZE) {
            //最多9张
            holder.rlAdd.setVisibility(View.GONE);
        } else {
            holder.rlImg.setVisibility(View.VISIBLE);
            holder.rlAdd.setVisibility(View.VISIBLE);
        }
        if (getItemViewType(position) == TYPE_ADD) {
            holder.rlImg.setVisibility(View.GONE);
        } else {
            holder.rlAdd.setVisibility(View.GONE);
            holder.rlImg.setVisibility(View.VISIBLE);
            Glide.with(context).load("file://" + data.get(position)).into(holder.iv);
        }
        holder.rlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addListener.onAddImg();
            }
        });
        holder.iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteListener.onDeleteImg(position);
                return true;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_ADD;
        } else {
            return TYPE_PIC;
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size() + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rlAdd;
        private RelativeLayout rlImg;
        private ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            rlAdd = itemView.findViewById(R.id.activity_create_intercalation_rv_rl_add);
            rlImg = itemView.findViewById(R.id.activity_create_intercalation_rv_rl_img);
            iv = itemView.findViewById(R.id.activity_create_intercalation_rv_iv_img);
        }
    }

    public interface OnAddImgListener {
        void onAddImg();
    }

    public interface OnDeleteImgListener{
        void onDeleteImg(int position);
    }

}
