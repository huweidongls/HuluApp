package com.jingna.hulu.huluapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityCallPhoneAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.model.BaiduCityModel;
import com.jingna.hulu.huluapp.model.TelModel;
import com.jingna.hulu.huluapp.utils.PermissionHelper;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CallPhoneActivity extends BaseActivity {

    @BindView(R.id.activity_call_phone_rv)
    RecyclerView recyclerView;
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;

    private ActivityCallPhoneAdapter adapter;
    private List<TelModel.DataBean> mList;

    private PermissionHelper mHelper;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(CallPhoneActivity.this);
        mHelper = new PermissionHelper(this);

        initData();

    }

    private void initData() {

        ViseHttp.POST("/platformTel/queryList")
                .setJson("{\n" +
                        "  \"pageNum\": 0,\n" +
                        "  \"pageSize\": 0,\n" +
                        "  \"platformTelExt\": {\n" +
                        "    \n" +
                        "  }\n" +
                        "}")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("123123", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                TelModel model = gson.fromJson(data, TelModel.class);
                                mList = model.getData();
                                LinearLayoutManager manager = new LinearLayoutManager(CallPhoneActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.addItemDecoration(new DividerItemDecoration(CallPhoneActivity.this, DividerItemDecoration.VERTICAL));
                                adapter = new ActivityCallPhoneAdapter(mList);
                                recyclerView.setAdapter(adapter);
                                adapter.setOnCallListener(new ActivityCallPhoneAdapter.OnCallListener() {
                                    @Override
                                    public void onCall(int position) {
                                        callPhone(mList.get(position).getTelNumber());
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

    @OnClick({R.id.activity_call_phone_rl_back, R.id.ll_phone_type})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_call_phone_rl_back:
                finish();
                break;
            case R.id.ll_phone_type:
                showCallType();
                break;
        }
    }

    private void callPhone(final String telNum){
        mHelper.requestPermissions("请授予[拨打电话]，否则无法拨打", new PermissionHelper.PermissionListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void doAfterGrand(String... permission) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + telNum);
                intent.setData(data);
                startActivity(intent);
            }

            @Override
            public void doAfterDenied(String... permission) {
                ToastUtil.showShort(CallPhoneActivity.this, "请授权,否则无法拨打");
            }
        }, Manifest.permission.CALL_PHONE);
    }

    //直接把参数交给mHelper就行了
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showCallType(){

        View view = LayoutInflater.from(CallPhoneActivity.this).inflate(R.layout.popupwindow_call_type, null);
        ScreenAdapterTools.getInstance().loadView(view);

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
//        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        popupWindow.showAsDropDown(rlSearch);
        // 设置popWindow的显示和消失动画
//        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.alpha = 0.5f;
//        getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
//                WindowManager.LayoutParams params = getWindow().getAttributes();
//                params.alpha = 1f;
//                getWindow().setAttributes(params);
            }
        });

//        tvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                popupWindow.dismiss();
//            }
//        });

    }

}
