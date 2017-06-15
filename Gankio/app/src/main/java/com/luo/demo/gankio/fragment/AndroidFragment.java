package com.luo.demo.gankio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.adapter.AndroidRvAdapter;
import com.luo.demo.gankio.api.Api;
import com.luo.demo.gankio.api.CallBack;
import com.luo.demo.gankio.base.BaseFragment;
import com.luo.demo.gankio.bean.Android;
import com.luo.demo.gankio.bean.ResultsBean;
import com.luo.demo.gankio.listener.LoadMoreScrollListener;
import com.luo.demo.gankio.util.TimeUtils;
import com.luo.demo.gankio.view.LoadingLayout;
import com.socks.library.KLog;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 包名:  com.luo.demo.gankio.fragment
 * 作者:  Mr.Luo
 * 时间:  2017/5/22 14:17
 * 描述:  bean:android
 * 联系:  175262808@qq.com
 */

public class AndroidFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, LoadMoreScrollListener.LoadMoreListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private int mCurrentPage;
    private int mPageCount;
    private List<ResultsBean> mData;
    private AndroidRvAdapter mRvAdapter;
    private int mOffsetCount;
    private LoadingLayout mLoadingLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mRootView != null) {
            return mRootView;
        }
        mRootView = inflater.inflate(R.layout.fragment_android, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.android_recyclerview);
        mLoadingLayout = (LoadingLayout) mRootView.findViewById(R.id.android_loadinglayout);
        mLoadingLayout.setOnRetryClickListener(this);
        mLoadingLayout.showLoading();
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.android_swiper);
        LoadMoreScrollListener listener = new LoadMoreScrollListener(this);
        mRecyclerView.addOnScrollListener(listener);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setEnabled(false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KLog.d("android onActivityCreated");
        getData();
    }

    private void getData() {
        mPageCount = 10;    // 个数
        mOffsetCount = mPageCount;
        mCurrentPage = 1;   // 页数

        Api.getInstance().getAndroid(mPageCount, mCurrentPage, new CallBack<Android>() {
            @Override
            public void onFinish(final boolean isSuccess, final Android bean, final String error) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess) {
                            saveAndformat(bean);
                            mData = bean.getResults();
                            LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRvAdapter = new AndroidRvAdapter(mActivity, mData);
                            mRecyclerView.setAdapter(mRvAdapter);
                        } else {
                            mData = DataSupport.where("flag=?", "android").order("createdAt desc").limit(10).find(ResultsBean.class);
                            if (mData.isEmpty()) {
                                if (isAdded()) {
                                    Snackbar.make(mRecyclerView, getResources().
                                                    getString(R.string.fragment_android_data_fail),
                                            Snackbar.LENGTH_LONG).show();
                                }
                                mLoadingLayout.showError();
                                mSwipeRefreshLayout.setRefreshing(false);
                                return;
                            } else {
                                LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                mRecyclerView.setLayoutManager(layoutManager);
                                mRvAdapter = new AndroidRvAdapter(mActivity, mData);
                                mRecyclerView.setAdapter(mRvAdapter);
                            }
                        }
                        mSwipeRefreshLayout.setEnabled(true);
                        mSwipeRefreshLayout.setRefreshing(false);
                        mLoadingLayout.showContent();
                    }
                }, 1500);

            }
        });
    }

    // 刷新
    @Override
    public void onRefresh() {
        getData();
    }

    // 加载更多
    @Override
    public void onLoadMore() {
        mCurrentPage = mCurrentPage + 1;

        if (mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        mSwipeRefreshLayout.setRefreshing(true);

        Api.getInstance().getAndroid(mPageCount, mCurrentPage, new CallBack<Android>() {
            @Override
            public void onFinish(final boolean isSuccess, final Android bean, String error) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isSuccess) {
                            // 成功
                            saveAndformat(bean);
                            mSwipeRefreshLayout.setRefreshing(false);
                            if (mData.isEmpty()) {
                                mData = bean.getResults();
                                LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                mRecyclerView.setLayoutManager(layoutManager);
                                mRvAdapter = new AndroidRvAdapter(mActivity, mData);
                                mRecyclerView.setAdapter(mRvAdapter);
                            } else {
                                mData.addAll(bean.getResults());
                                mRvAdapter.notifyDataSetChanged();
                            }
                        } else {
                            mSwipeRefreshLayout.setRefreshing(false);

                            if (mData.isEmpty()) {
                                return;
                            }
                            List<ResultsBean> mList = DataSupport.where("flag=?", "android").order("createdAt desc")
                                    .limit(mPageCount).offset(mOffsetCount).find(ResultsBean.class);
                            if (mList.isEmpty()) {
                                Snackbar.make(mRecyclerView, getResources().
                                                getString(R.string.fragment_android_no_data),
                                        Snackbar.LENGTH_LONG).show();
                                return;
                            }
                            mOffsetCount = mOffsetCount + 10;
                            mData.addAll(mList);
                            mRvAdapter.notifyDataSetChanged();
                        }
                    }

                });
            }
        });
    }

    /**
     * 缓存数据及格式化时间
     *
     * @param bean
     */
    private void saveAndformat(Android bean) {
        // 格式化时间
        for (int i = 0; i < bean.getResults().size(); i++) {
            String time = TimeUtils.getFormatDate(bean.getResults().get(i).getCreatedAt());
            bean.getResults().get(i).setCreatedAt(time);
            bean.getResults().get(i).setFlag("android");
        }

        // 如果存在则修改，否则插入 -- 根据唯一的url
        for (int i = 0; i < bean.getResults().size(); i++) {
            bean.getResults().get(i).saveOrUpdate("url=?", bean.getResults().get(i).getUrl());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_error_retry:
                mLoadingLayout.showLoading();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 2000);
                break;
        }
    }

}
