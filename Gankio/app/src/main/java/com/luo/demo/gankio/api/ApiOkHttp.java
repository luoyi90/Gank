package com.luo.demo.gankio.api;

import com.google.gson.Gson;
import com.luo.demo.gankio.Constants;
import com.luo.demo.gankio.bean.Android;
import com.luo.demo.gankio.bean.IOS;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-05-21 18:22
 * Email：175262808@qq.com
 * TODO:  网络请求方法OkHttp实现
 * FIXME:
 */

public class ApiOkHttp implements IApi {

    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    @Override
    public void getAndroid(int count, int pager, final CallBack<Android> c) {

        Request.Builder requestBuilder = new Request.Builder().url(Constants.Android + count + "/" + pager);
        Request request = requestBuilder.build();
        Call mcall = mOkHttpClient.newCall(request);

        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                // Log.d("response : " + response.body().string());
                String str = response.body().string();
                Android bean = new Gson().fromJson(str, Android.class);
                c.onFinish(true, bean, str);
            }
        });
    }

    @Override
    public void getIOS(int count, int pager, CallBack<IOS> c) {

    }
}
