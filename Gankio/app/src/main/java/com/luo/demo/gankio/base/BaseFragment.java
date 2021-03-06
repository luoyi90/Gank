package com.luo.demo.gankio.base;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-05-21 17:56
 * Email：175262808@qq.com
 * TODO:
 * FIXME:
 */

public class BaseFragment extends Fragment {

    public View mRootView;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public Activity mActivity;
    public Handler mHandler = new Handler();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }
}
