package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.app.MyApp;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.utils.Constant;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPwdActivity extends BaseActivity {

    @BindView(R.id.activity_forgot_pwd_tv_get_yzm)
    Button btnYzm;
    @BindView(R.id.activity_forgot_pwd_et_username)
    EditText etUsername;
    @BindView(R.id.activity_forgot_pwd_et_code)
    EditText etCode;
    @BindView(R.id.activity_forgot_pwd_et_pwd)
    EditText etPwd;
    @BindView(R.id.activity_forgot_pwd_et_pwd_sure)
    EditText etPwdSure;

    public Button getCode_btn() {
        return btnYzm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        MyApp.ftptimecount.setActivity(ForgotPwdActivity.this);
        ButterKnife.bind(ForgotPwdActivity.this);

    }

    @OnClick({R.id.activity_forgot_pwd_rl_back, R.id.activity_forgot_pwd_tv_get_yzm, R.id.activity_forgot_pwd_btn_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_forgot_pwd_rl_back:
                finish();
                break;
            case R.id.activity_forgot_pwd_tv_get_yzm:
                if (TextUtils.isEmpty(etUsername.getText().toString())) {
                    ToastUtil.showShort(ForgotPwdActivity.this, "职工号不能为空");
                } else {
                    getCode();
                }
                break;
            case R.id.activity_forgot_pwd_btn_complete:
                onComplete();
                break;
        }
    }

    private void onComplete() {

        String username = etUsername.getText().toString();
        String code = etCode.getText().toString();
        String pwd = etPwd.getText().toString();
        String pwdSure = etPwdSure.getText().toString();
        if (!TextUtils.isEmpty(username)) {
            if (!TextUtils.isEmpty(code)) {
                if (!TextUtils.isEmpty(pwd)) {
                    if (pwd.equals(pwdSure)) {

                        String url = Constant.BASE_URL + "SystemUser/updatePassword?username=" + username + "&password=" + pwd + "&code=" + code;
                        ViseHttp.GET(url)
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String data) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(data);
                                            if (jsonObject.getString("status").equals("SUCCESS")) {
                                                ToastUtil.showShort(ForgotPwdActivity.this, "密码修改成功");
                                                finish();
                                            } else {
                                                ToastUtil.showShort(ForgotPwdActivity.this, "密码修改失败");
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {
                                        ToastUtil.showShort(ForgotPwdActivity.this, "密码修改失败");
                                    }
                                });

                    } else {
                        ToastUtil.showShort(ForgotPwdActivity.this, "密码不一致");
                    }
                } else {
                    ToastUtil.showShort(ForgotPwdActivity.this, "密码不能为空");
                }
            } else {
                ToastUtil.showShort(ForgotPwdActivity.this, "验证码不能为空");
            }
        } else {
            ToastUtil.showShort(ForgotPwdActivity.this, "职工号不能为空");
        }

    }

    private void getCode() {

        MyApp.ftptimecount.start();
        String url = Constant.BASE_URL + "SystemUser/resetPassword?userName=" + etUsername.getText().toString();
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getString("status").equals("SUCCESS") && jsonObject.getInt("data") == 1) {
                                ToastUtil.showShort(ForgotPwdActivity.this, "验证码获取成功");
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
