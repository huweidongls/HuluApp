package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityVideoAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.model.VideoListModel;
import com.jingna.hulu.huluapp.utils.Constant;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.vise.xsnow.cache.SpCache;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends BaseActivity {

    @BindView(R.id.activity_video_rv)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText etSearch;

    private ActivityVideoAdapter adapter;
    private List<VideoListModel.DataBean> mList;

    private SpCache spCache;
    private String num1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(VideoActivity.this);
        spCache = new SpCache(VideoActivity.this);
        num1 = spCache.get(Constant.BUMEN, "");
        initData();

    }

    private void initData() {

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pageNum", 0);
        map.put("pageSize", 0);
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("num1", num1);
        map.put("systemUserExt", map1);
        String json = Map2Json.map2json(map);

        ViseHttp.POST("/SystemUser/queryList")
                .setJson(json)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("123123", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                VideoListModel model = gson.fromJson(data, VideoListModel.class);
                                mList = model.getData();
                                LinearLayoutManager manager = new LinearLayoutManager(VideoActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.addItemDecoration(new DividerItemDecoration(VideoActivity.this, DividerItemDecoration.VERTICAL));
                                adapter = new ActivityVideoAdapter(mList);
                                recyclerView.setAdapter(adapter);
                                etSearch.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {
                                        adapter.getFilter().filter(sequence.toString());
                                    }

                                    @Override
                                    public void afterTextChanged(Editable editable) {

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

    @OnClick({R.id.activity_video_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_video_rl_back:
                finish();
                break;
        }
    }

}
