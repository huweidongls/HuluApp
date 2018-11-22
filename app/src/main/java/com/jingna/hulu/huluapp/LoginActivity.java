package com.jingna.hulu.huluapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.activity.ForgotPwdActivity;
import com.jingna.hulu.huluapp.activity.Main1Activity;
import com.jingna.hulu.huluapp.activity.Main2Activity;
import com.jingna.hulu.huluapp.model.GetOneModel;
import com.jingna.hulu.huluapp.model.LoginModel;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.jingna.hulu.huluapp.utils.Constant;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.vise.xsnow.cache.SpCache;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.activity_login_et_username)
    EditText etUsername;
    @BindView(R.id.activity_login_et_password)
    EditText etPassword;

    private SpImp spImp;
    private SpCache spCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(LoginActivity.this);
        spImp = new SpImp(LoginActivity.this);
        spCache = new SpCache(LoginActivity.this);

        initData();
    }

    private void initData() {

        Intent intent = new Intent();
        if(spImp.getUID() != 0){
            if(spImp.getUIDTYPE().equals("1")){
                intent.setClass(LoginActivity.this, Main1Activity.class);
                startActivity(intent);
                finish();
            }else if(spImp.getUIDTYPE().equals("2")){
                intent.setClass(LoginActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        }

    }

    @OnClick({R.id.activity_login_btn, R.id.tv_forget_password})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.activity_login_btn:
//                intent.setClass(LoginActivity.this, Main1Activity.class);
//                startActivity(intent);
//                finish();
                String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    ToastUtil.showShort(LoginActivity.this, "账号或密码不能为空");
                } else {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("userName", username);
                    map.put("password", password);
                    String json = Map2Json.map2json(map);
                    ViseHttp.POST("SystemUser/login")
                            .setJson(json)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.e("222", data);
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if (jsonObject.getString("status").equals("SUCCESS")) {
                                            Gson gson = new Gson();
                                            LoginModel model = gson.fromJson(data, LoginModel.class);
                                            spImp.setUID(model.getData().getUser().getId());
                                            spImp.setNAME(model.getData().getUser().getPeopleName());
                                            spImp.setPASSWORD(password);
                                            spCache.put("num4", model.getData().getUser().getNum4());
                                            spCache.put("avatar", model.getData().getUser().getUserPic());
                                            spCache.put("username", model.getData().getUser().getUserName());
                                            spCache.put(Constant.BUMEN, model.getData().getUser().getNum1());
                                            getOne(model.getData().getUser().getRoleId());
                                        }else {
                                            ToastUtil.showShort(LoginActivity.this, "登录失败");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {
                                    Log.e("222", errMsg);
                                }
                            });
                }
                break;
            case R.id.tv_forget_password:
                intent.setClass(LoginActivity.this, ForgotPwdActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 判断是领导还是员工
     */
    private void getOne(int roleId) {

        final Intent intent = new Intent();

        String url = Constant.BASE_URL + "SystemRole/getOne?userId=" + roleId;
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("222", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                GetOneModel model = gson.fromJson(data, GetOneModel.class);
                                List<GetOneModel.DataBean> list = model.getData().get(0);
                                for(int i = 0; i<list.size(); i++){
                                    if(list.get(i).getJurisdictionId() == 25){
                                        spImp.setUIDTYPE("2");
                                        intent.setClass(LoginActivity.this, Main2Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }else if(list.get(i).getJurisdictionId() == 26){
                                        spImp.setUIDTYPE("1");
                                        intent.setClass(LoginActivity.this, Main1Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
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

}
