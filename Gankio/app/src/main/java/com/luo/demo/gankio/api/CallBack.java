package com.luo.demo.gankio.api;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-05-21 18:23
 * Email：175262808@qq.com
 *
 * @param <T> 请求完成后返回的数据实体类
 *            TODO: 网络请求完成后的回调接口
 *            FIXME:
 */

public interface CallBack<T> {

    /**
     * 请求完成后回调该方法
     *
     * @param isSuccess 请求是否成功
     * @param bean      如果请求成功该参数不为空否则为空
     * @param error     如果请求成功该参数为空否则会将失败原因传入
     */
    void onFinish(boolean isSuccess, T bean, String error);

}
