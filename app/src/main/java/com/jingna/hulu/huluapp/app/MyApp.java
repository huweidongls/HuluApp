package com.jingna.hulu.huluapp.app;

import android.app.Activity;
import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.jingna.hulu.huluapp.utils.Constant;
import com.jingna.hulu.huluapp.utils.FTPTimeCount;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/11 0011.
 */

public class MyApp extends Application {
    private List<Activity> mList = new LinkedList<Activity>();
    private static MyApp instance;

    // 修改密码获取验证码倒计时
    public static FTPTimeCount ftptimecount;

    List<LatLng> points = new ArrayList<LatLng>();

    public List<LatLng> getPoints() {
        return points;
    }

    public void setPoints(LatLng latLng) {
        points.add(latLng);
    }

    public void setClear() {
        points.clear();
    }

    public MyApp() {
    }
    public synchronized static MyApp getInstance() {
        if (null == instance) {
            instance = new MyApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        ScreenAdapterTools.init(this);
        ViseHttp.init(this);
        ViseHttp.CONFIG()
                //配置请求主机地址
                .baseUrl(Constant.BASE_URL);

        ftptimecount = new FTPTimeCount(60000, 1000);

//        IWXAPI mWxApi = WXAPIFactory.createWXAPI(this, ShareKey.WX_ID, true);
//        mWxApi.registerApp(ShareKey.WX_ID);

    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
