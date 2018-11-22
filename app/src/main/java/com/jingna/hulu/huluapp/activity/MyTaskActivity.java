package com.jingna.hulu.huluapp.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
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
import com.jingna.hulu.huluapp.adapter.ActivityMyTaskAdapter;
import com.jingna.hulu.huluapp.app.MyApp;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.dialog.DialogCustom;
import com.jingna.hulu.huluapp.model.LocationListModel;
import com.jingna.hulu.huluapp.model.MyTaskModel;
import com.jingna.hulu.huluapp.service.UploadLocationService;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.jingna.hulu.huluapp.utils.PermissionHelper;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.vise.xsnow.cache.SpCache;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTaskActivity extends BaseActivity {

    @BindView(R.id.activity_my_task_rv)
    RecyclerView recyclerView;
    @BindView(R.id.mapview)
    MapView mapView;
    @BindView(R.id.ll_start)
    LinearLayout llStart;
    @BindView(R.id.ll_end)
    LinearLayout llEnd;
    @BindView(R.id.iv_deng)
    ImageView ivDeng;
    @BindView(R.id.tv_deng)
    TextView tvDeng;
    @BindView(R.id.iv_start)
    ImageView ivStart;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.iv_end)
    ImageView ivEnd;
    @BindView(R.id.tv_end)
    TextView tvEnd;

    private ActivityMyTaskAdapter adapter;
    private List<MyTaskModel.DataBean.PlatformSolvesBean> mList;

    private BaiduMap mBaiduMap;
    private BitmapDescriptor mCurrentMarker;
    private LocationManager lm;

    private PermissionHelper mHelper;
    private Polyline mPolyline;
    private Polyline mPolyline1;

    private SpImp spImp;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    private double latitude = 0.0;//纬度
    private double longitude = 0.0;//经度

    private int lmByid;

    private SpCache spCache;
    private String num4 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        spCache = new SpCache(MyTaskActivity.this);
        num4 = spCache.get("num4", "");
        ButterKnife.bind(MyTaskActivity.this);
        spImp = new SpImp(MyTaskActivity.this);
        mHelper = new PermissionHelper(this);
        mBaiduMap = mapView.getMap();

        initLocation();
        initData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        List<LatLng> list = MyApp.getInstance().getPoints();
        if(list.size()>1){
            //绘制折线
            OverlayOptions ooPolyline1 = new PolylineOptions().width(10)
                    .color(Color.parseColor("#38DA11")).points(list);
            mPolyline1 = (Polyline) mBaiduMap.addOverlay(ooPolyline1);
        }

        if(spImp.getDATAID() == 0){
            ivDeng.setImageResource(R.drawable.deng_gray);
            tvDeng.setText("未护路");
            ivStart.setImageResource(R.drawable.start);
            tvStart.setTextColor(Color.parseColor("#333333"));
            ivEnd.setImageResource(R.drawable.end_g);
            tvEnd.setTextColor(Color.parseColor("#979797"));
            llStart.setEnabled(true);
            llEnd.setEnabled(false);
        }else {
            ivDeng.setImageResource(R.drawable.deng_green);
            tvDeng.setText("护路中");
            ivStart.setImageResource(R.drawable.start_g);
            tvStart.setTextColor(Color.parseColor("#979797"));
            ivEnd.setImageResource(R.drawable.end);
            tvEnd.setTextColor(Color.parseColor("#333333"));
            llStart.setEnabled(false);
            llEnd.setEnabled(true);
        }

    }

    private void initLocation() {

        mHelper.requestPermissions("请授予[定位]，否则无法定位", new PermissionHelper.PermissionListener() {
            @Override
            public void doAfterGrand(String... permission) {
                // 开启定位图层
                mBaiduMap.setMyLocationEnabled(true);

                startLocate();
            }

            @Override
            public void doAfterDenied(String... permission) {
                ToastUtil.showShort(MyTaskActivity.this, "请授权,否则无法定位");
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);

    }

    private void initData() {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("num2", spImp.getUID());
        map.put("num4", num4);
        String json = Map2Json.map2json(map);

        ViseHttp.POST("/RoadprotectionLoggerApi/myTask")
                .setJson(json)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                MyTaskModel model = gson.fromJson(data, MyTaskModel.class);
                                mList = model.getData().getPlatformSolves();
                                LinearLayoutManager manager = new LinearLayoutManager(MyTaskActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                adapter = new ActivityMyTaskAdapter(mList);
                                recyclerView.setAdapter(adapter);

                                lmByid = model.getData().getPlatformLineManages().get(0).getId();

                                //构建Marker图标
                                BitmapDescriptor bitmap = BitmapDescriptorFactory
                                        .fromResource(R.drawable.danger);

                                List<OverlayOptions> options = new ArrayList<OverlayOptions>();
                                for (int i = 0; i<mList.size(); i++){
                                    String s = mList.get(i).getLpCoordinate().substring(1, mList.get(i).getLpCoordinate().length()-1);
                                    String[] ss = s.split(",");
//                                    Log.e("123123", Double.valueOf(ss[0])+"......"+Double.valueOf(ss[1]));
//                                    LatLng point1 = new LatLng(39.92235, 116.380338);
//                                    OverlayOptions option1 =  new MarkerOptions()
//                                            .position(point1)
//                                            .icon(bdA);
                                    options.add(new MarkerOptions().position(new LatLng(Double.valueOf(ss[1]), Double.valueOf(ss[0]))).icon(bitmap));
                                }

//                                //定义Maker坐标点
//                                LatLng point = new LatLng(45.745916, 126.668362);
//                                //构建MarkerOption，用于在地图上添加Marker
//                                OverlayOptions option = new MarkerOptions()
//                                        .position(point)
//                                        .icon(bitmap);
//                                //在地图上添加Marker，并显示
//                                mBaiduMap.addOverlay(option);
                                mBaiduMap.addOverlays(options);

                                JSONArray jsonArray = new JSONArray(model.getData().getPlatformLineManages().get(0).getLmContent());
                                List<LatLng> points = new ArrayList<LatLng>();
                                for(int a = 0; a<jsonArray.length(); a++){
                                    String s = jsonArray.get(a)+"";
                                    String ss = s.substring(1, s.length()-1);
                                    String[] sss = ss.split(",");
//                                    Log.e("123123", Double.valueOf(sss[0])+"......"+Double.valueOf(sss[1]));
                                    points.add(new LatLng(Double.valueOf(sss[1]), Double.valueOf(sss[0])));
                                }

                                //构建折线点坐标
//                                LatLng p1 = new LatLng(45.751199, 126.662757);
//                                LatLng p2 = new LatLng(45.742545, 126.676986);
//                                points.add(p1);
//                                points.add(p2);

                                //绘制折线
                                OverlayOptions ooPolyline = new PolylineOptions().width(10)
                                        .color(Color.parseColor("#0088FF")).points(points);
                                mPolyline = (Polyline) mBaiduMap.addOverlay(ooPolyline);

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

    @OnClick({R.id.activity_my_task_rl_back, R.id.ll_start, R.id.ll_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_my_task_rl_back:
                finish();
                break;
            case R.id.ll_start:
                DialogCustom dialogCustom = new DialogCustom(MyTaskActivity.this, "是否开始巡检", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {

                        Map<String, Object> map = new LinkedHashMap<>();
                        List<String> locaList1 = new ArrayList<>();
                        locaList1.add(longitude+","+latitude);
                        map.put("coordinate", locaList1+"");
                        map.put("num2", spImp.getUID());
                        map.put("createBy", spImp.getNAME());
                        map.put("lmBy", lmByid);
                        String json = Map2Json.map2json(map);
                        Log.e("121212", json);

                        MyApp.getInstance().setClear();
                        MyApp.getInstance().setPoints(new LatLng(latitude, longitude));

                        ViseHttp.POST("/RoadprotectionLoggerApi/statusHl")
                                .setJson(json)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        Log.e("121212", data);
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if(jsonObject.getString("status").equals("SUCCESS")){
                                                int dataid = jsonObject.optInt("data");
                                                spImp.setDATAID(dataid);
                                                Intent intent = new Intent(MyTaskActivity.this, UploadLocationService.class);
                                                startService(intent);
                                                ToastUtil.showShort(MyTaskActivity.this, "开始巡检");
                                                ivDeng.setImageResource(R.drawable.deng_green);
                                                tvDeng.setText("护路中");
                                                ivStart.setImageResource(R.drawable.start_g);
                                                tvStart.setTextColor(Color.parseColor("#979797"));
                                                ivEnd.setImageResource(R.drawable.end);
                                                tvEnd.setTextColor(Color.parseColor("#333333"));
                                                llStart.setEnabled(false);
                                                llEnd.setEnabled(true);
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
                });
                dialogCustom.show();
                break;
            case R.id.ll_end:
                DialogCustom dialogCustom1 = new DialogCustom(MyTaskActivity.this, "是否结束巡检", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {

                        ViseHttp.POST("/RoadprotectionLoggerApi/endHl")
                                .addParam("infoId", spImp.getDATAID()+"")
                                .addParam("coordinate", "["+longitude+","+latitude+"]")
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if(jsonObject.getString("status").equals("SUCCESS")){
                                                Intent intent = new Intent(MyTaskActivity.this, UploadLocationService.class);
                                                stopService(intent);
                                                ToastUtil.showShort(MyTaskActivity.this, "结束巡检");

                                                ViseHttp.POST("/RoadprotectionLoggerApi/getone")
                                                        .addParam("lmBy", spImp.getDATAID()+"")
                                                        .request(new ACallback<String>() {
                                                            @Override
                                                            public void onSuccess(String data) {
                                                                Log.e("123123", data);
                                                                try {
                                                                    JSONObject jsonObject1 = new JSONObject(data);
                                                                    if(jsonObject1.getString("status").equals("SUCCESS")){
                                                                        spImp.setDATAID(0);
                                                                        Gson gson = new Gson();
                                                                        LocationListModel model = gson.fromJson(data, LocationListModel.class);

                                                                        ivDeng.setImageResource(R.drawable.deng_gray);
                                                                        tvDeng.setText("未护路");
                                                                        ivStart.setImageResource(R.drawable.start);
                                                                        tvStart.setTextColor(Color.parseColor("#333333"));
                                                                        ivEnd.setImageResource(R.drawable.end_g);
                                                                        tvEnd.setTextColor(Color.parseColor("#979797"));
                                                                        llStart.setEnabled(true);
                                                                        llEnd.setEnabled(false);

                                                                        List<LatLng> points = new ArrayList<LatLng>();
                                                                        for (int i = 0; i<model.getData().getInfo().size(); i++){
                                                                            String s = model.getData().getInfo().get(i).getCoordinate();
                                                                            String ss = s.substring(1, s.length()-1);
                                                                            String[] sss = ss.split(",");
                                                                            points.add(new LatLng(Double.valueOf(sss[1]), Double.valueOf(sss[0])));
                                                                        }
                                                                        //绘制折线
                                                                        OverlayOptions ooPolyline1 = new PolylineOptions().width(10)
                                                                                .color(Color.parseColor("#38DA11")).points(points);
                                                                        mPolyline1 = (Polyline) mBaiduMap.addOverlay(ooPolyline1);
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
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {

                                    }
                                });

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
//        mLocationClient.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);
    }

    /**
     * 定位
     */
    private void startLocate() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 5000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        //开启定位
        mLocationClient.start();
    }

    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            // 构造定位数据
            MyLocationData locData = new MyLocationData.Builder()
//                        .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                        .direction(100)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();

            // 设置定位数据
            mBaiduMap.setMyLocationData(locData);

            // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
            mCurrentMarker = BitmapDescriptorFactory
                    .fromResource(R.drawable.location);
            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(18));
            mBaiduMap.setMyLocationConfiguration(config);

            Log.e("121212", "lat"+latitude+"long"+longitude);

        }
    }

}
