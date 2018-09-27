package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityDetailsDangerShowAdapter;
import com.jingna.hulu.huluapp.adapter.ActivityEventContentAppendAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.model.BaiduCityModel;
import com.jingna.hulu.huluapp.model.EventContentModel;
import com.jingna.hulu.huluapp.utils.Map2Json;
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

public class EventContentActivity extends BaseActivity {

    @BindView(R.id.activity_event_content_tv_title)
    TextView tvTitle;
    @BindView(R.id.activity_event_content_tv_type)
    TextView tvType;
    @BindView(R.id.activity_event_content_tv_content)
    TextView tvContent;
    @BindView(R.id.activity_event_content_tv_location)
    TextView tvLocation;
    @BindView(R.id.activity_event_content_rv_pic)
    RecyclerView recyclerViewPic;
    @BindView(R.id.activity_event_content_rv)
    RecyclerView recyclerView;

    private int id;

    private List<EventContentModel.DataBean> eventType1;
    private List<EventContentModel.DataBean> eventType2;
    private List<EventContentModel.DataBean> eventType3;

    private ActivityDetailsDangerShowAdapter showAdapter;
    private List<String> picList;

    /**
     * 事件补充
     */
    private ActivityEventContentAppendAdapter appendAdapter;
    private List<EventContentModel.DataBean> appendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_content);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventContentActivity.this);

        initData();

    }

    private void initData() {

        eventType1 = new ArrayList<>();
        eventType2 = new ArrayList<>();
        eventType3 = new ArrayList<>();
        picList = new ArrayList<>();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pageNum", 0);
        map.put("pageSize", 0);
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("id", id);
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
                                EventContentModel model = gson.fromJson(data, EventContentModel.class);
                                for (int i = 0; i<model.getData().size(); i++){
                                    if(model.getData().get(i).getEventType() == 1){
                                        eventType1.add(model.getData().get(i));
                                    }
                                    if(model.getData().get(i).getEventType() == 2){
                                        eventType2.add(model.getData().get(i));
                                    }
                                    if(model.getData().get(i).getEventType() == 3){
                                        eventType3.add(model.getData().get(i));
                                    }
                                }
                                //加载事件主体
                                tvTitle.setText(eventType1.get(0).getEventTitle());
                                tvType.setText(eventType1.get(0).getTypeName());
                                tvContent.setText(eventType1.get(0).getEventContent());
                                String a = eventType1.get(0).getNum2();
                                String s = a.substring(2, a.length()-2);
                                String[] aaaa = s.split(",");
                                String url = "http://api.map.baidu.com/geocoder?output=json&location=" + aaaa[1] + "," + aaaa[0] + "&key=ovbH9tDk74DcpRTv59n1zEOkRrmdSPf2";
                                ViseHttp.GET(url)
                                        .request(new ACallback<String>() {
                                            @Override
                                            public void onSuccess(String data) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(data);
                                                    if (jsonObject.getString("status").equals("OK")) {
                                                        Gson gson = new Gson();
                                                        BaiduCityModel model = gson.fromJson(data, BaiduCityModel.class);
                                                        tvLocation.setText(model.getResult().getFormatted_address());
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            @Override
                                            public void onFail(int errCode, String errMsg) {

                                            }
                                        });
                                String[] show = model.getData().get(0).getEventPic().split(",");
                                for (int i = 0; i<show.length; i++){
                                    picList.add(show[i]);
                                }
                                GridLayoutManager manager = new GridLayoutManager(EventContentActivity.this, 3);
                                recyclerViewPic.setLayoutManager(manager);
                                showAdapter = new ActivityDetailsDangerShowAdapter(picList);
                                recyclerViewPic.setAdapter(showAdapter);

                                //加载补充内容
                                LinearLayoutManager manager1 = new LinearLayoutManager(EventContentActivity.this){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager1.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager1);
                                Log.e("123123", eventType2.size()+"");
                                appendAdapter = new ActivityEventContentAppendAdapter(eventType2);
                                recyclerView.setAdapter(appendAdapter);

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

    @OnClick({R.id.activity_event_content_rl_back, R.id.activity_event_content_rl_append})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.activity_event_content_rl_back:
                finish();
                break;
            case R.id.activity_event_content_rl_append:
                intent.putExtra("id", id);
                intent.setClass(EventContentActivity.this, EventAppendActivity.class);
                startActivity(intent);
                break;
        }
    }

}
