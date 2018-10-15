package com.jingna.hulu.huluapp.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityLogInfoAdapter;
import com.jingna.hulu.huluapp.adapter.ShowBumenAdapter;
import com.jingna.hulu.huluapp.adapter.ShowBumenSonAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.model.BumenOneModel;
import com.jingna.hulu.huluapp.model.BumenSonModel;
import com.jingna.hulu.huluapp.model.EventListModel;
import com.jingna.hulu.huluapp.model.LogInfoModel;
import com.jingna.hulu.huluapp.utils.Constant;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.jingna.hulu.huluapp.widget.DoubleTimeSelectDialog;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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

public class LogInfoActivity extends BaseActivity {

    @BindView(R.id.activity_log_info_refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.activity_log_info_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_log_info_bumen)
    TextView tvBumen;
    @BindView(R.id.activity_log_info_bumen1)
    ImageView ivBumen1;
    @BindView(R.id.activity_log_info_bumen2)
    ImageView ivBumen2;
    @BindView(R.id.activity_log_info_time)
    TextView tvTime;
    @BindView(R.id.activity_log_info_time1)
    ImageView ivTime1;
    @BindView(R.id.activity_log_info_time2)
    ImageView ivTime2;
    @BindView(R.id.ll_select)
    LinearLayout llSelect;

    private ActivityLogInfoAdapter adapter;
    private List<LogInfoModel.DataBean> mList;

    private DoubleTimeSelectDialog mDoubleTimeSelectDialog;

    private PopupWindow popupWindow1;
    private int page = 1;
    private SpCache spCache;

