package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.activity_change_password_et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.activity_change_password_et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.activity_change_password_et_new_pwd_sure)
    EditText etNewPwdSure;

    private SpImp spImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        spImp = new SpImp(ChangePasswordActivity.this);
        ButterKnife.bind(ChangePasswordActivity.this);

    }

    @OnClick({R.id.activity_change_password_rl_back, R.id.activity_change_password_btn_complete})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_change_password_rl_back:
                finish();
                break;
            case R.id.activity_change_password_btn_complete:
                onComplete();
                break;
        }
    }

    private void onComplete() {

        String oldPwd = etOldPwd.getText().toString();
        final String newPwd = etNewPwd.getText().toString();
        String newPwdSure = etNewPwdSure.getText().toString();
        if(oldPwd.equals(spImp.getPASSWORD())){
            if(!TextUtils.isEmpty(newPwd)&&!TextUtils.isEmpty(newPwdSure)){
                if(newPwd.equals(newPwdSure)){

                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("id", spImp.getUID());
                    map.put("password", newPwd);
                    String json = Map2Json.map2json(map);
                    ViseHttp.POST("/SystemUser/updateUser")
                            .setJson(json)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.e("123123", data);
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.getString("status").equals("SUCCESS")){
                                            ToastUtil.showShort(ChangePasswordActivity.this, "密码修改成功");
                                            spImp.setPASSWORD(newPwd);
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {

                                }
                            });

                }else {
                    ToastUtil.showShort(ChangePasswordActivity.this, "新密码不一致");
                }
            }else {
                ToastUtil.showShort(ChangePasswordActivity.this, "新密码不能为空");
            }
        }else {
            ToastUtil.showShort(ChangePasswordActivity.this, "旧密码输入错误");
        }

    }

}
