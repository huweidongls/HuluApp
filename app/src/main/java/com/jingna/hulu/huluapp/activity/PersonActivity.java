package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingna.hulu.huluapp.LoginActivity;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.dialog.DialogCustom;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity {

    @BindView(R.id.activity_person_iv_avatar)
    ImageView ivAvatar;

    private SpImp spImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(PersonActivity.this);
        spImp = new SpImp(PersonActivity.this);

        initData();

    }

    private void initData() {

        Glide.with(PersonActivity.this).load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=418259163,3789341045&fm=26&gp=0.jpg").into(ivAvatar);

    }

    @OnClick({R.id.activity_person_rl_back, R.id.activity_person_btn_out})
    public void onClick(View view){
        final Intent intent = new Intent();
        switch (view.getId()){
            case R.id.activity_person_rl_back:
                finish();
                break;
            case R.id.activity_person_btn_out:
                DialogCustom dialogCustom = new DialogCustom(PersonActivity.this, "是否退出登录", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        spImp.setUID(0);
                        spImp.setUIDTYPE("0");
                        spImp.setNAME("0");
                        spImp.setDATAID(0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setClass(PersonActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                dialogCustom.show();
                break;
        }
    }
}
