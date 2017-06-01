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
import com.socks.library.KLog;

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
    private Android mBean;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();

        View mRootView = inflater.inflate(R.layout.fragment_android, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.android_recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.android_swiper);

        // 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
        // 设置下拉进度的背景颜色，默认就是白色的
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
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

                if (isSuccess) {

                    if (mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }

                    if (mBean != null) {
                        KLog.d("isRn : " + mBean.getResults().contains(bean.getResults()));
                    }

                    if (mBean != null && mBean.getResults().contains(bean.getResults())) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(mRecyclerView, "数据相同", Snackbar.LENGTH_LONG).show();
                            }
                        });
                        return;
                    }

                    mBean = bean;

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
                    Snackbar.make(mRecyclerView, error, Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        KLog.d("onRefresh");
        getData();
    }
}
