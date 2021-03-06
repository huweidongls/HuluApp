package com.jingna.hulu.huluapp.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.custom.GlideImageLoader;
import com.jingna.hulu.huluapp.dialog.DialogCustom1;
import com.jingna.hulu.huluapp.model.BannerModel;
import com.jingna.hulu.huluapp.model.VersionModel;
import com.jingna.hulu.huluapp.utils.Constant;
import com.jingna.hulu.huluapp.utils.PermissionHelper;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.jingna.hulu.huluapp.utils.VersionUtils;
import com.jingna.hulu.huluapp.video.Constents;
import com.jingna.hulu.huluapp.video.SDKCoreHelper;
import com.vise.xsnow.cache.SpCache;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity {

    private Context context = Main2Activity.this;

    @BindView(R.id.banner)
    Banner banner;

    private PermissionHelper mHelper;

    private int versionCode;
    private SpCache spCache;
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(Main2Activity.this);
        mHelper = new PermissionHelper(this);

        spCache = new SpCache(Main2Activity.this);
        username = spCache.get("username", "");
        Constents.id = username;
        SDKCoreHelper.init(context);

        checkVersion();
        initBanner();

    }

    private void checkVersion() {

        versionCode = VersionUtils.packageCode(Main2Activity.this);

        ViseHttp.POST("platformVersionApi/queryList")
                .setJson("{\n" +
                        "  \"pageNum\": 0,\n" +
                        "  \"pageSize\": 0,\n" +
                        "  \"platformVersionExt\": {\n" +
                        "    \"orderBy\":\"create_date desc\"\n" +
                        "  }\n" +
                        "}")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getString("status").equals("SUCCESS")) {
                                Gson gson = new Gson();
                                final VersionModel model = gson.fromJson(data, VersionModel.class);
                                int code = Integer.valueOf(model.getData().get(0).getNum1());
                                if (code > versionCode && model.getData().get(0).getIsForceupdate().equals("1")) {
                                    DialogCustom1 dialogCustom = new DialogCustom1(Main2Activity.this, "本版本为强制更新，更新后可正常使用", new DialogCustom1.OnYesListener() {
                                        @Override
                                        public void onYes() {
                                            Intent intent = new Intent();
                                            intent.setAction("android.intent.action.VIEW");
                                            //此处填写更新apk地址
                                            Uri apk_url = Uri.parse(model.getData().get(0).getVersionDownurl());
                                            intent.setData(apk_url);
                                            startActivity(intent);
                                        }
                                    });
                                    dialogCustom.setCanceledOnTouchOutside(false);
                                    dialogCustom.setCancelable(false);
                                    dialogCustom.show();
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

        final List<String> images = new ArrayList<>();
        ViseHttp.POST("/bannerApi/queryList")
                .setJson("{\n" +
                        "  \"pageNum\": 0,\n" +
                        "  \"pageSize\": 0,\n" +
                        "  \"platformBannerExt\": {\n" +
                        "  \t\"orderBy\":\"banner_weight desc\",\n" +
                        "    \"isShow\":1\n" +
                        "  }\n" +
                        "}")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                final BannerModel model = gson.fromJson(data, BannerModel.class);
                                for (int i = 0; i<model.getData().size(); i++){
                                    images.add(Constant.BASE_URL+model.getData().get(i).getNum1());
                                }
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
                                banner.setOnBannerListener(new OnBannerListener() {
                                    @Override
                                    public void OnBannerClick(int position) {
                                        Intent intent = new Intent();
                                        intent.putExtra("url", model.getData().get(position).getBannerUrl());
                                        intent.setClass(Main2Activity.this, BannerWebActivity.class);
                                        startActivity(intent);
                                    }
                                });
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

    @OnClick({R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
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
