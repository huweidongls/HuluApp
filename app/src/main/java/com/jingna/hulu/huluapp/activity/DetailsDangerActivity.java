package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.IntercalationAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.dialog.DialogCustom;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsDangerActivity extends BaseActivity {

    @BindView(R.id.activity_details_danger_rv_pic)
    RecyclerView recyclerView;
    @BindView(R.id.activity_details_danger_rv_pic1)
    RecyclerView recyclerView1;

    private IntercalationAdapter adapter;
    private List<String> mList;

    private IntercalationAdapter adapter1;
    private List<String> mList1;

    private static final int REQUEST_CODE = 0x00000011;
    private static final int REQUEST_CODE1 = 0x00000012;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_danger);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(DetailsDangerActivity.this);

        initData();

    }

    private void initData() {

        GridLayoutManager manager = new GridLayoutManager(DetailsDangerActivity.this, 3);
        recyclerView.setLayoutManager(manager);
        mList = new ArrayList<>();
        adapter = new IntercalationAdapter(mList);
        recyclerView.setAdapter(adapter);
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
                        .start(DetailsDangerActivity.this, REQUEST_CODE); // 打开相册
            }
        });
        adapter.setDeleteListener(new IntercalationAdapter.OnDeleteImgListener() {
            @Override
            public void onDeleteImg(final int position) {
                DialogCustom dialogCustom = new DialogCustom(DetailsDangerActivity.this, "是否删除该张图片", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        mList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                dialogCustom.show();
            }
        });

        GridLayoutManager manager1 = new GridLayoutManager(DetailsDangerActivity.this, 3);
        recyclerView1.setLayoutManager(manager1);
        mList1 = new ArrayList<>();
        adapter1 = new IntercalationAdapter(mList1);
        recyclerView1.setAdapter(adapter1);
        adapter1.setListener(new IntercalationAdapter.OnAddImgListener() {
            @Override
            public void onAddImg() {
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(9 - mList1.size()) // 图片的最大选择数量，小于等于0时，不限数量。
//                        .setSelected(selected) // 把已选的图片传入默认选中。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(DetailsDangerActivity.this, REQUEST_CODE1); // 打开相册
            }
        });
        adapter1.setDeleteListener(new IntercalationAdapter.OnDeleteImgListener() {
            @Override
            public void onDeleteImg(final int position) {
                DialogCustom dialogCustom = new DialogCustom(DetailsDangerActivity.this, "是否删除该张图片", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        mList1.remove(position);
                        adapter1.notifyDataSetChanged();
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
        if (requestCode == REQUEST_CODE1 && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
            mList1.addAll(images);
            adapter1.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.activity_details_rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_details_rl_back:
                finish();
                break;
        }
    }

}
