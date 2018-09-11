package com.jingna.hulu.huluapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(LoginActivity.this);

        initData();
    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userName", "aa");
        map.put("password", "bb");
        JSONObject json =new JSONObject(map);
        Gson gson = new Gson();
        String a = gson.toJson(map);

        ViseHttp.POST("SystemUser/login")
                .setJson(a)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        Log.e("222", errMsg);
                    }
                });

    }
}
