package com.luo.demo.gankio.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.api.Api;
import com.luo.demo.gankio.api.CallBack;
import com.luo.demo.gankio.base.BaseActivity;
import com.luo.demo.gankio.bean.ResultsBean;
import com.luo.demo.gankio.bean.Welfare;
import com.luo.demo.gankio.loadmore.LoadMoreRvAdapter;
import com.luo.demo.gankio.loadmore.OnRecyclerViewScrollListener;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class WelfareActivity extends BaseActivity implements LoadMoreRvAdapter.OnErrorClickListener {

    private List<ResultsBean> mDatas = new ArrayList<>();
    private Handler mHandler = new Handler();
    private LoadMoreRvAdapter mAdapter;
    private OnRecyclerViewScrollListener<ResultsBean> mListener;

    private int mCurrentPage;
    private int mPageCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);

        initView();
        initData();
    }

    private void initData() {
        mPageCount = 10;    // 个数
        mCurrentPage = 1;   // 页数

        Api.getInstance().getWelfare(mPageCount, mCurrentPage, new CallBack<Welfare>() {
            @Override
            public void onFinish(boolean isSuccess, Welfare bean, String error) {
                if (isSuccess) {
                    mDatas = bean.getResults();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.addAll(mDatas);
                            mAdapter.notifyDataSetChanged();
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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.welfare_rv);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mListener = new OnRecyclerViewScrollListener<ResultsBean>() {

            @Override
            public void onPrepare() {
                KLog.d("OnRecyclerViewScrollListener onPrepare " + mDatas.size());
                mAdapter.setFooterView();
            }

            @Override
            public void onLoadMore() { // 加载更多
                LoadMore();
            }
        };

        recyclerView.addOnScrollListener(mListener);
        mAdapter = new LoadMoreRvAdapter(WelfareActivity.this, mDatas);
        mAdapter.setOnErrorClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadMore() {
        mCurrentPage = mCurrentPage + 1;
        Api.getInstance().getWelfare(mPageCount, mCurrentPage, new CallBack<Welfare>() {
            @Override
            public void onFinish(final boolean isSuccess, final Welfare bean, String error) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess) {
                            mListener.setLoadingMore(false);
                            mAdapter.setFooterView();
                            mAdapter.addAll(bean.getResults());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            mCurrentPage = mCurrentPage - 1;
                            mAdapter.showError();
                        }
                    }
                }, 2000);
            }
        });
    }

    // 重新加载
    @Override
    public void onReload() {
        LoadMore();
    }
}
