package com.luo.demo.gankio.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.adapter.WelfareAdapter;
import com.luo.demo.gankio.api.Api;
import com.luo.demo.gankio.api.CallBack;
import com.luo.demo.gankio.base.BaseActivity;
import com.luo.demo.gankio.bean.ResultsBean;
import com.luo.demo.gankio.bean.Welfare;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class WelfareActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<ResultsBean> mDatas = new ArrayList<>();
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);

        initView();
        initData();
    }

    private void initData() {

        Api.getInstance().getWelfare(10, 1, new CallBack<Welfare>() {
            @Override
            public void onFinish(boolean isSuccess, Welfare bean, String error) {
                if (isSuccess) {
                    mDatas = bean.getResults();
                    for (int i = 0; i < mDatas.size(); i++) {
                        KLog.d("item : " + mDatas.get(i));
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            WelfareAdapter mAdapter = new WelfareAdapter(WelfareActivity.this, mDatas);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    });
                }
            }
        });
    }


    private void initView() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.welfare_toolbar);
        mToolbar.setTitle("福利");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.welfare_rv);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
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
