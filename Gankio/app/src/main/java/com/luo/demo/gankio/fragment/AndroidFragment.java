package com.luo.demo.gankio.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import com.socks.library.KLog;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 包名:  com.luo.demo.gankio.fragment
 * 作者:  Mr.Luo
 * 时间:  2017/5/22 14:17
 * 描述:  TODO
 * 联系:  175262808@qq.com
 */

public class AndroidFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, LoadMoreScrollListener.LoadMoreListener {

    private Context mContext;
    private Handler mHandler = new Handler();
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int mCurrentPage;
    private int mPageCount;
    private List<ResultsBean> mData;
    private AndroidRvAdapter mRvAdapter;

    private int startPage = 10;
    private int endPage = startPage + 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();

        View mRootView = inflater.inflate(R.layout.fragment_android, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.android_recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.android_swiper);
        setLoadMore();
        // 设置下拉进度的主题颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        KLog.d(this + " onActivityCreated execute");
        getData();
    }

    private void setLoadMore() {
        LoadMoreScrollListener listener = new LoadMoreScrollListener(this);
        mRecyclerView.addOnScrollListener(listener);
    }

    private void getData() {
        mPageCount = 10;    // 个数
        mCurrentPage = 1;   // 页数

        Api.getInstance().getAndroid(mPageCount, mCurrentPage, new CallBack<Android>() {
            @Override
            public void onFinish(boolean isSuccess, final Android bean, final String error) {

                checkSwipeRefreshLayout();

                if (isSuccess) {
                    saveAndformat(bean);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mData = bean.getResults();
                            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRvAdapter = new AndroidRvAdapter(mContext, mData);
                            mRecyclerView.setAdapter(mRvAdapter);
                        }
                    });

                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mData = DataSupport.order("createdAt desc").limit(10).find(ResultsBean.class);
                            if (mData.isEmpty()) {
                                Snackbar.make(mRecyclerView, "获取数据失败", Snackbar.LENGTH_LONG).show();
                                return;
                            }
                            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRvAdapter = new AndroidRvAdapter(mContext, mData);
                            mRecyclerView.setAdapter(mRvAdapter);
                        }
                    });
                }
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
        }

        // 如果存在则修改，否则插入 -- 根据唯一的url
        for (int i = 0; i < bean.getResults().size(); i++) {
            bean.getResults().get(i).saveOrUpdate("url=?", bean.getResults().get(i).getUrl());
        }
    }

    /**
     * 检查刷新状态
     */
    private void checkSwipeRefreshLayout() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }


        startPage = 10;
        endPage = startPage + 10;
    }

    // 刷新
    @Override
    public void onRefresh() {
        KLog.d("Android : onRefresh");
        getData();
    }

    // 加载更多
    @Override
    public void onLoadMore() {
        KLog.d("Android : onLoadMore");
        mCurrentPage = mCurrentPage + 1;

        if (mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        mSwipeRefreshLayout.setRefreshing(true);

        Api.getInstance().getAndroid(mPageCount, mCurrentPage, new CallBack<Android>() {
            @Override
            public void onFinish(boolean isSuccess, final Android bean, String error) {
                if (isSuccess) {
                    // 成功
                    saveAndformat(bean);

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mSwipeRefreshLayout.setRefreshing(false);
                            if (mData.isEmpty()) {
                                mData = bean.getResults();
                                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                mRecyclerView.setLayoutManager(layoutManager);
                                mRvAdapter = new AndroidRvAdapter(mContext, mData);
                                mRecyclerView.setAdapter(mRvAdapter);
                            } else {
                                mData.addAll(bean.getResults());
                                mRvAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                } else {
                    // 失败
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mSwipeRefreshLayout.setRefreshing(false);
                            List<ResultsBean> mList = DataSupport.order("createdAt desc")
                                    .limit(startPage).offset(endPage).find(ResultsBean.class);
                            if (mList.isEmpty()) {
                                Snackbar.make(mRecyclerView, "没有更多数据了", Snackbar.LENGTH_LONG).show();
                                return;
                            }

                            if (mData.isEmpty()) {
                                return;
                            }
                            startPage += 10;
                            endPage += 10;
                            mData.addAll(mList);
                            mRvAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });

    }
}
