package com.luo.demo.gankio.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-06-04 12:55
 * Email：175262808@qq.com
 * TODO:
 * FIXME:
 */

public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item) {
        setCurrentItem(item, false);
    }
}
