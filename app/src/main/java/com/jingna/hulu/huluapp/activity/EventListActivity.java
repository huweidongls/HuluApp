package com.jingna.hulu.huluapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.LoginActivity;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityEventListAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.model.EventListModel;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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

public class EventListActivity extends BaseActivity {

    @BindView(R.id.activity_event_list_refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.activity_event_list_rv)
    RecyclerView recyclerView;

    private ActivityEventListAdapter adapter;
    private List<EventListModel.DataBean> mList;

    private int page = 1;

    private SpImp spImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        spImp = new SpImp(EventListActivity.this);
        ButterKnife.bind(EventListActivity.this);
        initData();

    }

    private void initData() {

        refreshLayout.setRefreshHeader(new ClassicsHeader(EventListActivity.this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(EventListActivity.this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("pageNum", 1);
                map.put("pageSize", 5);
                Map<String, Object> map1 = new LinkedHashMap<>();
//                map1.put("orderBy", "create_date desc");
                map1.put("createBy", spImp.getUID());
                map1.put("eventType", 1);
                map.put("eventExt", map1);
                String json = Map2Json.map2json(map);
                ViseHttp.POST("/eventApi/queryList")
                        .setJson(json)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                Log.e("123123", data);
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.getString("status").equals("SUCCESS")){
                                        Gson gson = new Gson();
                                        EventListModel model = gson.fromJson(data, EventListModel.class);
                                        mList.clear();
                                        mList.addAll(model.getData());
                                        adapter.notifyDataSetChanged();
                                        page = 2;
                                    }
                                    refreshlayout.finishRefresh(1000);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshlayout.finishRefresh(1000);
                            }
                        });
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshlayout) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("pageNum", page);
                map.put("pageSize", 5);
                Map<String, Object> map1 = new LinkedHashMap<>();
//                map1.put("orderBy", "create_date desc");
                map1.put("createBy", spImp.getUID());
                map1.put("eventType", 1);
                map.put("eventExt", map1);
                String json = Map2Json.map2json(map);
                ViseHttp.POST("/eventApi/queryList")
                        .setJson(json)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                Log.e("123123", data);
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.getString("status").equals("SUCCESS")){
                                        Gson gson = new Gson();
                                        EventListModel model = gson.fromJson(data, EventListModel.class);
                                        mList.addAll(model.getData());
                                        adapter.notifyDataSetChanged();
                                        page = page + 1;
                                    }
                                    refreshlayout.finishLoadMore(1000);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshlayout.finishLoadMore(1000);
                            }
                        });
            }
        });

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pageNum", 1);
        map.put("pageSize", 5);
        Map<String, Object> map1 = new LinkedHashMap<>();
//        map1.put("orderBy", "create_date desc");
        map1.put("createBy", spImp.getUID());
        map1.put("eventType", 1);
        map.put("eventExt", map1);
        String json = Map2Json.map2json(map);
        Log.e("123123", json);
        ViseHttp.POST("/eventApi/queryList")
                .setJson(json)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("123123", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                EventListModel model = gson.fromJson(data, EventListModel.class);
                                mList = model.getData();
                                adapter = new ActivityEventListAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(EventListActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                                page = 2;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        Log.e("123123", errMsg);
                    }
                });

    }

    @OnClick({R.id.activity_event_list_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_event_list_rl_back:
                finish();
                break;
        }
    }

}