    private String bumenId;

    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_info);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(LogInfoActivity.this);
        spCache = new SpCache(LogInfoActivity.this);
        initData();

    }

    private void initData() {

        bumenId = spCache.get(Constant.BUMEN, "0");

        refreshLayout.setRefreshHeader(new ClassicsHeader(LogInfoActivity.this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(LogInfoActivity.this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("pageNum", 1);
                map.put("pageSize", 5);
                Map<String, Object> map1 = new LinkedHashMap<>();
                map1.put("orderBy", "create_date desc");
                map1.put("time", time);
                map1.put("pd", bumenId);
                map.put("roadprotectionLoggerExt", map1);
                String json = Map2Json.map2json(map);
                Log.e("123123", json);
                ViseHttp.POST("/RoadprotectionLoggerApi/queryList")
                        .setJson(json)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                Log.e("123123", data);
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.getString("status").equals("SUCCESS")){
                                        Gson gson = new Gson();
                                        LogInfoModel model = gson.fromJson(data, LogInfoModel.class);
                                        mList.clear();
                                        mList.addAll(model.getData());
                                        adapter.notifyDataSetChanged();
                                        page = 2;
                                    }
                                    refreshLayout.finishRefresh(1000);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishRefresh(1000);
                            }
                        });
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("pageNum", page);
                map.put("pageSize", 5);
                Map<String, Object> map1 = new LinkedHashMap<>();
                map1.put("orderBy", "create_date desc");
                map1.put("time", time);
                map1.put("pd", bumenId);
                map.put("roadprotectionLoggerExt", map1);
                String json = Map2Json.map2json(map);
                Log.e("123123", json);
                ViseHttp.POST("/RoadprotectionLoggerApi/queryList")
                        .setJson(json)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                Log.e("123123", data);
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.getString("status").equals("SUCCESS")){
                                        Gson gson = new Gson();
                                        LogInfoModel model = gson.fromJson(data, LogInfoModel.class);
                                        mList.addAll(model.getData());
                                        adapter.notifyDataSetChanged();
                                        page = page+1;
                                    }
                                    refreshLayout.finishLoadMore(1000);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishLoadMore(1000);
                            }
                        });
            }
        });

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pageNum", 1);
        map.put("pageSize", 5);
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("orderBy", "create_date desc");
        map1.put("time", time);
        map1.put("pd", bumenId);
        map.put("roadprotectionLoggerExt", map1);
        String json = Map2Json.map2json(map);
        Log.e("123123", json);
        ViseHttp.POST("/RoadprotectionLoggerApi/queryList")
                .setJson(json)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("123123", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                LogInfoModel model = gson.fromJson(data, LogInfoModel.class);
                                mList = model.getData();
                                adapter = new ActivityLogInfoAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(LogInfoActivity.this);
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

                    }
                });

    }

    @OnClick({R.id.activity_log_info_rl_back, R.id.activity_log_info_rl_left, R.id.activity_log_info_rl_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_log_info_rl_back:
                finish();
                break;
            case R.id.activity_log_info_rl_left:
                tvBumen.setTextColor(Color.parseColor("#32CC0D"));
                ivBumen1.setImageResource(R.drawable.top_g);
                ivBumen2.setImageResource(R.drawable.bottom_g);
                tvTime.setTextColor(Color.parseColor("#333333"));
                ivTime1.setImageResource(R.drawable.top_b);
                ivTime2.setImageResource(R.drawable.bottom_b);
                showBumen();
                break;
            case R.id.activity_log_info_rl_right:
                tvTime.setTextColor(Color.parseColor("#32CC0D"));
                ivTime1.setImageResource(R.drawable.top_g);
                ivTime2.setImageResource(R.drawable.bottom_g);
                tvBumen.setTextColor(Color.parseColor("#333333"));
                ivBumen1.setImageResource(R.drawable.top_b);
                ivBumen2.setImageResource(R.drawable.bottom_b);
                showCustomTimePicker();
                break;
        }
    }

    private void showBumen(){
        View view = LayoutInflater.from(LogInfoActivity.this).inflate(R.layout.popupwindow_show_bumen, null);
        ScreenAdapterTools.getInstance().loadView(view);

        final ListView lv1 = view.findViewById(R.id.lv1);
        final ListView lv2 = view.findViewById(R.id.lv2);
        final ListView lv3 = view.findViewById(R.id.lv3);

        final ShowBumenAdapter[] bumenAdapter1 = {null};
        final ShowBumenSonAdapter[] bumenAdapter2 = new ShowBumenSonAdapter[1];
        final ShowBumenSonAdapter[] bumenAdapter3 = new ShowBumenSonAdapter[1];

        final List<BumenOneModel.DataBean> bumenList1 = new ArrayList<>();
        final List<BumenSonModel.DataBean> bumenList2 = new ArrayList<>();
        final List<BumenSonModel.DataBean> bumenList3 = new ArrayList<>();

        ViseHttp.POST("/SystemDepartment/getOne")
                .addParam("id", spCache.get(Constant.BUMEN, "0"))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("123123", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.getString("status").equals("SUCCESS")){
                                Gson gson = new Gson();
                                BumenOneModel model = gson.fromJson(data, BumenOneModel.class);
                                bumenList1.add(model.getData());
                                bumenAdapter1[0] = new ShowBumenAdapter(LogInfoActivity.this, bumenList1);
                                lv1.setAdapter(bumenAdapter1[0]);
                                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        ViseHttp.POST("/SystemDepartment/getSon")
                                                .addParam("id", bumenList1.get(position).getId()+"")
                                                .request(new ACallback<String>() {
                                                    @Override
                                                    public void onSuccess(String data) {
                                                        try {
                                                            JSONObject jsonObject1 = new JSONObject(data);
                                                            if(jsonObject1.getString("status").equals("SUCCESS")){
                                                                Gson gson1 = new Gson();
                                                                BumenSonModel model1 = gson1.fromJson(data, BumenSonModel.class);
                                                                bumenList2.clear();
                                                                bumenList2.add(new BumenSonModel.DataBean(Integer.valueOf(spCache.get(Constant.BUMEN, "0")), "全部"));
                                                                bumenList2.addAll(model1.getData());
                                                                bumenAdapter2[0] = new ShowBumenSonAdapter(LogInfoActivity.this, bumenList2);
                                                                lv2.setAdapter(bumenAdapter2[0]);
                                                                lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                    @Override
                                                                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                                                        if(position == 0){
                                                                            tvBumen.setText("全部");
                                                                            bumenId = spCache.get(Constant.BUMEN, "0");
                                                                            updataView(bumenId);
                                                                        }else {
                                                                            ViseHttp.POST("/SystemDepartment/getSon")
                                                                                    .addParam("id", bumenList2.get(position).getId()+"")
                                                                                    .request(new ACallback<String>() {
                                                                                        @Override
                                                                                        public void onSuccess(String data) {
                                                                                            try {
                                                                                                JSONObject jsonObject2 = new JSONObject(data);
                                                                                                if(jsonObject2.getString("status").equals("SUCCESS")){
                                                                                                    Gson gson2 = new Gson();
                                                                                                    BumenSonModel model2 = gson2.fromJson(data, BumenSonModel.class);
                                                                                                    if(model2.getData().size() == 0){
                                                                                                        tvBumen.setText(bumenList2.get(position).getDepartmentName());
                                                                                                        bumenId = bumenList2.get(position).getId()+"";
                                                                                                        updataView(bumenId);
                                                                                                    }else {
                                                                                                        bumenList3.clear();
                                                                                                        bumenList3.addAll(model2.getData());
                                                                                                        bumenAdapter3[0] = new ShowBumenSonAdapter(LogInfoActivity.this, bumenList3);
                                                                                                        lv3.setAdapter(bumenAdapter3[0]);
                                                                                                        lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                                                            @Override
                                                                                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                                                                tvBumen.setText(bumenList3.get(position).getDepartmentName());
                                                                                                                bumenId = bumenList3.get(position).getId()+"";
                                                                                                                updataView(bumenId);
                                                                                                            }
                                                                                                        });
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

        popupWindow1 = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow1.setTouchable(true);
        popupWindow1.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow1.setOutsideTouchable(true);
//        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        popupWindow1.showAsDropDown(llSelect);
        // 设置popWindow的显示和消失动画
//        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.alpha = 0.5f;
//        getWindow().setAttributes(params);
        popupWindow1.update();

        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
//                WindowManager.LayoutParams params = getWindow().getAttributes();
//                params.alpha = 1f;
//                getWindow().setAttributes(params);
            }
        });
    }

    /**
     * 默认的周开始时间，格式如：yyyy-MM-dd
     **/
    public String defaultWeekBegin;
    /**
     * 默认的周结束时间，格式如：yyyy-MM-dd
     */
    public String defaultWeekEnd;

    public void showCustomTimePicker() {
        String beginDeadTime = "2017-01-01";
        if (mDoubleTimeSelectDialog == null) {
            mDoubleTimeSelectDialog = new DoubleTimeSelectDialog(this, beginDeadTime, defaultWeekBegin, defaultWeekEnd);
            mDoubleTimeSelectDialog.setOnDateSelectFinished(new DoubleTimeSelectDialog.OnDateSelectFinished() {
                @Override
                public void onSelectFinished(String startTime, String endTime) {
//                    ui_button1.setText(startTime.replace("-", ".") + "至\n" + endTime.replace("-", "."));
                    Log.e("123123", startTime.replace("-", ".") + "至\n" + endTime.replace("-", "."));
                    time = startTime + "," + endTime;
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("pageNum", 1);
                    map.put("pageSize", 5);
                    Map<String, Object> map1 = new LinkedHashMap<>();
                    map1.put("orderBy", "create_date desc");
                    map1.put("time", time);
                    map1.put("pd", bumenId);
                    map.put("roadprotectionLoggerExt", map1);
                    String json = Map2Json.map2json(map);
                    Log.e("123123", json);
                    ViseHttp.POST("/RoadprotectionLoggerApi/queryList")
                            .setJson(json)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.e("123123", data);
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.getString("status").equals("SUCCESS")){
                                            Gson gson = new Gson();
                                            LogInfoModel model = gson.fromJson(data, LogInfoModel.class);
                                            mList.clear();
                                            mList.addAll(model.getData());
                                            adapter.notifyDataSetChanged();
                                            page = 2;
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
            });

            mDoubleTimeSelectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                }
            });
        }
        if (!mDoubleTimeSelectDialog.isShowing()) {
            mDoubleTimeSelectDialog.recoverButtonState();
            mDoubleTimeSelectDialog.show();
        }
    }

    private void updataView(String bumen) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pageNum", 1);
        map.put("pageSize", 5);
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("orderBy", "create_date desc");
        map1.put("time", time);
        map1.put("pd", bumen);
        map.put("roadprotectionLoggerExt", map1);
        String json = Map2Json.map2json(map);
        ViseHttp.POST("/RoadprotectionLoggerApi/queryList")
                .setJson(json)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("123123", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getString("status").equals("SUCCESS")) {
                                Gson gson = new Gson();
                                LogInfoModel model = gson.fromJson(data, LogInfoModel.class);
                                mList.clear();
                                mList.addAll(model.getData());
                                adapter.notifyDataSetChanged();
                                page = 2;
                            }
                            popupWindow1.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        popupWindow1.dismiss();
                    }
                });
    }

}
