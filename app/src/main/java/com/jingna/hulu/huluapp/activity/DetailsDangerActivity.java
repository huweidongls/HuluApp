package com.jingna.hulu.huluapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ActivityDetailsDangerShowAdapter;
import com.jingna.hulu.huluapp.adapter.ActivityLineDangerAdapter;
import com.jingna.hulu.huluapp.adapter.IntercalationAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.dialog.DialogCustom;
import com.jingna.hulu.huluapp.model.BaiduCityModel;
import com.jingna.hulu.huluapp.model.FileUploadByAPPModel;
import com.jingna.hulu.huluapp.model.FileUploadModel;
import com.jingna.hulu.huluapp.model.LineDangerModel;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.jingna.hulu.huluapp.utils.PermissionHelper;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.jingna.hulu.huluapp.utils.WeiboDialogUtils;
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
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class DetailsDangerActivity extends BaseActivity {

    @BindView(R.id.activity_details_danger_rv_pic)
    RecyclerView recyclerView;
    @BindView(R.id.activity_details_danger_rv_pic1)
    RecyclerView recyclerView1;
    @BindView(R.id.activity_details_danger_tv_title)
    TextView tvTitle;
    @BindView(R.id.activity_details_danger_tv_type)
    TextView tvType;
    @BindView(R.id.activity_details_danger_tv_content)
    TextView tvContent;
    @BindView(R.id.activity_details_danger_tv_location)
    TextView tvLocation;
    @BindView(R.id.activity_details_danger_mapview)
    MapView mapView;
    @BindView(R.id.et_upload)
    EditText etUpload;
    @BindView(R.id.tv_upload)
    TextView tvUpload;
    @BindView(R.id.activity_details_danger_rv_pic_show)
    RecyclerView recyclerViewShow;
    @BindView(R.id.activity_details_danger_rv_pic1_show)
    RecyclerView recyclerView1Show;
    @BindView(R.id.activity_details_danger_rl_complete)
    RelativeLayout rlComplete;
    @BindView(R.id.tv_max)
    TextView tvMax;
    @BindView(R.id.tv_max1)
    TextView tvMax1;

    private IntercalationAdapter adapter;
    private List<String> mList;

    private IntercalationAdapter adapter1;
    private List<String> mList1;

    private static final int REQUEST_CODE = 0x00000011;
    private static final int REQUEST_CODE1 = 0x00000012;

    private int id = 0;

    /**
     * 上传的图片url拼接
     */
    private String imgs = "";
    private String imgs1 = "";

    /**
     * 上传的图片数组
     */
    private List<String> uploadImgs;

    private ActivityDetailsDangerShowAdapter showAdapter;
    private List<String> showList;
    private ActivityDetailsDangerShowAdapter showAdapter1;
    private List<String> showList1;

    private BaiduMap mBaiduMap;
    private BitmapDescriptor mCurrentMarker;
    private LocationManager lm;

    private PermissionHelper mHelper;
    private Polyline mPolyline;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    private Dialog dialog;

    private SpImp spImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_danger);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        spImp = new SpImp(DetailsDangerActivity.this);
        ButterKnife.bind(DetailsDangerActivity.this);
        mHelper = new PermissionHelper(this);
        mBaiduMap = mapView.getMap();

        initLocation();
        initData();

    }

    private void initLocation() {

        mHelper.requestPermissions("请授予[定位]，否则无法定位", new PermissionHelper.PermissionListener() {
            @Override
            public void doAfterGrand(String... permission) {

                // 开启定位图层
                mBaiduMap.setMyLocationEnabled(true);
                startLocate();

            }

            @Override
            public void doAfterDenied(String... permission) {
                ToastUtil.showShort(DetailsDangerActivity.this, "请授权,否则无法定位");
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);

    }

    private void initData() {

        uploadImgs = new ArrayList<>();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("pageNum", 0);
        map.put("pageSize", 0);
        Map<String, Integer> map1 = new LinkedHashMap<>();
        map1.put("id", id);
        map.put("platformSolveExt", map1);
        String json = Map2Json.map2json(map);

        ViseHttp.POST("/platformSolve/queryList")
                .setJson(json)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        Log.e("123123", data);
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getString("status").equals("SUCCESS")) {
                                Gson gson = new Gson();
                                LineDangerModel model = gson.fromJson(data, LineDangerModel.class);
                                tvTitle.setText(model.getData().get(0).getLpTitle());
                                tvContent.setText(model.getData().get(0).getLpContent());
                                tvType.setText(model.getData().get(0).getTypeName());

                                String a = model.getData().get(0).getLpCoordinate();
                                String aa = a.substring(1, a.length()-1);
                                String[] aaaa = aa.split(",");
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
//                                                        holder.tvLocation.setText(model.getResult().getFormatted_address());
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

                                if (model.getData().get(0).getIsSolve() == 0) {
                                    etUpload.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    recyclerView1.setVisibility(View.VISIBLE);
                                    rlComplete.setVisibility(View.VISIBLE);
                                    tvMax.setVisibility(View.VISIBLE);
                                    tvMax1.setVisibility(View.VISIBLE);
                                    tvUpload.setVisibility(View.GONE);
                                    recyclerViewShow.setVisibility(View.GONE);
                                    recyclerView1Show.setVisibility(View.GONE);
                                } else if (model.getData().get(0).getIsSolve() == 1) {
                                    etUpload.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.GONE);
                                    recyclerView1.setVisibility(View.GONE);
                                    rlComplete.setVisibility(View.GONE);
                                    tvMax.setVisibility(View.GONE);
                                    tvMax1.setVisibility(View.GONE);
                                    tvUpload.setVisibility(View.VISIBLE);
                                    recyclerViewShow.setVisibility(View.VISIBLE);
                                    recyclerView1Show.setVisibility(View.VISIBLE);
                                    tvUpload.setText(model.getData().get(0).getSolveContent());
                                    showList = new ArrayList<>();
                                    showList1 = new ArrayList<>();
                                    String[] show = model.getData().get(0).getNum2().split(",");
                                    for (int i = 0; i < show.length; i++) {
                                        showList.add(show[i]);
                                    }
                                    GridLayoutManager manager = new GridLayoutManager(DetailsDangerActivity.this, 3);
                                    recyclerViewShow.setLayoutManager(manager);
                                    showAdapter = new ActivityDetailsDangerShowAdapter(showList);
                                    recyclerViewShow.setAdapter(showAdapter);
                                    String[] show1 = model.getData().get(0).getNum3().split(",");
                                    for (int i = 0; i < show1.length; i++) {
                                        showList1.add(show1[i]);
                                    }
                                    GridLayoutManager manager1 = new GridLayoutManager(DetailsDangerActivity.this, 3);
                                    recyclerView1Show.setLayoutManager(manager1);
                                    showAdapter1 = new ActivityDetailsDangerShowAdapter(showList1);
                                    recyclerView1Show.setAdapter(showAdapter1);
                                }

                                //地图显示隐患位置
                                String s = model.getData().get(0).getLpCoordinate().substring(1, model.getData().get(0).getLpCoordinate().length()-1);
                                String[] ss = s.split(",");

                                MyLocationData locData = new MyLocationData.Builder()
//                        .accuracy(location.getRadius())
                                        // 此处设置开发者获取到的方向信息，顺时针0-360
//                        .direction(100)
                                        .latitude(Double.valueOf(ss[1]))
                                        .longitude(Double.valueOf(ss[0]))
                                        .build();

                                // 设置定位数据
                                mBaiduMap.setMyLocationData(locData);

                                // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
                                mCurrentMarker = BitmapDescriptorFactory
                                        .fromResource(R.drawable.danger);
                                MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
                                mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(13));
                                mBaiduMap.setMyLocationConfiguration(config);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

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

    @OnClick({R.id.activity_details_rl_back, R.id.activity_details_danger_rl_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_details_rl_back:
                finish();
                break;
            case R.id.activity_details_danger_rl_complete:
                if (TextUtils.isEmpty(etUpload.getText().toString()) || mList.size() <= 0 || mList1.size() <= 0) {
                    ToastUtil.showShort(DetailsDangerActivity.this, "请完善信息后上报");
                }else if(spImp.getDATAID() == 0){
                    ToastUtil.showShort(DetailsDangerActivity.this, "当前未开始护路,无法上报");
                } else {
                    dialog = WeiboDialogUtils.createLoadingDialog(DetailsDangerActivity.this, "请等待...");
                    onComplete();
                }
                break;
        }
    }

    /**
     * 隐患上报
     */
    private void onComplete() {

        Log.e("123123", "开始上传");

        Observable<List<String>> observable = Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<String>> e) throws Exception {
                final List<String> list = new ArrayList<>();
                final List<String> list1 = new ArrayList<>();
                final List<File> listFiles = new ArrayList<>();
                final List<File> listFiles1 = new ArrayList<>();
                Luban.with(DetailsDangerActivity.this)
                        .load(mList)
                        .ignoreBy(100)
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            }

                            @Override
                            public void onSuccess(File file) {
                                // TODO 压缩成功后调用，返回压缩后的图片文件
                                listFiles.add(file);
                                if (listFiles.size() == mList.size()) {
                                    Map<String, File> fileMap = new LinkedHashMap<>();
                                    for (int i = 0; i < listFiles.size(); i++) {
                                        fileMap.put("file" + i, listFiles.get(i));
                                    }

                                    ViseHttp.UPLOAD("/bannerApi/fileUploadByAPP")
                                            .addHeader("Content-Type", "multipart/form-data")
                                            .addFiles(fileMap)
                                            .request(new ACallback<String>() {
                                                @Override
                                                public void onSuccess(String data) {
                                                    Log.e("123123", data);
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(data);
                                                        if (jsonObject.getString("status").equals("SUCCESS")) {
                                                            Gson gson = new Gson();
                                                            FileUploadByAPPModel model = gson.fromJson(data, FileUploadByAPPModel.class);
                                                            list.addAll(model.getData());
                                                            for (int i = 0; i < list.size(); i++) {
                                                                if (i == list.size() - 1) {
                                                                    imgs = imgs + list.get(i);
                                                                } else {
                                                                    imgs = imgs + list.get(i) + ",";
                                                                }
                                                            }
                                                            Log.e("123123", imgs);
                                                            uploadImgs.add(imgs);
                                                            Luban.with(DetailsDangerActivity.this)
                                                                    .load(mList1)
                                                                    .ignoreBy(100)
                                                                    .filter(new CompressionPredicate() {
                                                                        @Override
                                                                        public boolean apply(String path) {
                                                                            return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                                                                        }
                                                                    })
                                                                    .setCompressListener(new OnCompressListener() {
                                                                        @Override
                                                                        public void onStart() {

                                                                        }

                                                                        @Override
                                                                        public void onSuccess(File file) {
                                                                            listFiles1.add(file);
                                                                            if (listFiles1.size() == mList1.size()) {
                                                                                Map<String, File> fileMap = new LinkedHashMap<>();
                                                                                for (int i = 0; i < listFiles1.size(); i++) {
                                                                                    fileMap.put("file" + i, listFiles1.get(i));
                                                                                }

                                                                                ViseHttp.UPLOAD("/bannerApi/fileUploadByAPP")
                                                                                        .addHeader("Content-Type", "multipart/form-data")
                                                                                        .addFiles(fileMap)
                                                                                        .request(new ACallback<String>() {
                                                                                            @Override
                                                                                            public void onSuccess(String data) {
                                                                                                Log.e("123123", data);
                                                                                                try {
                                                                                                    JSONObject jsonObject = new JSONObject(data);
                                                                                                    if (jsonObject.getString("status").equals("SUCCESS")) {
                                                                                                        Gson gson = new Gson();
                                                                                                        FileUploadByAPPModel model = gson.fromJson(data, FileUploadByAPPModel.class);
                                                                                                        list1.addAll(model.getData());
                                                                                                        for (int i = 0; i < list1.size(); i++) {
                                                                                                            if (i == list1.size() - 1) {
                                                                                                                imgs1 = imgs1 + list1.get(i);
                                                                                                            } else {
                                                                                                                imgs1 = imgs1 + list1.get(i) + ",";
                                                                                                            }
                                                                                                        }
                                                                                                        Log.e("123123", imgs1);
                                                                                                        uploadImgs.add(imgs1);
                                                                                                        e.onNext(uploadImgs);
                                                                                                    }
                                                                                                } catch (JSONException e) {
                                                                                                    e.printStackTrace();
                                                                                                }
                                                                                            }

                                                                                            @Override
                                                                                            public void onFail(int errCode, String errMsg) {
                                                                                                Log.e("123123", errMsg);
                                                                                                WeiboDialogUtils.closeDialog(dialog);
                                                                                            }
                                                                                        });
                                                                            }
                                                                        }

                                                                        @Override
                                                                        public void onError(Throwable e) {

                                                                        }
                                                                    }).launch();
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                @Override
                                                public void onFail(int errCode, String errMsg) {
                                                    Log.e("123123", errMsg);
                                                    WeiboDialogUtils.closeDialog(dialog);
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                            }
                        }).launch();
            }
        });
        Observer<List<String>> observer = new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> value) {

                String imgs = value.get(0);
                String imgs1 = value.get(1);
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("id", id);
                map.put("isSolve", 2);
                map.put("solveContent", etUpload.getText().toString());
                map.put("num2", imgs);
                map.put("num3", imgs1);
                map.put("num5", spImp.getDATAID());
                map.put("lpUser", spImp.getUID());
                String json = Map2Json.map2json(map);

                ViseHttp.POST("/platformSolve/toUpdate")
                        .setJson(json)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.getString("status").equals("SUCCESS")) {
                                        ToastUtil.showShort(DetailsDangerActivity.this, "上报成功");
                                        finish();
                                    } else {
                                        ToastUtil.showShort(DetailsDangerActivity.this, jsonObject.optString("errorMsg"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                WeiboDialogUtils.closeDialog(dialog);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                WeiboDialogUtils.closeDialog(dialog);
                            }
                        });

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
//        mLocationClient.stop();
    }

    /**
     * 定位
     */
    private void startLocate() {
        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 5000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        //开启定位
        mLocationClient.start();
    }

    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // 构造定位数据
//            MyLocationData locData = new MyLocationData.Builder()
////                        .accuracy(location.getRadius())
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
////                        .direction(100)
//                    .latitude(location.getLatitude())
//                    .longitude(location.getLongitude())
//                    .build();
//
//            // 设置定位数据
//            mBaiduMap.setMyLocationData(locData);
//
//            // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//            mCurrentMarker = BitmapDescriptorFactory
//                    .fromResource(R.drawable.location);
//            MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, mCurrentMarker);
//            mBaiduMap.setMyLocationConfiguration(config);
        }
    }

}
