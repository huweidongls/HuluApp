package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.app.MyApp;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPwdActivity extends BaseActivity {

    @BindView(R.id.activity_forgot_pwd_tv_get_yzm)
    Button btnYzm;

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

    @OnClick({R.id.activity_forgot_pwd_rl_back, R.id.activity_forgot_pwd_tv_get_yzm})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_forgot_pwd_rl_back:
                finish();
                break;
            case R.id.activity_forgot_pwd_tv_get_yzm:
                MyApp.ftptimecount.start();
                break;
        }
    }

}
