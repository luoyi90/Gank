package com.luo.demo.gankio.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.luo.demo.gankio.util.Log;

/**
 * 包名:  com.luo.demo.gankio.fragment
 * 作者:  Mr.Luo
 * 时间:  2017/5/22 14:17
 * 描述:  TODO
 * 联系:  175262808@qq.com
 */

public class AndroidFragment extends BaseFragment {

    private Context mContext;
    private Handler mHandler = new Handler();
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getContext();

        View mRootView = inflater.inflate(R.layout.fragment_android, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.android_recyclerview);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Api.getInstance().getAndroid(10, 1, new CallBack<Android>() {
            @Override
            public void onFinish(boolean isSuccess, final Android bean, final String error) {

                if (isSuccess) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("size : " + bean.getResults().size());
                            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                            mRecyclerView.setLayoutManager(layoutManager);
                            //设置垂直滚动，也可以设置横向滚动
                            mRecyclerView.setAdapter(new AndroidRvAdapter(mContext, bean));
                        }
                    });
                } else {
                    Snackbar.make(mRecyclerView, error, Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }
}
