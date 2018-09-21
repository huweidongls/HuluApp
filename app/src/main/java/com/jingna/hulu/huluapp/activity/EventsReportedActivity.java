package com.jingna.hulu.huluapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.adapter.ExampleAdapter;
import com.jingna.hulu.huluapp.adapter.IntercalationAdapter;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.dialog.DialogCustom;
import com.jingna.hulu.huluapp.history.DBManager;
import com.jingna.hulu.huluapp.manager.AudioRecordButton;
import com.jingna.hulu.huluapp.manager.MediaManager;
import com.jingna.hulu.huluapp.model.BaiduCityModel;
import com.jingna.hulu.huluapp.model.FileUploadModel;
import com.jingna.hulu.huluapp.model.QueryListModel;
import com.jingna.hulu.huluapp.sp.SpImp;
import com.jingna.hulu.huluapp.utils.Map2Json;
import com.jingna.hulu.huluapp.utils.PermissionHelper;
import com.jingna.hulu.huluapp.utils.Record;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.vise.xsnow.cache.SpCache;
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

public class EventsReportedActivity extends BaseActivity {

    @BindView(R.id.activity_events_reported_rv_pic)
    RecyclerView rvPic;
    @BindView(R.id.activity_events_reported_sp)
    Spinner spinner;
    @BindView(R.id.em_tv_btn)
    AudioRecordButton mEmTvBtn;
    @BindView(R.id.em_lv_recodeList)
    RecyclerView mEmLvRecodeList;
    @BindView(R.id.activity_events_reported_tv_location)
    TextView tvLocation;
    @BindView(R.id.activity_events_reported_et_title)
    EditText etTitle;
    @BindView(R.id.activity_events_reported_et_content)
    EditText etContent;

    private IntercalationAdapter adapter;
    private List<String> mList;

    List<Record> mRecords;
    ExampleAdapter mExampleAdapter;
    PermissionHelper mHelper;
    //db
    private DBManager mgr;

    private static final int REQUEST_CODE = 0x00000011;

    private LocationManager locationManager;
    private double latitude = 0.0;//纬度
    private double longitude = 0.0;//经度
    private String latLongString = "";

    /**
     * 事件分类列表
     */
    private String[] spinnerItems;
    private int[] num1;
    private int numid;

    /**
     * 上传的图片url拼接
     */
    private String imgs = "";

