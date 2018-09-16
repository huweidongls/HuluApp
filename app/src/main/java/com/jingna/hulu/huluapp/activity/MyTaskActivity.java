package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityMyTaskAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.dialog.DialogCustom;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTaskActivity extends BaseActivity {

    @BindView(R.id.activity_my_task_rv)
    RecyclerView recyclerView;
    @BindView(R.id.mapview)
    MapView mapView;

    private ActivityMyTaskAdapter adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(MyTaskActivity.this);

        initLocation();
        initData();

    }

    private void initLocation() {

        // 开启定位图层
        mapView.setMyLocationEnabled(true);

// 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();

// 设置定位数据
        mapView.setMyLocationData(locData);

// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_geo);
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        mapView.setMyLocationConfiguration();

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
        LinearLayoutManager manager = new LinearLayoutManager(MyTaskActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new ActivityMyTaskAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.activity_my_task_rl_back, R.id.ll_start, R.id.ll_end})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_my_task_rl_back:
                finish();
                break;
            case R.id.ll_start:
                DialogCustom dialogCustom = new DialogCustom(MyTaskActivity.this, "是否开始巡检", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {

                    }
                });
                dialogCustom.show();
                break;
            case R.id.ll_end:
                DialogCustom dialogCustom1 = new DialogCustom(MyTaskActivity.this, "是否结束巡检", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {

                    }
                });
                dialogCustom1.show();
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
