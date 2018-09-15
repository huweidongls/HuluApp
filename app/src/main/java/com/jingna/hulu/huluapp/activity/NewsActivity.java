package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityNewsAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.model.NewsModel;
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

public class NewsActivity extends BaseActivity {

    @BindView(R.id.activity_news_rv)
    RecyclerView recyclerView;

    private ActivityNewsAdapter adapter;
    private List<NewsModel.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(NewsActivity.this);

        initData();

    }

    private void initData() {

        ViseHttp.POST("/platformNewsApi/queryList")
                .setJson("{\n" +
                        "  \"pageNum\": 0,\n" +
                        "  \"pageSize\": 0,\n" +
                        "  \"platformNewsExt\": {\n" +
                        "   \n" +
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
                                NewsModel model = gson.fromJson(data, NewsModel.class);
                                mList = model.getData();
                                LinearLayoutManager manager = new LinearLayoutManager(NewsActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                adapter = new ActivityNewsAdapter(mList);
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

    @OnClick({R.id.activity_news_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_news_rl_back:
                finish();
                break;
        }
    }

}
