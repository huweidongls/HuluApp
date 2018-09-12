package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityCallPhoneAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CallPhoneActivity extends BaseActivity {

    @BindView(R.id.activity_call_phone_rv)
    RecyclerView recyclerView;

    private ActivityCallPhoneAdapter adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(CallPhoneActivity.this);

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
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        LinearLayoutManager manager = new LinearLayoutManager(CallPhoneActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(CallPhoneActivity.this, DividerItemDecoration.VERTICAL));
        adapter = new ActivityCallPhoneAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.activity_call_phone_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_call_phone_rl_back:
                finish();
                break;
        }
    }

}
