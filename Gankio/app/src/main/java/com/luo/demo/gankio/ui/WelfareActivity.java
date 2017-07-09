package com.luo.demo.gankio.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.luo.demo.gankio.util.TimeUtils;
import com.socks.library.KLog;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class WelfareActivity extends BaseActivity implements LoadMoreRvAdapter.OnErrorClickListener, SwipeRefreshLayout.OnRefreshListener {

    private List<ResultsBean> mDatas = new ArrayList<>();
    private Handler mHandler = new Handler();
    private LoadMoreRvAdapter mAdapter;
    private OnRecyclerViewScrollListener<ResultsBean> mListener;

    private int mCurrentPage;
    private int mPageCount = 10;
    private int mOffsetCount;
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);

        initView();
        getData();
    }

    private void getData() {
        if (mListener.isLoadingMore()) {
            mRefreshLayout.setRefreshing(false);
            return;
        }
        mRefreshLayout.setRefreshing(true);
        mCurrentPage = 1;   // 页数
        mOffsetCount = mPageCount;
        Api.getInstance().getWelfare(mPageCount, mCurrentPage, new CallBack<Welfare>() {
            @Override
            public void onFinish(final boolean isSuccess, final Welfare bean, String error) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess) {
                            mDatas = bean.getResults();
                            saveAndformat(bean);
                            mAdapter.setDatas(mDatas);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            mDatas = DataSupport.where("flag=?", "welfare").order("createdAt desc").limit(10).find(ResultsBean.class);
                            if (mDatas.isEmpty()) {
                                Snackbar.make(mRefreshLayout, getResources().
                                                getString(R.string.fragment_android_data_fail),
                                        Snackbar.LENGTH_LONG).show();
                            } else {
                                mAdapter.setDatas(mDatas);
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                        mRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void initView() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.welfare_swip);
        mRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green);
        mRefreshLayout.setOnRefreshListener(this);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.welfare_toolbar);
        mToolbar.setTitle("福利");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.welfare_rv);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mListener = new OnRecyclerViewScrollListener<ResultsBean>() {

            @Override
            public void onPrepare() {
                if (mRefreshLayout.isRefreshing()) {
                    mListener.setLoadingMore(false);
                    return;
                }
                KLog.d("OnRecyclerViewScrollListener onPrepare " + mDatas.size());
                mAdapter.setFooterView();
                mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount());
            }

            @Override
            public void onLoadMore() { // 加载更多
                LoadMore();
            }
        };

        mRecyclerView.addOnScrollListener(mListener);
        mAdapter = new LoadMoreRvAdapter(WelfareActivity.this, mDatas);
        mAdapter.setOnErrorClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
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
        if (mRefreshLayout.isRefreshing()) {
            return;
        }

        mCurrentPage = mCurrentPage + 1;
        Api.getInstance().getWelfare(mPageCount, mCurrentPage, new CallBack<Welfare>() {
            @Override
            public void onFinish(final boolean isSuccess, final Welfare bean, String error) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess) {
                            saveAndformat(bean);
                            mListener.setLoadingMore(false);
                            mAdapter.setFooterView();
                            mAdapter.addAll(bean.getResults());
                            mAdapter.notifyDataSetChanged();
                        } else {
                            // 获取缓存
                            List<ResultsBean> mList = DataSupport.where("flag=?", "welfare").order("createdAt desc")
                                    .limit(mPageCount).offset(mOffsetCount).find(ResultsBean.class);
                            if (mList.isEmpty()) {// 获取缓存失败
                                Snackbar.make(mRefreshLayout, getResources().
                                                getString(R.string.fragment_android_no_data),
                                        Snackbar.LENGTH_SHORT).show();
                                mAdapter.showError();

                            } else {
                                mOffsetCount = mOffsetCount + 10;
                                mAdapter.addAll(mList);
                                mAdapter.setFooterView();
                                mListener.setLoadingMore(false);
                                mAdapter.notifyDataSetChanged();
                            }

                            mCurrentPage = mCurrentPage - 1;
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

    @Override
    public void onRefresh() {
        getData();
    }

    /**
     * 缓存数据及格式化时间
     *
     * @param bean
     */
    private void saveAndformat(Welfare bean) {
        // 格式化时间
        for (int i = 0; i < bean.getResults().size(); i++) {
            String time = TimeUtils.getFormatDate(bean.getResults().get(i).getCreatedAt());
            bean.getResults().get(i).setCreatedAt(time);
            bean.getResults().get(i).setFlag("welfare");
        }

        // 如果存在则修改，否则插入 -- 根据唯一的url
        for (int i = 0; i < bean.getResults().size(); i++) {
            bean.getResults().get(i).saveOrUpdate("url=?", bean.getResults().get(i).getUrl());
        }
    }
}
