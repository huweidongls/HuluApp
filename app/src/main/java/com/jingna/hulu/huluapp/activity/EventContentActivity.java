package com.jingna.hulu.huluapp.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityDetailsDangerShowAdapter;
import com.jingna.hulu.huluapp.adapter.ActivityEventContentAppendAdapter;
import com.jingna.hulu.huluapp.adapter.EventContentLeaderAdapter;
import com.jingna.hulu.huluapp.adapter.ExampleShowAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.manager.AudioRecordButton;
import com.jingna.hulu.huluapp.manager.MediaManager;
import com.jingna.hulu.huluapp.model.BaiduCityModel;
import com.jingna.hulu.huluapp.model.EventContentModel;
import com.jingna.hulu.huluapp.model.FileUploadSoundModel;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.jingna.hulu.huluapp.utils.PermissionHelper;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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
    @BindView(R.id.em_lv_recodeList)
    RecyclerView rvRecord;
    @BindView(R.id.activity_event_content_rl_append)
    RelativeLayout rlAppend;
    @BindView(R.id.activity_event_content_iv_jianpan_or_yuyin)
    ImageView ivJpOrYy;
    @BindView(R.id.em_tv_btn)
    AudioRecordButton mEmTvBtn;
    @BindView(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @BindView(R.id.activity_event_content_rl_send_msg)
    RelativeLayout rlSendMsg;
    @BindView(R.id.activity_event_content_et_msg)
    EditText etMsg;
    @BindView(R.id.activity_event_content_rv1)
    RecyclerView recyclerView1;//领导处置建议
    @BindView(R.id.list_ll1)
    LinearLayout listLL1;
    @BindView(R.id.list_ll2)
    LinearLayout listLL2;
    @BindView(R.id.tv_solve_content)
    TextView tvSolveContent;

    private int id;

    private List<EventContentModel.DataBean> eventType1;
    private List<EventContentModel.DataBean> eventType2;
    private List<EventContentModel.DataBean> eventType3;

    private ActivityDetailsDangerShowAdapter showAdapter;
    private List<String> picList;

    private ExampleShowAdapter recordAdapter;

    private PermissionHelper mHelper;

    /**
     * 事件补充
     */
    private ActivityEventContentAppendAdapter appendAdapter;
    private List<EventContentModel.DataBean> appendList;

    private SpImp spImp;

    /**
     * 领导端处置建议
     */
    private EventContentLeaderAdapter eventContentLeaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_content);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventContentActivity.this);
        mHelper = new PermissionHelper(this);
        spImp = new SpImp(EventContentActivity.this);
        initView();
        initData();
        initListener();

    }

    private void initView() {

        if(spImp.getUID() != 0){
            if(spImp.getUIDTYPE().equals("1")){
//                intent.setClass(LoginActivity.this, Main1Activity.class);
//                startActivity(intent);
//                finish();
                rlAppend.setVisibility(View.VISIBLE);
                rlBottom.setVisibility(View.GONE);
            }else if(spImp.getUIDTYPE().equals("2")){
//                intent.setClass(LoginActivity.this, Main2Activity.class);
//                startActivity(intent);
//                finish();
                rlAppend.setVisibility(View.GONE);
                rlBottom.setVisibility(View.VISIBLE);
            }
        }

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

        String url = "/eventApi/getOne?id="+id;
        ViseHttp.GET(url)
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
                                tvSolveContent.setText(eventType1.get(0).getSolveContent());
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
                                //加载事件主体语音
                                LinearLayoutManager managerRecord = new LinearLayoutManager(EventContentActivity.this){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                managerRecord.setOrientation(LinearLayoutManager.VERTICAL);
                                rvRecord.setLayoutManager(managerRecord);
                                String[] path = eventType1.get(0).getEventRecordings().split(",");
                                List<String> recordList = new ArrayList<>();
                                for (int i = 0; i<path.length; i++){
                                    recordList.add(path[i]);
                                }
                                recordAdapter = new ExampleShowAdapter(EventContentActivity.this, recordList);
                                rvRecord.setAdapter(recordAdapter);

                                //加载补充内容
                                if(eventType2.size() == 0){
                                    listLL1.setVisibility(View.GONE);
                                }else {
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

                                //领导补充内容
                                if(eventType3.size() == 0){
                                    listLL2.setVisibility(View.GONE);
                                }else {
                                    LinearLayoutManager manager2 = new LinearLayoutManager(EventContentActivity.this){
                                        @Override
                                        public boolean canScrollVertically() {
                                            return false;
                                        }
                                    };
                                    manager2.setOrientation(LinearLayoutManager.VERTICAL);
                                    recyclerView1.setLayoutManager(manager2);
                                    eventContentLeaderAdapter = new EventContentLeaderAdapter(EventContentActivity.this, eventType3);
                                    recyclerView1.setAdapter(eventContentLeaderAdapter);
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

    @OnClick({R.id.activity_event_content_rl_back, R.id.activity_event_content_rl_append, R.id.activity_event_content_iv_jianpan_or_yuyin,
    R.id.activity_event_content_tv_send_msg})
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
            case R.id.activity_event_content_iv_jianpan_or_yuyin:
                if(rlSendMsg.getVisibility() == View.VISIBLE){
                    ivJpOrYy.setImageResource(R.drawable.jianpan);
                    rlSendMsg.setVisibility(View.GONE);
                    mEmTvBtn.setVisibility(View.VISIBLE);
                }else {
                    ivJpOrYy.setImageResource(R.drawable.yuyin);
                    rlSendMsg.setVisibility(View.VISIBLE);
                    mEmTvBtn.setVisibility(View.GONE);
                }
                break;
            case R.id.activity_event_content_tv_send_msg:
                //发送文字
                sendMsg();
                break;
        }
    }

    /**
     * 发送文字消息
     */
    private void sendMsg() {

        String msg = etMsg.getText().toString();
        if(!TextUtils.isEmpty(msg)){

            Map<String, Object> map = new LinkedHashMap<>();
            map.put("createBy", spImp.getNAME());
            map.put("eventContent", msg);
            map.put("eventId", id);
            String json = Map2Json.map2json(map);
            Log.e("123123", json);
            ViseHttp.POST("eventApi/toLeader")
                    .setJson(json)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.getString("status").equals("SUCCESS")){
                                    ToastUtil.showShort(EventContentActivity.this, "发送成功");
                                    etMsg.setText(null);
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
            ToastUtil.showShort(EventContentActivity.this, "发送消息不能为空");
        }

    }

    /**
     * 设置语音上传监听
     */
    private void initListener() {
        mEmTvBtn.setHasRecordPromission(false);
        //        授权处理
        mHelper.requestPermissions("请授予[录音]，[读写]权限，否则无法录音",
                new PermissionHelper.PermissionListener() {
                    @Override
                    public void doAfterGrand(String... permission) {
                        mEmTvBtn.setHasRecordPromission(true);
                        mEmTvBtn.setAudioFinishRecorderListener(new AudioRecordButton.AudioFinishRecorderListener() {
                            @Override
                            public void onFinished(float seconds, String filePath) {
                                File file = new File(filePath);
                                ViseHttp.UPLOAD("/bannerApi/fileUploadSound")
                                        .addHeader("Content-Type", "multipart/form-data")
                                        .addFile("file0", file)
                                        .request(new ACallback<String>() {
                                            @Override
                                            public void onSuccess(String data) {
                                                Log.e("123123", data);
                                                try {
                                                    JSONObject jsonObject = new JSONObject(data);
                                                    if (jsonObject.getString("status").equals("SUCCESS")) {
                                                        Gson gson = new Gson();
                                                        FileUploadSoundModel model = gson.fromJson(data, FileUploadSoundModel.class);
                                                        String sound = model.getData().get(0);
                                                        Map<String, Object> map = new LinkedHashMap<>();
                                                        map.put("createBy", spImp.getNAME());
                                                        map.put("eventRecordings", sound);
                                                        map.put("eventId", id);
                                                        String json = Map2Json.map2json(map);
                                                        ViseHttp.POST("eventApi/toLeader")
                                                                .setJson(json)
                                                                .request(new ACallback<String>() {
                                                                    @Override
                                                                    public void onSuccess(String data) {
                                                                        try {
                                                                            JSONObject jsonObject = new JSONObject(data);
                                                                            if(jsonObject.getString("status").equals("SUCCESS")){
                                                                                ToastUtil.showShort(EventContentActivity.this, "发送成功");
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

                    @Override
                    public void doAfterDenied(String... permission) {
                        mEmTvBtn.setHasRecordPromission(false);
                        Toast.makeText(EventContentActivity.this, "请授权,否则无法录音", Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    //直接把参数交给mHelper就行了
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause() {
        MediaManager.release();//保证在退出该页面时，终止语音播放
        super.onPause();
    }

}
