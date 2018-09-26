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
import android.widget.Toast;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ExampleAdapter;
import com.jingna.hulu.huluapp.adapter.IntercalationAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.dialog.DialogCustom;
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

public class EventAppendActivity extends BaseActivity {

    @BindView(R.id.activity_events_reported_rv_pic)
    RecyclerView rvPic;
    @BindView(R.id.em_tv_btn)
    AudioRecordButton mEmTvBtn;
    @BindView(R.id.em_lv_recodeList)
    RecyclerView mEmLvRecodeList;

    private IntercalationAdapter adapter;
    private List<String> mList;
    private static final int REQUEST_CODE = 0x00000011;

    List<Record> mRecords;
    ExampleAdapter mExampleAdapter;
    PermissionHelper mHelper;
    //db
    private DBManager mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_append);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventAppendActivity.this);
        mHelper = new PermissionHelper(this);
        initData();
        initAdapter();
        initListener();

    }

    private void initData() {

        mRecords = new ArrayList<>();
        //初始化DBManager
        mgr = new DBManager(this);

        GridLayoutManager manager = new GridLayoutManager(EventAppendActivity.this, 3);
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
                        .start(EventAppendActivity.this, REQUEST_CODE); // 打开相册
            }
        });
        adapter.setDeleteListener(new IntercalationAdapter.OnDeleteImgListener() {
            @Override
            public void onDeleteImg(final int position) {
                DialogCustom dialogCustom = new DialogCustom(EventAppendActivity.this, "是否删除该张图片", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        mList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialogCustom.show();
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

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(EventAppendActivity.this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mEmLvRecodeList.setLayoutManager(manager);
        mExampleAdapter = new ExampleAdapter(this, mRecords);
        mEmLvRecodeList.setAdapter(mExampleAdapter);

        mExampleAdapter.setOnDeleteListener(new ExampleAdapter.OnDeleteListener() {
            @Override
            public void onDelete(final int position) {
                DialogCustom dialogCustom = new DialogCustom(EventAppendActivity.this, "是否删除该条语音", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        Log.e("123", mRecords.get(position).getPath());
                        mRecords.remove(position);
                        mExampleAdapter.notifyDataSetChanged();
                    }
                });
                dialogCustom.show();
            }
        });

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
                        Toast.makeText(EventAppendActivity.this, "请授权,否则无法录音", Toast.LENGTH_SHORT).show();
                    }
                }, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    //直接把参数交给mHelper就行了
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @OnClick({R.id.activity_event_append_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_event_append_rl_back:
                finish();
                break;
        }
    }

    @Override
    protected void onPause() {
        MediaManager.release();//保证在退出该页面时，终止语音播放
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mLocationClient.stop();
    }

    public DBManager getMgr() {
        return mgr;
    }

    public void setMgr(DBManager mgr) {
        this.mgr = mgr;
    }

}
