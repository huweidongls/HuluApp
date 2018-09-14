package com.jingna.hulu.huluapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.activity.Main1Activity;
import com.jingna.hulu.huluapp.activity.Main2Activity;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

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
        String a = Map2Json.map2json(map);

//        ViseHttp.POST("SystemUser/login")
//                .setJson(a)
//                .request(new ACallback<String>() {
//                    @Override
//                    public void onSuccess(String data) {
//                        Log.e("222", data);
//                    }
//
//                    @Override
//                    public void onFail(int errCode, String errMsg) {
//                        Log.e("222", errMsg);
//                    }
//                });

    }

    @OnClick({R.id.activity_login_btn})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.activity_login_btn:
                intent.setClass(LoginActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
