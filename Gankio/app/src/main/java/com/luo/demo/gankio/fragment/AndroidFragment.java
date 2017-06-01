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

public class AndroidFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private Context mContext;
    private Handler mHandler = new Handler();
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();

        View mRootView = inflater.inflate(R.layout.fragment_android, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.android_recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.android_swiper);

        // 设置下拉进度的主题颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
    }

    private void getData() {
        Api.getInstance().getAndroid(10, 1, new CallBack<Android>() {
            @Override
            public void onFinish(boolean isSuccess, final Android bean, final String error) {

                checkSwipeRefreshLayout();

                if (isSuccess) {

                    for (int i = 0; i < bean.getResults().size(); i++) {
                        KLog.d("beford : " + bean.getResults().get(i).getCreatedAt());
                    }

                    for (int i = 0; i < bean.getResults().size(); i++) {
                        String time = TimeUtils.getFormatDate(bean.getResults().get(i).getCreatedAt());
                        KLog.d("time : " + time);
                        bean.getResults().get(i).setCreatedAt(time);
                    }

                    for (int i = 0; i < bean.getResults().size(); i++) {
                        KLog.d("after : " + bean.getResults().get(i).getCreatedAt());
                    }

                    // 如果存在则修改，否则插入 -- 根据唯一的url
                    for (int i = 0; i < bean.getResults().size(); i++) {
                        bean.getResults().get(i).saveOrUpdate("url=?", bean.getResults().get(i).getUrl());
                    }

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setAdapter(new AndroidRvAdapter(mContext, bean));
                        }
                    });

                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            // List<ResultsBean> mList = DataSupport.findAll(ResultsBean.class);

                            List<ResultsBean> mList = DataSupport.order("createdAt desc").limit(10).find(ResultsBean.class);

                            if (mList.isEmpty()) {
                                Snackbar.make(mRecyclerView, "获取数据失败", Snackbar.LENGTH_LONG).show();
                                return;
                            }

                            final Android android = new Android();
                            android.setResults(mList);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setAdapter(new AndroidRvAdapter(mContext, android));
                        }
                    });
                }
            }
        });
    }

    private void checkSwipeRefreshLayout() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    @Override
    public void onRefresh() {
        KLog.d("onRefresh");
        getData();
    }
}
