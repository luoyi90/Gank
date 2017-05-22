package com.luo.demo.gankio.api;

import com.luo.demo.gankio.bean.Android;
import com.luo.demo.gankio.bean.IOS;

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
}
