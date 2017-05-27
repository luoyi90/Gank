package com.luo.demo.gankio;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-05-21 17:35
 * Email：175262808@qq.com
 * TODO:
 * FIXME:
 */

public class App extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        LitePal.initialize(this);

    }

    public static Context getContext() {
        return sContext;
    }

}
