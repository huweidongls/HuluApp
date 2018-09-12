package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.custom.GlideImageLoader;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main1Activity extends BaseActivity {

    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(Main1Activity.this);

        initBanner();

    }

    private void initBanner() {

        List<String> images = new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536678658773&di=8fa8eb91cc5db5c39f4e01b94114e2e4&imgtype=0&src=http%3A%2F%2Fwww.wallcoo.com%2Fnature%2FDigital_3D_HD_Wallpaper_1920x1200%2Fwallpapers%2F1920x1200%2Fdigital_3D_wallpaper_citidel2k81.jpg");
        images.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=418259163,3789341045&fm=26&gp=0.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536678658772&di=40a22fe55d78a265f57c29b77b8f0327&imgtype=0&src=http%3A%2F%2Fimage.17173.com%2Fbbs%2Fv1%2F2010%2F03%2F24%2F1269416139181.jpg");
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

    @OnClick({R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.iv1:
                intent.setClass(Main1Activity.this, MyTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.iv2:
                intent.setClass(Main1Activity.this, LineDangerActivity.class);
                startActivity(intent);
                break;
            case R.id.iv3:
                intent.setClass(Main1Activity.this, EventsReportedActivity.class);
                startActivity(intent);
                break;
            case R.id.iv4:
                intent.setClass(Main1Activity.this, PersonActivity.class);
                startActivity(intent);
                break;
            case R.id.iv5:
                intent.setClass(Main1Activity.this, CallPhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.iv6:
                intent.setClass(Main1Activity.this, VideoActivity.class);
                startActivity(intent);
                break;
        }
    }

}
