package com.jingna.hulu.huluapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.model.BumenOneModel;

import java.util.List;

/**
 * Created by Administrator on 2018/10/12.
 */

public class ShowBumenAdapter extends BaseAdapter {

    private Context context;
    private List<BumenOneModel.DataBean> data;
    private LayoutInflater inflater;

    public ShowBumenAdapter(Context context, List<BumenOneModel.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_show_bumen, null);
            holder.tv = convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv.setText(data.get(position).getDepartmentName());

        return convertView;
    }

    class ViewHolder{
        TextView tv;
    }

}
