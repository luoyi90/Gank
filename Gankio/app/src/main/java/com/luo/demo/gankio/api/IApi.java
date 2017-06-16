package com.luo.demo.gankio.api;

import com.luo.demo.gankio.bean.Android;
import com.luo.demo.gankio.bean.App;
import com.luo.demo.gankio.bean.Expand;
import com.luo.demo.gankio.bean.IOS;
import com.luo.demo.gankio.bean.JS;
import com.luo.demo.gankio.bean.Recommend;
import com.luo.demo.gankio.bean.Video;
import com.luo.demo.gankio.bean.Welfare;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-05-21 18:14
 * Email：175262808@qq.com
 * TODO: 网络请求方法接口
 * FIXME:
 */

interface IApi {

    void getAndroid(int count, int pager, CallBack<Android> c);

    void getIOS(int count, int pager, CallBack<IOS> c);

    void getJS(int count, int pager, CallBack<JS> c);

    void getVideo(int count, int pager, CallBack<Video> c);

    void getExpand(int count, int pager, CallBack<Expand> c);

    void getApp(int count, int pager, CallBack<App> c);

    void getRecommend(int count, int pager, CallBack<Recommend> c);

    void getWelfare(int count, int pager, CallBack<Welfare> c);
}
