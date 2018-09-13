package com.jingna.hulu.huluapp.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ExampleAdapter;
import com.jingna.hulu.huluapp.adapter.IntercalationAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.history.DBManager;
import com.jingna.hulu.huluapp.manager.AudioRecordButton;
import com.jingna.hulu.huluapp.manager.MediaManager;
import com.jingna.hulu.huluapp.utils.PermissionHelper;
import com.jingna.hulu.huluapp.utils.Record;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventsReportedActivity extends BaseActivity {

    @BindView(R.id.activity_events_reported_rv_pic)
    RecyclerView rvPic;
    @BindView(R.id.activity_events_reported_sp)
    Spinner spinner;
    @BindView(R.id.em_tv_btn)
    AudioRecordButton mEmTvBtn;
    @BindView(R.id.em_lv_recodeList)
    RecyclerView mEmLvRecodeList;

    private IntercalationAdapter adapter;
    private List<String> mList;

    List<Record> mRecords;
    ExampleAdapter mExampleAdapter;
    PermissionHelper mHelper;
    //db
    private DBManager mgr;

    private static final int REQUEST_CODE = 0x00000011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_reported);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventsReportedActivity.this);

        initData();
        initSpinner();
        initAdapter();
        initListener();

    }

    private void initSpinner() {

        //原始string数组
        final String[] spinnerItems = {"事件分类1","事件分类2","事件分类3","事件分类4","事件分类5"};
        //简单的string数组适配器：样式res，数组
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(EventsReportedActivity.this,
                android.R.layout.simple_spinner_item, spinnerItems);
        //下拉的样式res
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner.setAdapter(spinnerAdapter);
        //选择监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //parent就是父控件spinner
            //view就是spinner内填充的textview,id=@android:id/text1
            //position是值所在数组的位置
            //id是值所在行的位置，一般来说与positin一致
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
//                LogUtil.i("onItemSelected : parent.id="+parent.getId()+
//                        ",isSpinnerId="+(parent.getId() == R.id.spinner_1)+
//                        ",viewid="+view.getId()+ ",pos="+pos+",id="+id);
//                ToastUtil.showShort(instance,"选择了["+spinnerItems[pos]+"]");
                //设置spinner内的填充文字居中
                //((TextView)view).setGravity(Gravity.CENTER);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

    }

    private void initData() {

        mRecords = new ArrayList<>();
        //初始化DBManager
        mgr = new DBManager(this);

        GridLayoutManager manager = new GridLayoutManager(EventsReportedActivity.this, 3);
        rvPic.setLayoutManager(manager);
        mList = new ArrayList<>();
        adapter = new IntercalationAdapter(mList);
        rvPic.setAdapter(adapter);
        adapter.setListener(new IntercalationAdapter.OnAddImgListener() {
            @Override
            public void onAddImg() {
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(9 - mList.size()) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected(selected) // 把已选的图片传入默认选中。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(EventsReportedActivity.this, REQUEST_CODE); // 打开相册
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
            mList.addAll(images);
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.activity_events_reported_rl_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_events_reported_rl_back:
                finish();
                break;
        }
    }

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(EventsReportedActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mEmLvRecodeList.setLayoutManager(manager);
        mExampleAdapter = new ExampleAdapter(this, mRecords);
        mEmLvRecodeList.setAdapter(mExampleAdapter);

        //开始获取数据库数据
//        List<Record> records = mgr.query();
//        if (records == null || records.isEmpty()) return;
//        for (Record record : records) {
//            Log.e("wgy", "initAdapter: " + record.toString());
//        }
//        mRecords.addAll(records);
//        mExampleAdapter.notifyDataSetChanged();
//        mEmLvRecodeList.setSelection(mRecords.size() - 1);
    }

    private void initListener() {
        mEmTvBtn.setHasRecordPromission(false);
        //        授权处理
        mHelper = new PermissionHelper(this);

        mHelper.requestPermissions("请授予[录音]，[读写]权限，否则无法录音",
                new PermissionHelper.PermissionListener() {
                    @Override
                    public void doAfterGrand(String... permission) {
                        mEmTvBtn.setHasRecordPromission(true);
//                        mEmTvBtn.setAudioFinishRecorderListener((seconds, filePath) -> {
//                            Record recordModel = new Record();
//                            recordModel.setSecond((int) seconds <= 0 ? 1 : (int) seconds);
//                            recordModel.setPath(filePath);
//                            recordModel.setPlayed(false);
//                            mRecords.add(recordModel);
//                            mExampleAdapter.notifyDataSetChanged();
//                            mEmLvRecodeList.setSelection(mRecords.size() - 1);
//
//                            //添加到数据库
//                            mgr.add(recordModel);
//                        });
                        mEmTvBtn.setAudioFinishRecorderListener(new AudioRecordButton.AudioFinishRecorderListener() {
                            @Override
                            public void onFinished(float seconds, String filePath) {
                                Record recordModel = new Record();
                                recordModel.setSecond((int) seconds <= 0 ? 1 : (int) seconds);
                                recordModel.setPath(filePath);
                                recordModel.setPlayed(false);
                                mRecords.add(recordModel);
                                mExampleAdapter.notifyDataSetChanged();
//                                mEmLvRecodeList.setSelection(mRecords.size() - 1);

                                //添加到数据库
//                                mgr.add(recordModel);
                            }
                        });
                    }

                    @Override
                    public void doAfterDenied(String... permission) {
                        mEmTvBtn.setHasRecordPromission(false);
                        Toast.makeText(EventsReportedActivity.this, "请授权,否则无法录音", Toast.LENGTH_SHORT).show();
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

    public DBManager getMgr() {
        return mgr;
    }

    public void setMgr(DBManager mgr) {
        this.mgr = mgr;
    }

}
