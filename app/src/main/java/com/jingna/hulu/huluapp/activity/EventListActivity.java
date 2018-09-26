package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityEventListAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventListActivity extends BaseActivity {

    @BindView(R.id.activity_event_list_rv)
    RecyclerView recyclerView;

    private ActivityEventListAdapter adapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventListActivity.this);

        initData();

    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new ActivityEventListAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(EventListActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.activity_event_list_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_event_list_rl_back:
                finish();
                break;
        }
    }

}
