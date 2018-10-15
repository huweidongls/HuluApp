package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.view.View;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsLogInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_log_info);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(DetailsLogInfoActivity.this);

    }

    @OnClick({R.id.activity_details_log_info_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_details_log_info_rl_back:
                finish();
                break;
        }
    }

}