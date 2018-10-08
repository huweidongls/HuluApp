package com.jingna.hulu.huluapp.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityLogInfoAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.widget.DoubleTimeSelectDialog;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInfoActivity extends BaseActivity {

    @BindView(R.id.activity_log_info_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_log_info_bumen)
    TextView tvBumen;
    @BindView(R.id.activity_log_info_bumen1)
    ImageView ivBumen1;
    @BindView(R.id.activity_log_info_bumen2)
    ImageView ivBumen2;
    @BindView(R.id.activity_log_info_time)
    TextView tvTime;
    @BindView(R.id.activity_log_info_time1)
    ImageView ivTime1;
    @BindView(R.id.activity_log_info_time2)
    ImageView ivTime2;

    private ActivityLogInfoAdapter adapter;
    private List<String> data;

    private DoubleTimeSelectDialog mDoubleTimeSelectDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_info);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(LogInfoActivity.this);

        initData();

    }

    private void initData() {

        data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        adapter = new ActivityLogInfoAdapter(data);
        LinearLayoutManager manager = new LinearLayoutManager(LogInfoActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.activity_log_info_rl_back, R.id.activity_log_info_rl_left, R.id.activity_log_info_rl_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_log_info_rl_back:
                finish();
                break;
            case R.id.activity_log_info_rl_left:
                tvBumen.setTextColor(Color.parseColor("#32CC0D"));
                ivBumen1.setImageResource(R.drawable.top_g);
                ivBumen2.setImageResource(R.drawable.bottom_g);
                tvTime.setTextColor(Color.parseColor("#333333"));
                ivTime1.setImageResource(R.drawable.top_b);
                ivTime2.setImageResource(R.drawable.bottom_b);
                break;
            case R.id.activity_log_info_rl_right:
                tvTime.setTextColor(Color.parseColor("#32CC0D"));
                ivTime1.setImageResource(R.drawable.top_g);
                ivTime2.setImageResource(R.drawable.bottom_g);
                tvBumen.setTextColor(Color.parseColor("#333333"));
                ivBumen1.setImageResource(R.drawable.top_b);
                ivBumen2.setImageResource(R.drawable.bottom_b);
                showCustomTimePicker();
                break;
        }
    }

    /**
     * 默认的周开始时间，格式如：yyyy-MM-dd
     **/
    public String defaultWeekBegin;
    /**
     * 默认的周结束时间，格式如：yyyy-MM-dd
     */
    public String defaultWeekEnd;

    public void showCustomTimePicker() {
        String beginDeadTime = "2017-01-01";
        if (mDoubleTimeSelectDialog == null) {
            mDoubleTimeSelectDialog = new DoubleTimeSelectDialog(this, beginDeadTime, defaultWeekBegin, defaultWeekEnd);
            mDoubleTimeSelectDialog.setOnDateSelectFinished(new DoubleTimeSelectDialog.OnDateSelectFinished() {
                @Override
                public void onSelectFinished(String startTime, String endTime) {
//                    ui_button1.setText(startTime.replace("-", ".") + "至\n" + endTime.replace("-", "."));
                    Log.e("123123", startTime.replace("-", ".") + "至\n" + endTime.replace("-", "."));
                }
            });

            mDoubleTimeSelectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                }
            });
        }
        if (!mDoubleTimeSelectDialog.isShowing()) {
            mDoubleTimeSelectDialog.recoverButtonState();
            mDoubleTimeSelectDialog.show();
        }
    }

}
