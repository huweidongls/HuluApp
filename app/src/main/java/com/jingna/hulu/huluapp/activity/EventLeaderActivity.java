package com.jingna.hulu.huluapp.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityEventLeaderAdapter;
import com.jingna.hulu.huluapp.adapter.ActivityEventListAdapter;
import com.jingna.hulu.huluapp.adapter.ShowBumenAdapter;
import com.jingna.hulu.huluapp.adapter.ShowBumenSonAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.model.BumenOneModel;
import com.jingna.hulu.huluapp.model.BumenSonModel;
import com.jingna.hulu.huluapp.model.EventListModel;
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
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventLeaderActivity extends BaseActivity {

    @BindView(R.id.activity_event_leader_refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.activity_event_leader_rv)
    RecyclerView recyclerView;
    @BindView(R.id.ll_select)
    LinearLayout llSelect;
    @BindView(R.id.activity_event_leader_event_type)
    TextView tvEventType;
    @BindView(R.id.activity_event_leader_event_type1)
    ImageView ivEventType1;
    @BindView(R.id.activity_event_leader_event_type2)
    ImageView ivEventType2;
    @BindView(R.id.activity_event_leader_et_search)
    EditText etSearch;
    @BindView(R.id.activity_event_leader_bumen)
    TextView tvBumen;
    @BindView(R.id.activity_event_leader_bumen1)
    ImageView ivBumen1;
    @BindView(R.id.activity_event_leader_bumen2)
    ImageView ivBumen2;

    private ActivityEventLeaderAdapter adapter;
    private List<EventListModel.DataBean> mList;
    private List<EventListModel.DataBean> mList0;
    private List<EventListModel.DataBean> mList1;
    private List<EventListModel.DataBean> mData;

    private int mYear;
    private int mMonth;
    private int mDay;

    private int page = 1;

    private PopupWindow popupWindow;
    private PopupWindow popupWindow1;

    private int CALL_TYPE = 1;

    private DoubleTimeSelectDialog mDoubleTimeSelectDialog;

    private SpCache spCache;

    private String bumenId;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_leader);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventLeaderActivity.this);
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        spCache = new SpCache(EventLeaderActivity.this);

        initData();

    }

    private void initData() {

        bumenId = spCache.get(Constant.BUMEN, "0");
        mList = new ArrayList<>();
        mList0 = new ArrayList<>();
        mList1 = new ArrayList<>();
        mData = new ArrayList<>();

        refreshLayout.setRefreshHeader(new ClassicsHeader(EventLeaderActivity.this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(EventLeaderActivity.this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("pageNum", 1);
                map.put("pageSize", 5);
                Map<String, Object> map1 = new LinkedHashMap<>();
//                map1.put("orderBy", "create_date desc");
                map1.put("eventType", 1);
                map1.put("pd", bumenId);
                map1.put("time", time);
                if (CALL_TYPE == 2) {
                    map1.put("isSolve", 0);
                } else if (CALL_TYPE == 3) {
                    map1.put("isSolve", 1);
                }
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
                                    if (jsonObject.getString("status").equals("SUCCESS")) {
                                        Gson gson = new Gson();
                                        EventListModel model = gson.fromJson(data, EventListModel.class);
                                        mData.clear();
                                        mData.addAll(model.getData());
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
                map1.put("eventType", 1);
                map1.put("pd", bumenId);
                map1.put("time", time);
                if (CALL_TYPE == 2) {
                    map1.put("isSolve", 0);
                } else if (CALL_TYPE == 3) {
                    map1.put("isSolve", 1);
                }
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
                                    if (jsonObject.getString("status").equals("SUCCESS")) {
                                        Gson gson = new Gson();
                                        EventListModel model = gson.fromJson(data, EventListModel.class);
                                        mData.addAll(model.getData());
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
        map1.put("eventType", 1);
        map1.put("pd", bumenId);
        map1.put("time", time);
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
                            if (jsonObject.getString("status").equals("SUCCESS")) {
                                Gson gson = new Gson();
                                EventListModel model = gson.fromJson(data, EventListModel.class);
                                mList.addAll(model.getData());
                                for (int i = 0; i < mList.size(); i++) {
                                    if (mList.get(i).getIsSolve() == 0) {
                                        mList0.add(mList.get(i));
                                    } else if (mList.get(i).getIsSolve() == 1) {
                                        mList1.add(mList.get(i));
                                    }
                                }
                                mData.addAll(mList);
                                adapter = new ActivityEventLeaderAdapter(mData);
                                LinearLayoutManager manager = new LinearLayoutManager(EventLeaderActivity.this);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                                page = 2;
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
                        Log.e("123123", errMsg);
                    }
                });

    }

    @OnClick({R.id.activity_event_leader_rl_back, R.id.iv_calendar, R.id.activity_event_leader_rl_left, R.id.activity_event_leader_rl_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_event_leader_rl_back:
                finish();
                break;
            case R.id.iv_calendar:
//                new DatePickerDialog(EventLeaderActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
                showCustomTimePicker();
                break;
            case R.id.activity_event_leader_rl_left:
                tvEventType.setTextColor(Color.parseColor("#32CC0D"));
                ivEventType1.setImageResource(R.drawable.top_g);
                ivEventType2.setImageResource(R.drawable.bottom_g);
                tvBumen.setTextColor(Color.parseColor("#333333"));
                ivBumen1.setImageResource(R.drawable.top_b);
                ivBumen2.setImageResource(R.drawable.bottom_b);
                showCallType(CALL_TYPE);
                break;
            case R.id.activity_event_leader_rl_right:
                tvEventType.setTextColor(Color.parseColor("#333333"));
                ivEventType1.setImageResource(R.drawable.top_b);
                ivEventType2.setImageResource(R.drawable.bottom_b);
                tvBumen.setTextColor(Color.parseColor("#32CC0D"));
                ivBumen1.setImageResource(R.drawable.top_g);
                ivBumen2.setImageResource(R.drawable.bottom_g);
                showBumen();
                break;
        }
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
//                    Log.e("123123", startTime.replace("-", ".") + "至\n" + endTime.replace("-", "."));
                    time = startTime + "," + endTime;
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("pageNum", 1);
                    map.put("pageSize", 5);
                    Map<String, Object> map1 = new LinkedHashMap<>();
//                    map1.put("orderBy", "create_date desc");
                    map1.put("eventType", 1);
                    map1.put("pd", bumenId);
                    map1.put("time", time);
                    if (CALL_TYPE == 2) {
                        map1.put("isSolve", 0);
                    } else if (CALL_TYPE == 3) {
                        map1.put("isSolve", 1);
                    }
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
                                        if (jsonObject.getString("status").equals("SUCCESS")) {
                                            Gson gson = new Gson();
                                            EventListModel model = gson.fromJson(data, EventListModel.class);
                                            mData.clear();
                                            mData.addAll(model.getData());
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

    private void showBumen() {
        View view = LayoutInflater.from(EventLeaderActivity.this).inflate(R.layout.popupwindow_show_bumen, null);
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
                            if (jsonObject.getString("status").equals("SUCCESS")) {
                                Gson gson = new Gson();
                                BumenOneModel model = gson.fromJson(data, BumenOneModel.class);
                                bumenList1.add(model.getData());
                                bumenAdapter1[0] = new ShowBumenAdapter(EventLeaderActivity.this, bumenList1);
                                lv1.setAdapter(bumenAdapter1[0]);
                                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        ViseHttp.POST("/SystemDepartment/getSon")
                                                .addParam("id", bumenList1.get(position).getId() + "")
                                                .request(new ACallback<String>() {
                                                    @Override
                                                    public void onSuccess(String data) {
                                                        try {
                                                            JSONObject jsonObject1 = new JSONObject(data);
                                                            if (jsonObject1.getString("status").equals("SUCCESS")) {
                                                                Gson gson1 = new Gson();
                                                                BumenSonModel model1 = gson1.fromJson(data, BumenSonModel.class);
                                                                bumenList2.clear();
                                                                bumenList2.add(new BumenSonModel.DataBean(Integer.valueOf(spCache.get(Constant.BUMEN, "0")), "全部"));
                                                                bumenList2.addAll(model1.getData());
                                                                bumenAdapter2[0] = new ShowBumenSonAdapter(EventLeaderActivity.this, bumenList2);
                                                                lv2.setAdapter(bumenAdapter2[0]);
                                                                lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                    @Override
                                                                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                                                        if (position == 0) {
                                                                            tvBumen.setText("全部");
                                                                            bumenId = spCache.get(Constant.BUMEN, "0");
                                                                            updataView(bumenId);
                                                                        } else {
                                                                            ViseHttp.POST("/SystemDepartment/getSon")
                                                                                    .addParam("id", bumenList2.get(position).getId() + "")
                                                                                    .request(new ACallback<String>() {
                                                                                        @Override
                                                                                        public void onSuccess(String data) {
                                                                                            try {
                                                                                                JSONObject jsonObject2 = new JSONObject(data);
                                                                                                if (jsonObject2.getString("status").equals("SUCCESS")) {
                                                                                                    Gson gson2 = new Gson();
                                                                                                    BumenSonModel model2 = gson2.fromJson(data, BumenSonModel.class);
                                                                                                    if (model2.getData().size() == 0) {
                                                                                                        tvBumen.setText(bumenList2.get(position).getDepartmentName());
                                                                                                        bumenId = bumenList2.get(position).getId() + "";
                                                                                                        updataView(bumenId);
                                                                                                    } else {
                                                                                                        bumenList3.clear();
                                                                                                        bumenList3.addAll(model2.getData());
                                                                                                        bumenAdapter3[0] = new ShowBumenSonAdapter(EventLeaderActivity.this, bumenList3);
                                                                                                        lv3.setAdapter(bumenAdapter3[0]);
                                                                                                        lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                                                            @Override
                                                                                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                                                                tvBumen.setText(bumenList3.get(position).getDepartmentName());
                                                                                                                bumenId = bumenList3.get(position).getId() + "";
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

    private void showCallType(int callType) {

        View view = LayoutInflater.from(EventLeaderActivity.this).inflate(R.layout.popupwindow_call_type, null);
        ScreenAdapterTools.getInstance().loadView(view);

        TextView tv1 = view.findViewById(R.id.tv1);
        TextView tv2 = view.findViewById(R.id.tv2);
        TextView tv3 = view.findViewById(R.id.tv3);
        tv1.setText("全部");
        tv2.setText("未处置事件");
        tv3.setText("已处置事件");
        RelativeLayout rl1 = view.findViewById(R.id.rl1);
        RelativeLayout rl2 = view.findViewById(R.id.rl2);
        RelativeLayout rl3 = view.findViewById(R.id.rl3);

        switch (callType) {
            case 1:
                tv1.setTextColor(Color.parseColor("#32CC0D"));
                tv2.setTextColor(Color.parseColor("#404040"));
                tv3.setTextColor(Color.parseColor("#404040"));
                break;
            case 2:
                tv1.setTextColor(Color.parseColor("#404040"));
                tv2.setTextColor(Color.parseColor("#32CC0D"));
                tv3.setTextColor(Color.parseColor("#404040"));
                break;
            case 3:
                tv1.setTextColor(Color.parseColor("#404040"));
                tv2.setTextColor(Color.parseColor("#404040"));
                tv3.setTextColor(Color.parseColor("#32CC0D"));
                break;
        }

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
//        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        popupWindow.showAsDropDown(llSelect);
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

        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CALL_TYPE = 1;
                tvEventType.setText("全部");
                popupWindow.dismiss();

                Map<String, Object> map = new LinkedHashMap<>();
                map.put("pageNum", 1);
                map.put("pageSize", 5);
                Map<String, Object> map1 = new LinkedHashMap<>();
//                map1.put("orderBy", "create_date desc");
                map1.put("eventType", 1);
                map1.put("time", time);
                map1.put("pd", bumenId);
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
                                    if (jsonObject.getString("status").equals("SUCCESS")) {
                                        Gson gson = new Gson();
                                        EventListModel model = gson.fromJson(data, EventListModel.class);
                                        mData.clear();
                                        mData.addAll(model.getData());
                                        adapter.notifyDataSetChanged();
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
        });

        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CALL_TYPE = 2;
                tvEventType.setText("未处置事件");
                popupWindow.dismiss();

                Map<String, Object> map = new LinkedHashMap<>();
                map.put("pageNum", 1);
                map.put("pageSize", 5);
                Map<String, Object> map1 = new LinkedHashMap<>();
//                map1.put("orderBy", "create_date desc");
                map1.put("eventType", 1);
                map1.put("isSolve", 0);
                map1.put("time", time);
                map1.put("pd", bumenId);
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
                                    if (jsonObject.getString("status").equals("SUCCESS")) {
                                        Gson gson = new Gson();
                                        EventListModel model = gson.fromJson(data, EventListModel.class);
                                        mData.clear();
                                        mData.addAll(model.getData());
                                        adapter.notifyDataSetChanged();
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
        });

        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CALL_TYPE = 3;
                tvEventType.setText("已处置事件");
                popupWindow.dismiss();

                Map<String, Object> map = new LinkedHashMap<>();
                map.put("pageNum", 1);
                map.put("pageSize", 5);
                Map<String, Object> map1 = new LinkedHashMap<>();
//                map1.put("orderBy", "create_date desc");
                map1.put("eventType", 1);
                map1.put("isSolve", 1);
                map1.put("time", time);
                map1.put("pd", bumenId);
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
                                    if (jsonObject.getString("status").equals("SUCCESS")) {
                                        Gson gson = new Gson();
                                        EventListModel model = gson.fromJson(data, EventListModel.class);
                                        mData.clear();
                                        mData.addAll(model.getData());
                                        adapter.notifyDataSetChanged();
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
        });

    }

    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            }
        }
    };

    private void updataView(String bumen) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pageNum", 1);
        map.put("pageSize", 5);
        Map<String, Object> map1 = new LinkedHashMap<>();
//        map1.put("orderBy", "create_date desc");
        map1.put("eventType", 1);
        map1.put("time", time);
        map1.put("pd", bumen);
        if (CALL_TYPE == 2) {
            map1.put("isSolve", 0);
        } else if (CALL_TYPE == 3) {
            map1.put("isSolve", 1);
        }
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
                            if (jsonObject.getString("status").equals("SUCCESS")) {
                                Gson gson = new Gson();
                                EventListModel model = gson.fromJson(data, EventListModel.class);
                                mData.clear();
                                mData.addAll(model.getData());
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
