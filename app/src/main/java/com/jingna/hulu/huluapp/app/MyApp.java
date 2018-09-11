package com.jingna.hulu.huluapp.app;

import android.app.Activity;
import android.app.Application;

import com.vise.xsnow.http.ViseHttp;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/11 0011.
 */

public class MyApp extends Application {
    private List<Activity> mList = new LinkedList<Activity>();
    private static MyApp instance;

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
        ScreenAdapterTools.init(this);
        ViseHttp.init(this);
        ViseHttp.CONFIG()
                //配置请求主机地址
                .baseUrl("http://192.168.2.248:8080/");
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
