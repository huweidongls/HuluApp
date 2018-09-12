package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityLineDangerAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LineDangerActivity extends BaseActivity {

    @BindView(R.id.activity_line_danger_rv)
    RecyclerView recyclerView;

    private ActivityLineDangerAdapter adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_danger);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(LineDangerActivity.this);

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
        adapter = new ActivityLineDangerAdapter(data);
        LinearLayoutManager manager = new LinearLayoutManager(LineDangerActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.activity_line_danger_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_line_danger_rl_back:
                finish();
                break;
        }
    }

}
