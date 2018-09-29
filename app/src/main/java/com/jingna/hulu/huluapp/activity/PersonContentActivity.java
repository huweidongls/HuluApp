package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.model.PersonContentModel;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.jingna.hulu.huluapp.utils.Constant;
import com.jingna.hulu.huluapp.utils.DateUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonContentActivity extends BaseActivity {

    @BindView(R.id.activity_person_content_tv_name)
    TextView tvName;
    @BindView(R.id.activity_person_content_tv_sex)
    TextView tvSex;
    @BindView(R.id.activity_person_content_tv_age)
    TextView tvAge;
    @BindView(R.id.activity_person_content_tv_join_job_time)
    TextView tvJoinTime;
    @BindView(R.id.activity_person_content_tv_phone_num)
    TextView tvPhoneNum;
    @BindView(R.id.activity_person_content_tv_address)
    TextView tvAddress;

    private SpImp spImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_content);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        spImp = new SpImp(PersonContentActivity.this);
        ButterKnife.bind(PersonContentActivity.this);

        initData();

    }

    private void initData() {

        String url = Constant.BASE_URL+"SystemUser/getOne?userId="+spImp.getUID();
        ViseHttp.GET(url)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                PersonContentModel model = gson.fromJson(data, PersonContentModel.class);
                                tvName.setText(model.getData().getPeopleName());
                                if(model.getData().getSex() == 1){
                                    tvSex.setText("男");
                                }else if(model.getData().getSex() == 0){
                                    tvSex.setText("女");
                                }
                                tvAge.setText(model.getData().getUserAge()+"岁");
                                tvJoinTime.setText(DateUtils.stampToDate(model.getData().getJoinTime()+""));
                                tvPhoneNum.setText(model.getData().getNum5());
                                tvAddress.setText(model.getData().getAddress());
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

    @OnClick({R.id.activity_person_content_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_person_content_rl_back:
                finish();
                break;
        }
    }

}
