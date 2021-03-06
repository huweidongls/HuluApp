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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.activity.NewsContentActivity;
import com.jingna.hulu.huluapp.activity.NewsWebActivity;
import com.jingna.hulu.huluapp.model.NewsModel;
import com.jingna.hulu.huluapp.model.TelModel;
import com.jingna.hulu.huluapp.utils.Constant;
import com.jingna.hulu.huluapp.utils.DateUtils;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2018/9/12.
 */

public class ActivityNewsAdapter extends RecyclerView.Adapter<ActivityNewsAdapter.ViewHolder> implements Filterable {

    private Context context;
    private List<NewsModel.DataBean> data;

    private List<NewsModel.DataBean> mFilterList = new ArrayList<>();

    public ActivityNewsAdapter(List<NewsModel.DataBean> data) {
        this.data = data;
        this.mFilterList = data;
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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(mFilterList.get(position).getNewsTitle());
        Glide.with(context).load(Constant.BASE_URL + mFilterList.get(position).getNewsPic()).into(holder.ivTitle);
        holder.tvTime.setText(DateUtils.stampToDate(mFilterList.get(position).getCreateDate() + ""));
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsWebActivity.class);
                intent.putExtra("newid", mFilterList.get(position).getId());
                intent.putExtra("title", mFilterList.get(position).getNewsTitle());
                intent.putExtra("subtitle", mFilterList.get(position).getSubtitle());
                intent.putExtra("pic", mFilterList.get(position).getNewsPic());
                context.startActivity(intent);
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
                    List<NewsModel.DataBean> filteredList = new ArrayList<>();
//                    for (String str : mSourceList) {
//                        //这里根据需求，添加匹配规则
//                        if (str.contains(charString)) {
//                            filteredList.add(str.);
//                        }
//                    }
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getNewsTitle().contains(charString)) {
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
                mFilterList = (List<NewsModel.DataBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
