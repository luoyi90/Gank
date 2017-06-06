package com.luo.demo.gankio.api;

import com.luo.demo.gankio.bean.Android;
import com.luo.demo.gankio.bean.App;
import com.luo.demo.gankio.bean.Expand;
import com.luo.demo.gankio.bean.IOS;
import com.luo.demo.gankio.bean.JS;
import com.luo.demo.gankio.bean.Recommend;
import com.luo.demo.gankio.bean.Video;

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

    @Override
    public void getJS(int count, int pager, CallBack<JS> c) {
        mApi.getJS(count, pager, c);
    }

    @Override
    public void getVideo(int count, int pager, CallBack<Video> c) {
        mApi.getVideo(count, pager, c);
    }

    @Override
    public void getExpand(int count, int pager, CallBack<Expand> c) {
        mApi.getExpand(count, pager, c);
    }

    @Override
    public void getApp(int count, int pager, CallBack<App> c) {
        mApi.getApp(count, pager, c);
    }

    @Override
    public void getRecommend(int count, int pager, CallBack<Recommend> c) {
        mApi.getRecommend(count, pager, c);
    }

}
