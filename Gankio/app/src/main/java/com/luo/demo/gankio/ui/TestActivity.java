package com.luo.demo.gankio.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.view.LoadingLayout;
import com.luo.demo.gankio.widget.MyViewPager;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private MyViewPager mVp;
    private LoadingLayout mLoadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mLoadingLayout = (LoadingLayout) findViewById(R.id.loading_layout);

        mLoadingLayout.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingLayout.showLoading();
            }
        });
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                mLoadingLayout.showContent();
                break;
            case R.id.btn2:
                mLoadingLayout.showError();
                break;
            case R.id.btn3:
                mLoadingLayout.showEmpty();
                break;

            case R.id.btn4:
                mLoadingLayout.showLoading();
                break;
        }
    }
}
