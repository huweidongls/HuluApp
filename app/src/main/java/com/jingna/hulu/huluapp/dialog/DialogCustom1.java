package com.jingna.hulu.huluapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

/**
 * Created by Administrator on 2018/9/15.
 */

public class DialogCustom1 extends Dialog {

    private Context context;

    private TextView tvTitle;
    private TextView tvNo;
    private TextView tvYes;

    private String title;

    private OnYesListener listener;

    public DialogCustom1(@NonNull Context context, String title, OnYesListener listener) {
        super(context);
        this.context = context;
        this.title = title;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom, null);
        setContentView(view);
        ScreenAdapterTools.getInstance().loadView(view);

        tvTitle = view.findViewById(R.id.tv_title);
        tvNo = view.findViewById(R.id.tv_no);
        tvYes = view.findViewById(R.id.tv_yes);

        tvTitle.setText(title);
        tvNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        tvYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onYes();
            }
        });

    }

    public interface OnYesListener{
        void onYes();
    }

}
