package com.luo.demo.gankio.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.luo.demo.gankio.R;

public class EdDetailActivity extends AppCompatActivity {

    public static String EDTAIL_KEY = "key";
    private String VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ed_detail);

        VALUE = getIntent().getStringExtra(EDTAIL_KEY);

        WebView mWebView = (WebView) findViewById(R.id.eddetail_webview);
        //声明WebSettings子类
        WebSettings webSettings = mWebView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        webSettings.setDomStorageEnabled(true);    //开启DOM形式存储
        webSettings.setDatabaseEnabled(true);   //开启数据库形式存储
        String appCacheDir = getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();   //缓存数据的存储地址
        webSettings.setAppCachePath(appCacheDir);
        webSettings.setAppCacheEnabled(true);  //开启缓存功能
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);      //缓存模式
        webSettings.setAllowFileAccess(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(true); //隐藏原生的缩放控件
        mWebView.loadDataWithBaseURL(null, VALUE, "text/html" , "utf-8", null);
    }
}
