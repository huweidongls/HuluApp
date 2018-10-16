package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityDetailsLogInfoAdapter;
import com.jingna.hulu.huluapp.adapter.ActivityDetailsLogInfoDangerAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.model.DetailsLogInfoModel;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsLogInfoActivity extends BaseActivity {

    @BindView(R.id.rv_reporte)
    RecyclerView recyclerView;
    @BindView(R.id.rv_danger)
    RecyclerView recyclerView1;
    @BindView(R.id.mapview)
    MapView mapView;

    private ActivityDetailsLogInfoAdapter adapter;
    private ActivityDetailsLogInfoDangerAdapter adapter1;
    private List<DetailsLogInfoModel.DataBean.EventsBean> mData;
    private List<DetailsLogInfoModel.DataBean.PlatformSolvesBean> mData1;

    private BaiduMap mBaiduMap;
    private Polyline mPolyline;
    private Polyline mPolyline1;
    private BitmapDescriptor mCurrentMarker;

    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_log_info);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(DetailsLogInfoActivity.this);
        mBaiduMap = mapView.getMap();
        initData();

    }

    private void initData() {

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        ViseHttp.POST("RoadprotectionLoggerApi/getone")
                .addParam("lmBy", id+"")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("123123", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                DetailsLogInfoModel model = gson.fromJson(data, DetailsLogInfoModel.class);
                                //加载上报事件与隐患事件列表
                                mData = model.getData().getEvents();
                                mData1 = model.getData().getPlatformSolves();
                                LinearLayoutManager manager = new LinearLayoutManager(DetailsLogInfoActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.addItemDecoration(new DividerItemDecoration(DetailsLogInfoActivity.this, DividerItemDecoration.VERTICAL));
                                recyclerView.setLayoutManager(manager);
                                adapter = new ActivityDetailsLogInfoAdapter(DetailsLogInfoActivity.this, mData);
                                recyclerView.setAdapter(adapter);
                                LinearLayoutManager manager1 = new LinearLayoutManager(DetailsLogInfoActivity.this);
                                manager1.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView1.addItemDecoration(new DividerItemDecoration(DetailsLogInfoActivity.this, DividerItemDecoration.VERTICAL));
                                recyclerView1.setLayoutManager(manager1);
                                adapter1 = new ActivityDetailsLogInfoDangerAdapter(DetailsLogInfoActivity.this, mData1);
                                recyclerView1.setAdapter(adapter1);
                                //绘制地图
                                //绘制规定路线
                                JSONArray jsonArray = new JSONArray(model.getData().getEvents().get(0).getLmContent());
                                List<LatLng> points = new ArrayList<LatLng>();
                                for(int a = 0; a<jsonArray.length(); a++){
                                    String s = jsonArray.get(a)+"";
                                    String ss = s.substring(1, s.length()-1);
                                    String[] sss = ss.split(",");
//                                    Log.e("123123", Double.valueOf(sss[0])+"......"+Double.valueOf(sss[1]));
                                    points.add(new LatLng(Double.valueOf(sss[1]), Double.valueOf(sss[0])));
                                    if(a == 0){
                                        latitude = sss[1];
                                        longitude = sss[0];
                                    }
                                }

                                OverlayOptions ooPolyline = new PolylineOptions().width(10)
                                        .color(Color.parseColor("#0088FF")).points(points);
                                //绘制行走路线
                                List<LatLng> points1 = new ArrayList<>();
                                for (int i = 0; i<model.getData().getInfo().size(); i++){
                                    String l = model.getData().getInfo().get(i).getCoordinate().substring(1, model.getData().getInfo().get(i).getCoordinate().length()-1);
                                    String[] ss = l.split(",");
                                    points1.add(new LatLng(Double.valueOf(ss[1]), Double.valueOf(ss[0])));
                                    if(i == 0){
                                        latitude = ss[1];
                                        longitude = ss[0];
                                    }
                                }
                                OverlayOptions ooPolyline1 = new PolylineOptions().width(10)
                                        .color(Color.parseColor("#38DA11")).points(points);
                                List<OverlayOptions> oo = new ArrayList<>();
                                oo.add(ooPolyline);
                                oo.add(ooPolyline1);
                                mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);
                                //绘制行走路线
//                                List<LatLng> points1 = new ArrayList<>();
//                                for (int i = 0; i<model.getData().getInfo().size(); i++){
//                                    String l = model.getData().getInfo().get(i).getCoordinate().substring(1, model.getData().getInfo().get(i).getCoordinate().length()-1);
//                                    String[] ss = l.split(",");
//                                    points1.add(new LatLng(Double.valueOf(ss[1]), Double.valueOf(ss[0])));
//                                    if(i == 0){
//                                        latitude = ss[1];
//                                        longitude = ss[0];
//                                    }
//                                }
//                                OverlayOptions ooPolyline1 = new PolylineOptions().width(10)
//                                        .color(Color.parseColor("#38DA11")).points(points);
//                                mPolyline1 = (Polyline) mBaiduMap1.addOverlay(ooPolyline1);

                                //绘制线路隐患
                                //构建Marker图标
                                BitmapDescriptor bitmap = BitmapDescriptorFactory
                                        .fromResource(R.drawable.danger);

                                List<OverlayOptions> options = new ArrayList<OverlayOptions>();
                                for (int i = 0; i<mData1.size(); i++){
                                    String s = mData1.get(i).getLpCoordinate().substring(1, mData1.get(i).getLpCoordinate().length()-1);
                                    String[] ss = s.split(",");
//                                    Log.e("123123", Double.valueOf(ss[0])+"......"+Double.valueOf(ss[1]));
//                                    LatLng point1 = new LatLng(39.92235, 116.380338);
//                                    OverlayOptions option1 =  new MarkerOptions()
//                                            .position(point1)
//                                            .icon(bdA);
                                    options.add(new MarkerOptions().position(new LatLng(Double.valueOf(ss[1]), Double.valueOf(ss[0]))).icon(bitmap));
                                }
                                mBaiduMap.addOverlays(options);

                                //定位到规定路线起点
                                //设定中心点坐标
                                LatLng cenpt =  new LatLng(Double.valueOf(latitude),Double.valueOf(longitude));
                                //定义地图状态
                                MapStatus mMapStatus = new MapStatus.Builder()
                                        //要移动的点
                                        .target(cenpt)
                                        //放大地图到20倍
                                        .zoom(13)
                                        .build();
                                //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
                                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                                //改变地图状态
                                mBaiduMap.setMapStatus(mMapStatusUpdate);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

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