    private SpImp spImp;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_reported);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(EventsReportedActivity.this);
        spImp = new SpImp(EventsReportedActivity.this);
        mHelper = new PermissionHelper(this);

        initData();
        initSpinner();
        initAdapter();
        initListener();

    }

    private void initSpinner() {

        ViseHttp.POST("systemAssortmentApi/queryList")
                .setJson("{\"systemAssortmentExt\": {\"assortmentType\": 1}}")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.getString("status").equals("SUCCESS")) {
                                Gson gson = new Gson();
                                QueryListModel listModel = gson.fromJson(data, QueryListModel.class);
                                spinnerItems = new String[listModel.getData().size()];
                                num1 = new int[listModel.getData().size()];
                                for (int i = 0; i < listModel.getData().size(); i++) {
                                    spinnerItems[i] = listModel.getData().get(i).getTypeName();
                                    num1[i] = listModel.getData().get(i).getId();
                                }
                                numid = num1[0];
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
                                        numid = num1[pos];
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {
                                        // Another interface callback
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

    private void initData() {

        getLocation();

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
        adapter.setDeleteListener(new IntercalationAdapter.OnDeleteImgListener() {
            @Override
            public void onDeleteImg(final int position) {
                DialogCustom dialogCustom = new DialogCustom(EventsReportedActivity.this, "是否删除该张图片", new DialogCustom.OnYesListener() {
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

    @OnClick({R.id.activity_events_reported_rl_back, R.id.iv_location_b, R.id.activity_events_reported_rl_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_events_reported_rl_back:
                finish();
                break;
            case R.id.iv_location_b:
                getLocation();
                break;
            case R.id.activity_events_reported_rl_complete:
                if (TextUtils.isEmpty(etTitle.getText().toString()) || TextUtils.isEmpty(etContent.getText().toString()) || mList.size() <= 0) {
                    ToastUtil.showShort(EventsReportedActivity.this, "请完善信息后上报");
                } else {
//                    toUpdata();
                    File file = new File(mRecords.get(0).getPath());
                    ViseHttp.UPLOAD("/bannerApi/fileUploadSound")
                            .addHeader("Content-Type", "multipart/form-data")
                            .addFile("Sound", file)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    Log.e("123123", data);
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if (jsonObject.getString("status").equals("SUCCESS")) {
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
                break;
        }
    }

    /**
     * 上传
     */
    private void toUpdata() {

        Log.e("123123", "开始上传");

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                final List<String> list = new ArrayList<>();
                final List<File> listFile = new ArrayList<>();
                Luban.with(EventsReportedActivity.this)
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

                                listFile.add(file);

                                Log.e("123123", file.getName());

                                if (listFile.size() == mList.size()) {
                                    Map<String, File> fileMap = new LinkedHashMap<>();
                                    for (int i = 0; i < listFile.size(); i++) {
                                        fileMap.put("file"+i, listFile.get(i));
                                    }

                                    ViseHttp.UPLOAD("/bannerApi/fileUploadForList")
                                            .addHeader("Content-Type", "multipart/form-data")
                                            .addFiles(fileMap)
                                            .request(new ACallback<String>() {
                                                @Override
                                                public void onSuccess(String data) {
                                                    Log.e("123123", data);
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(data);
                                                        if (jsonObject.getString("status").equals("SUCCESS")) {
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

//                                ViseHttp.UPLOAD("bannerApi/fileUpload")
//                                        .addHeader("Content-Type", "multipart/form-data")
//                                        .addFile("file", file)
//                                        .request(new ACallback<String>() {
//                                            @Override
//                                            public void onSuccess(String data) {
//                                                Log.e("123123", data);
//                                                try {
//                                                    JSONObject jsonObject = new JSONObject(data);
//                                                    if(jsonObject.getString("status").equals("SUCCESS")){
//                                                        Gson gson = new Gson();
//                                                        FileUploadModel uploadModel = gson.fromJson(data, FileUploadModel.class);
//                                                        list.add(uploadModel.getData());
//                                                        if(list.size() == mList.size()){
//                                                            for (int i = 0; i<list.size(); i++){
//                                                                if(i == list.size() - 1){
//                                                                    imgs = imgs + list.get(i);
//                                                                }else {
//                                                                    imgs = imgs + list.get(i) + ",";
//                                                                }
//                                                            }
//                                                            Log.e("123123", imgs);
//                                                            e.onNext(imgs);
//                                                        }
//                                                    }
//                                                } catch (JSONException e) {
//                                                    e.printStackTrace();
//                                                }
//                                            }
//
//                                            @Override
//                                            public void onFail(int errCode, String errMsg) {
//                                                Log.e("123123", errMsg);
//                                            }
//                                        });
                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                            }
                        }).launch();
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

                List<List<String>> locaList = new ArrayList<>();
                List<String> locaList1 = new ArrayList<>();
                locaList1.add(longitude + "," + latitude);
                locaList.add(locaList1);

                Map<String, Object> map = new LinkedHashMap<>();
                map.put("createBy", spImp.getUID());
                map.put("num3", spImp.getNAME());
                map.put("num1", numid);
                map.put("eventTitle", etTitle.getText().toString());
                map.put("eventContent", etContent.getText().toString());
                map.put("num2", locaList + "");
                map.put("eventPic", value);
                String json = Map2Json.map2json(map);
                Log.e("123123", json);

                ViseHttp.POST("/eventApi/toUpdate")
                        .setJson(json)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                Log.e("123123", data);
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if (jsonObject.getString("status").equals("SUCCESS")) {
                                        ToastUtil.showShort(EventsReportedActivity.this, "上报成功");
                                        finish();
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

        mExampleAdapter.setOnDeleteListener(new ExampleAdapter.OnDeleteListener() {
            @Override
            public void onDelete(final int position) {
                DialogCustom dialogCustom = new DialogCustom(EventsReportedActivity.this, "是否删除该条语音", new DialogCustom.OnYesListener() {
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

    /**
     * 获取定位
     */
    private void getLocation() {
        mHelper.requestPermissions("请授予[定位]，否则无法定位", new PermissionHelper.PermissionListener() {
            @Override
            public void doAfterGrand(String... permission) {
//                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                @SuppressLint("MissingPermission")
//                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                if (location != null) {
//                    latitude = location.getLatitude(); // 纬度
//                    longitude = location.getLongitude(); // 经度
//                    try {
//                        // 去谷歌的地理位置获取中去解析经纬度对应的地理位置
////            String url = "http://maps.google.cn/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&sensor=true&language=zh-CN";
//                        String url = "http://api.map.baidu.com/geocoder?output=json&location=" + latitude + "," + longitude + "&key=8dDPAEEMwPNZgxg4YhNUXqWoV8GNItO1";
//
//                        ViseHttp.GET(url)
//                                .request(new ACallback<String>() {
//                                    @Override
//                                    public void onSuccess(String data) {
//                                        try {
//                                            JSONObject jsonObject = new JSONObject(data);
//                                            if (jsonObject.getString("status").equals("OK")) {
//                                                Gson gson = new Gson();
//                                                BaiduCityModel model = gson.fromJson(data, BaiduCityModel.class);
//                                                latLongString = model.getResult().getFormatted_address();
//                                                Log.e("123123", latLongString);
//                                                tvLocation.setText(latLongString);
//                                            }
//                                        } catch (JSONException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onFail(int errCode, String errMsg) {
//
//                                    }
//                                });
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                startLocate();
            }

            @Override
            public void doAfterDenied(String... permission) {
                ToastUtil.showShort(EventsReportedActivity.this, "请授权,否则无法定位");
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);
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
        int span = 10000;
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
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            String url = "http://api.map.baidu.com/geocoder?output=json&location=" + latitude + "," + longitude + "&key=ovbH9tDk74DcpRTv59n1zEOkRrmdSPf2";

//            Log.e("123123", location.getAddrStr());

            ViseHttp.GET(url)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if (jsonObject.getString("status").equals("OK")) {
                                    Gson gson = new Gson();
                                    BaiduCityModel model = gson.fromJson(data, BaiduCityModel.class);
                                    latLongString = model.getResult().getFormatted_address();
//                                    Log.e("123123", latLongString);
                                    tvLocation.setText(latLongString);
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

}
