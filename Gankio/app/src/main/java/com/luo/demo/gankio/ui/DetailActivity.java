package com.luo.demo.gankio.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.bean.Android;

public class DetailActivity extends AppCompatActivity {

    public final static String LOAD_URL = "load_url";
    public static final String MODEL = "model";
    private String mUrl;
    private Android.ResultsBean mResultsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initData();
        initView();
        initWebView();

    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        mToolbar.setTitle(mResultsBean.getDesc());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initWebView() {
        WebView mWebView = (WebView) findViewById(R.id.detail_webview);
        //声明WebSettings子类
        WebSettings webSettings = mWebView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);


        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(true); //隐藏原生的缩放控件

        mWebView.loadUrl(mResultsBean.getUrl());
    }

    private void initData() {
        mResultsBean = (Android.ResultsBean) getIntent().getSerializableExtra(MODEL);
        mUrl = getIntent().getStringExtra(LOAD_URL);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
