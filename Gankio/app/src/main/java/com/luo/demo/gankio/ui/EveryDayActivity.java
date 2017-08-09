package com.luo.demo.gankio.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.adapter.EDAdapter;
import com.luo.demo.gankio.api.Api;
import com.luo.demo.gankio.api.CallBack;
import com.luo.demo.gankio.base.BaseActivity;
import com.luo.demo.gankio.bean.EveryDay;
import com.luo.demo.gankio.bean.History;
import com.luo.demo.gankio.util.TimeUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class EveryDayActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;
    private EDAdapter mEdAdapter;
    private Handler mHandler = new Handler();
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);
        initView();
        getData();

    }

    private List<EveryDay> mData;

    private void getData() {
        mData = new ArrayList<>();

        Api.getInstance().getHistory(10, 1, new CallBack<History>() {
            @Override
            public void onFinish(boolean isSuccess, History bean, String error) {
                if (isSuccess) {

                    History b = bean;
                    EveryDay ed;
                    for (int i = 0; i < b.getResults().size(); i++) {

                        String content = b.getResults().get(i).getContent();
                        int indexOf = content.indexOf("http");
                        int lastOf = content.indexOf(".jpg");
                        String img = content.substring(indexOf, lastOf + 4);

                        ed = new EveryDay();
                        ed.setImg(img);
                        ed.setB(b.getResults().get(i));

                        KLog.d(b.getResults().get(i).getCreated_at());
                        KLog.d(TimeUtils.getFormatDate(b.getResults().get(i).getCreated_at()));
                        mData.add(ed);
                    }
                    mEdAdapter = new EDAdapter(EveryDayActivity.this, mData);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mRecyclerView.setAdapter(mEdAdapter);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    private void initView() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.ed_swip);
        mRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green);
        mRefreshLayout.setOnRefreshListener(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.ed_toolbar);
        mToolbar.setTitle("每日数据");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.ed_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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
