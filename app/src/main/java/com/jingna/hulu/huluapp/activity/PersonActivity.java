package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.hulu.huluapp.LoginActivity;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.dialog.DialogCustom;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.jingna.hulu.huluapp.utils.Constant;
import com.vise.xsnow.cache.SpCache;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity {

    @BindView(R.id.activity_person_iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.activity_person_tv_username)
    TextView tvUsername;
    @BindView(R.id.activity_person_rl_event_list)
    RelativeLayout rlEventList;

    private SpImp spImp;
    private SpCache spCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(PersonActivity.this);
        spImp = new SpImp(PersonActivity.this);
        spCache = new SpCache(PersonActivity.this);

        initData();

    }

    private void initData() {

        if(spImp.getUID() != 0){
            if(spImp.getUIDTYPE().equals("1")){
                rlEventList.setVisibility(View.VISIBLE);
            }else if(spImp.getUIDTYPE().equals("2")){
                rlEventList.setVisibility(View.GONE);
            }
        }

        String userPic = Constant.BASE_URL+spCache.get("avatar", "0");
        Glide.with(PersonActivity.this).load(userPic).into(ivAvatar);
        tvUsername.setText(spCache.get("username", ""));

    }

    @OnClick({R.id.activity_person_rl_back, R.id.activity_person_btn_out, R.id.activity_person_rl_feedback, R.id.activity_person_rl_about,
    R.id.activity_person_rl_change_pwd, R.id.activity_person_rl_event_list})
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
                        spImp.setPASSWORD("");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setClass(PersonActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                dialogCustom.show();
                break;
            case R.id.activity_person_rl_feedback:
                //意见反馈
                intent.setClass(PersonActivity.this, FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_person_rl_about:
                intent.setClass(PersonActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_person_rl_change_pwd:
                intent.setClass(PersonActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_person_rl_event_list:
                intent.setClass(PersonActivity.this, EventListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
