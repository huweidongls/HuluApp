package com.jingna.hulu.huluapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jingna.hulu.huluapp.R;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.OnClick;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

    }

    @OnClick({R.id.activity_news_content_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_news_content_rl_back:
                finish();
                break;
        }
    }

}
