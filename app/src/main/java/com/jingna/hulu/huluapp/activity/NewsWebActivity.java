package com.jingna.hulu.huluapp.activity;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jingna.hulu.huluapp.R;
import com.jingna.hulu.huluapp.base.BaseActivity;
import com.jingna.hulu.huluapp.dialog.DialogCustom;
import com.jingna.hulu.huluapp.utils.ToastUtil;
import com.jingna.hulu.huluapp.wxapi.OnResponseListener;
import com.jingna.hulu.huluapp.wxapi.WXShare;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsWebActivity extends BaseActivity {

    @BindView(R.id.activity_news_web_webview)
    WebView webView;

    private WXShare wxShare;

    private String url;
    private String title;
    private String subtitle;
    private String pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);

        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(NewsWebActivity.this);

        initListener();
        initWebView();

    }

    private void initListener() {

        wxShare = new WXShare(this);
        wxShare.setListener(new OnResponseListener() {
            @Override
            public void onSuccess() {
                ToastUtil.showShort(NewsWebActivity.this, "分享成功");
            }

            @Override
            public void onCancel() {
                ToastUtil.showShort(NewsWebActivity.this, "取消分享");
            }

            @Override
            public void onFail(String message) {
                ToastUtil.showShort(NewsWebActivity.this, "分享失败");
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        wxShare.register();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wxShare.unregister();
    }

    public void initWebView(){

        Intent intent = getIntent();
        String newid = intent.getIntExtra("newid", 0)+"";
        title = intent.getStringExtra("title");
        subtitle = intent.getStringExtra("subtitle");
        pic = intent.getStringExtra("pic");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        }
        url = "http://hl.5ijiaoyu.cn/hulu_h5/news.html?newsId="+newid;
        webView.loadUrl(url);
    }

    @OnClick({R.id.activity_news_web_rl_back, R.id.activity_news_web_rl_share})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_news_web_rl_back:
                finish();
                break;
            case R.id.activity_news_web_rl_share:
                //微信分享
                DialogCustom dialogCustom = new DialogCustom(NewsWebActivity.this, "“护路”想要打开“微信”", new DialogCustom.OnYesListener() {
                    @Override
                    public void onYes() {
                        wxShare.shareUrl(url, title, subtitle, pic);
                    }
                });
                dialogCustom.show();
                break;
        }
    }

}
