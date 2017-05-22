package com.luo.demo.gankio.api;

import com.luo.demo.gankio.bean.Android;
import com.luo.demo.gankio.bean.IOS;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-05-21 18:20
 * Email：175262808@qq.com
 * TODO: 网络请求方法单例封装类
 * FIXME:
 */

public class Api implements IApi {

    private volatile static Api sInstance;

    private IApi mApi;

    private Api() {
        mApi = new ApiOkHttp();
    }

    public static Api getInstance() {
        if (null == sInstance)
            synchronized (Api.class) {
                sInstance = new Api();
            }
        return sInstance;
    }

    @Override
    public void getAndroid(int count, int pager, CallBack<Android> c) {
        mApi.getAndroid(count, pager, c);
    }

    @Override
    public void getIOS(int count, int pager, CallBack<IOS> c) {
        mApi.getIOS(count, pager, c);
    }
}
