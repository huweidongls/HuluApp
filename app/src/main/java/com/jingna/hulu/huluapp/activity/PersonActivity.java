package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity {

    @BindView(R.id.activity_person_iv_avatar)
    ImageView ivAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(PersonActivity.this);

        initData();

    }

    private void initData() {

        Glide.with(PersonActivity.this).load("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=418259163,3789341045&fm=26&gp=0.jpg").into(ivAvatar);

    }

    @OnClick({R.id.activity_person_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_person_rl_back:
                finish();
                break;
        }
    }

}
