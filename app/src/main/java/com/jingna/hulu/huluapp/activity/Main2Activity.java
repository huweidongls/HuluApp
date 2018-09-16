package com.jingna.hulu.huluapp.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.custom.GlideImageLoader;
import com.jingna.hulu.huluapp.utils.PermissionHelper;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity {

    @BindView(R.id.banner)
    Banner banner;

    private PermissionHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(Main2Activity.this);
        mHelper = new PermissionHelper(this);

        initBanner();

    }

    private void initBanner() {

        mHelper.requestPermissions("请授予权限", new PermissionHelper.PermissionListener() {
                    @Override
                    public void doAfterGrand(String... permission) {

                    }

                    @Override
                    public void doAfterDenied(String... permission) {
                        ToastUtil.showShort(Main2Activity.this, "请授权");
                    }
                }, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA);

        List<String> images = new ArrayList<>();
        images.add("http://47.92.127.1:8088/upload/jn_1.jpg");
        images.add("http://47.92.127.1:8088/upload/jn_2.jpg");
        images.add("http://47.92.127.1:8088/upload/jn_3.jpg");
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    @OnClick({R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.iv1:
                intent.setClass(Main2Activity.this, EventLeaderActivity.class);
                startActivity(intent);
                break;
            case R.id.iv2:
                intent.setClass(Main2Activity.this, VideoActivity.class);
                startActivity(intent);
                break;
            case R.id.iv3:
                intent.setClass(Main2Activity.this, NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.iv4:
                intent.setClass(Main2Activity.this, LogInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.iv5:
                intent.setClass(Main2Activity.this, PersonActivity.class);
                startActivity(intent);
                break;
        }
    }

}
