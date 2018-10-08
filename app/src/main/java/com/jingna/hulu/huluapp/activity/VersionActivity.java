package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.dialog.DialogCustom;
import com.jingna.hulu.huluapp.model.VersionModel;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.jingna.hulu.huluapp.utils.VersionUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VersionActivity extends BaseActivity {

    @BindView(R.id.activity_version_tv_current_version)
    TextView tvVersion;

    private String currentVersion;
    private int versionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(VersionActivity.this);

        initData();

    }

    private void initData() {

        versionCode = VersionUtils.packageCode(VersionActivity.this);
        currentVersion = VersionUtils.packageName(VersionActivity.this);
        tvVersion.setText(currentVersion);

    }

    @OnClick({R.id.activity_version_rl_back, R.id.activity_version_btn_check_version})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_version_rl_back:
                finish();
                break;
            case R.id.activity_version_btn_check_version:
                checkVersion();
                break;
        }
    }

    private void checkVersion() {

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
                                if (code > versionCode) {
                                    DialogCustom dialogCustom = new DialogCustom(VersionActivity.this, "已有新版本，是否去下载", new DialogCustom.OnYesListener() {
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
                                    dialogCustom.show();
                                }else if(code == versionCode){
                                    ToastUtil.showShort(VersionActivity.this, "当前为最新版本");
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
