package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityLineDangerAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.model.LineDangerModel;
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

public class LineDangerActivity extends BaseActivity {

    @BindView(R.id.activity_line_danger_rv)
    RecyclerView recyclerView;

    private ActivityLineDangerAdapter adapter;
    private List<LineDangerModel.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_danger);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(LineDangerActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

        ViseHttp.POST("/platformSolve/queryList")
                .setJson("{\n" +
                        "  \"pageNum\": 0,\n" +
                        "  \"pageSize\": 0,\n" +
                        "  \"platformSolveExt\": {\n" +
                        "    \n" +
                        "  }\n" +
                        "}")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                LineDangerModel model = gson.fromJson(data, LineDangerModel.class);
                                mList = model.getData();
                                adapter = new ActivityLineDangerAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(LineDangerActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
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

    @OnClick({R.id.activity_line_danger_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_line_danger_rl_back:
                finish();
                break;
        }
    }

}
