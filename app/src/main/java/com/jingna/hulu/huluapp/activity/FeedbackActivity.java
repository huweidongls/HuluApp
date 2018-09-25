package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(FeedbackActivity.this);

    }

    @OnClick({R.id.activity_feedback_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_feedback_rl_back:
                finish();
                break;
        }
    }

}
