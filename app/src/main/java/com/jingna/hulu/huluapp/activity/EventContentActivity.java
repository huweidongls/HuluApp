package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventContentActivity extends BaseActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_content);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventContentActivity.this);

        initData();

    }

    private void initData() {

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pageNum", 0);
        map.put("pageSize", 0);
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("id", id);
        map.put("eventExt", map1);
        String json = Map2Json.map2json(map);

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
