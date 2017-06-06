package com.luo.demo.gankio.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import com.luo.demo.gankio.bean.App;
import com.luo.demo.gankio.bean.ResultsBean;
import com.luo.demo.gankio.listener.LoadMoreScrollListener;
import com.luo.demo.gankio.util.TimeUtils;
import com.socks.library.KLog;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends BaseFragment implements LoadMoreScrollListener.LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    public Handler mHandler = new Handler();
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int mCurrentPage;
    private int mPageCount;
    private List<ResultsBean> mData;
    private AndroidRvAdapter mRvAdapter;
    private int mOffsetCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        KLog.d("AppFragment onCreateView");
        View mRootView = inflater.inflate(R.layout.fragment_app, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.app_recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.app_swiper);
        LoadMoreScrollListener listener = new LoadMoreScrollListener(this);
        mRecyclerView.addOnScrollListener(listener);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KLog.d("AppFragment onActivityCreated");
        getData();
    }

    private void getData() {
        mPageCount = 10;    // 个数
        mOffsetCount = mPageCount;
        mCurrentPage = 1;   // 页数

        Api.getInstance().getApp(mPageCount, mCurrentPage, new CallBack<App>() {
            @Override
            public void onFinish(final boolean isSuccess, final App bean, final String error) {
                mHandler.post(new Runnable() {
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
                            mData = DataSupport.where("flag=?", "app").order("createdAt desc").limit(10).find(ResultsBean.class);
                            if (mData.isEmpty()) {
                                Snackbar.make(mRecyclerView, getResources().getString(R.string.fragment_android_data_fail),
                                        Snackbar.LENGTH_LONG).show();
                                return;
                            }
                            LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRvAdapter = new AndroidRvAdapter(mActivity, mData);
                            mRecyclerView.setAdapter(mRvAdapter);
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        });
    }

    @Override
    public void onRefresh() {
        getData();
    }

    @Override
    public void onLoadMore() {
        mCurrentPage = mCurrentPage + 1;

        if (mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        mSwipeRefreshLayout.setRefreshing(true);

        Api.getInstance().getApp(mPageCount, mCurrentPage, new CallBack<App>() {
            @Override
            public void onFinish(final boolean isSuccess, final App bean, String error) {
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
                            List<ResultsBean> mList = DataSupport.where("flag=?", "app").order("createdAt desc")
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
    private void saveAndformat(App bean) {
        // 格式化时间
        for (int i = 0; i < bean.getResults().size(); i++) {
            String time = TimeUtils.getFormatDate(bean.getResults().get(i).getCreatedAt());
            bean.getResults().get(i).setCreatedAt(time);
            bean.getResults().get(i).setFlag("app");
        }

        // 如果存在则修改，否则插入 -- 根据唯一的url
        for (int i = 0; i < bean.getResults().size(); i++) {
            bean.getResults().get(i).saveOrUpdate("url=?", bean.getResults().get(i).getUrl());
        }
    }

}
