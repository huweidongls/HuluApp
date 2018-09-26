package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_content);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventContentActivity.this);

    }

    @OnClick({R.id.activity_event_content_rl_back, R.id.activity_event_content_rl_append})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.activity_event_content_rl_back:
                finish();
                break;
            case R.id.activity_event_content_rl_append:
                intent.setClass(EventContentActivity.this, EventAppendActivity.class);
                startActivity(intent);
                break;
        }
    }

}
