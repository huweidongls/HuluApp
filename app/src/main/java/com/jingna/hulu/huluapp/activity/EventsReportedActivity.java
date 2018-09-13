package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.IntercalationAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
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

    private IntercalationAdapter adapter;
    private List<String> mList;

    private static final int REQUEST_CODE = 0x00000011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_reported);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventsReportedActivity.this);

        initData();
        initSpinner();

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

}
