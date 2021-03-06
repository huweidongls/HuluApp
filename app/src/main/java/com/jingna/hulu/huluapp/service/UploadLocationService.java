package com.jingna.hulu.huluapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.jingna.hulu.huluapp.app.MyApp;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/9/17.
 */

public class UploadLocationService extends Service {

    private Timer timer;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    private SpImp spImp;

    private double latitude = 0.0;//纬度
    private double longitude = 0.0;//经度

    @Override
    public void onCreate() {
        super.onCreate();

        spImp = new SpImp(this);
        startLocate();
        uploadLocation();

    }

    private void uploadLocation() {

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                MyApp.getInstance().setPoints(new LatLng(latitude, longitude));

                Map<String, Object> map = new LinkedHashMap<>();
                List<String> locaList1 = new ArrayList<>();
                locaList1.add(longitude + "," + latitude);
                map.put("coordinate", locaList1 + "");
                map.put("rploggerId", spImp.getDATAID());
                String json = Map2Json.map2json(map);

                ViseHttp.POST("/RoadprotectionLoggerApi/realTime")
                        .setJson(json)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.getString("status").equals("SUCCESS")) {
                                        Log.e("123123", "上报成功");
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
        }, 20000, 100000);

        startLocate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        timer.cancel();
        mLocationClient.stop();
        MyApp.getInstance().setClear();
        timer.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
        int span = 10000;
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
            Log.e("123123", "lat" + location.getLatitude() + "long" + location.getLongitude());

        }
    }

}
