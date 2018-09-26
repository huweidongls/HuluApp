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

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.activity_feedback_et_content)
    EditText etContent;

    private SpImp spImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        spImp = new SpImp(FeedbackActivity.this);
        ButterKnife.bind(FeedbackActivity.this);

    }

    @OnClick({R.id.activity_feedback_rl_back, R.id.activity_feedback_btn_complete})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_feedback_rl_back:
                finish();
                break;
            case R.id.activity_feedback_btn_complete:
                onComplete();
                break;
        }
    }

    private void onComplete() {

        String name = spImp.getNAME();
        String content = etContent.getText().toString();
        if(!TextUtils.isEmpty(content)){

            Map<String, Object> map = new LinkedHashMap<>();
            map.put("createBy", name);
            map.put("feedbackContent", content);
            String json = Map2Json.map2json(map);
            ViseHttp.POST("/platformFeedbackApi/toUpdate")
                    .setJson(json)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Log.e("123123", data);
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.getString("status").equals("SUCCESS")){
                                    ToastUtil.showShort(FeedbackActivity.this, "提交成功");
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
            ToastUtil.showShort(FeedbackActivity.this, "反馈内容不能为空");
        }

    }

}
