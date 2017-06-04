package com.luo.demo.gankio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.base.BaseFragment;
import com.socks.library.KLog;

/**
 * 包名:  com.luo.demo.gankio.fragment
 * 作者:  Mr.Luo
 * 时间:  2017/6/2 16:30
 * 描述:  TODO
 * 联系:  175262808@qq.com
 */

public class IOSFragment extends BaseFragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KLog.d("IOS onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        KLog.d("IOS onCreate");
        View mRootView = inflater.inflate(R.layout.fragment_ios, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        KLog.d("IOS onCreate");
        super.onActivityCreated(savedInstanceState);
    }
}
