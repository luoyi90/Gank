package com.luo.demo.gankio.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.luo.demo.gankio.Constants;
import com.luo.demo.gankio.bean.Android;
import com.luo.demo.gankio.bean.App;
import com.luo.demo.gankio.bean.Expand;
import com.luo.demo.gankio.bean.History;
import com.luo.demo.gankio.bean.IOS;
import com.luo.demo.gankio.bean.JS;
import com.luo.demo.gankio.bean.Recommend;
import com.luo.demo.gankio.bean.Video;
import com.luo.demo.gankio.bean.Welfare;

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

class ApiOkHttp implements IApi {

    private static OkHttpClient mOkHttpClient = new OkHttpClient();

    @Override
    public void getAndroid(int count, int pager, final CallBack<Android> c) {

        Request.Builder requestBuilder = new Request.Builder().url(Constants.Android + count + "/" + pager);
        Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                c.onFinish(false, null, e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String str = response.body().string();
                Android bean = new Gson().fromJson(str, Android.class);
                c.onFinish(true, bean, str);
            }
        });
    }

    @Override
    public void getIOS(int count, int pager, final CallBack<IOS> c) {
        Request.Builder requestBuilder = new Request.Builder().url(Constants.IOS + count + "/" + pager);
        Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                c.onFinish(false, null, e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String str = response.body().string();
                IOS bean = new Gson().fromJson(str, IOS.class);
                c.onFinish(true, bean, str);
            }
        });
    }

    @Override
    public void getJS(int count, int pager, final CallBack<JS> c) {
        Request.Builder requestBuilder = new Request.Builder().url(Constants.JS + count + "/" + pager);
        Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                c.onFinish(false, null, e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String str = response.body().string();
                JS bean = new Gson().fromJson(str, JS.class);
                c.onFinish(true, bean, str);
            }
        });
    }

    @Override
    public void getVideo(int count, int pager, final CallBack<Video> c) {
        Request.Builder requestBuilder = new Request.Builder().url(Constants.VIDEO + count + "/" + pager);
        Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                c.onFinish(false, null, e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String str = response.body().string();
                Video bean = new Gson().fromJson(str, Video.class);
                c.onFinish(true, bean, str);
            }
        });
    }

    @Override
    public void getExpand(int count, int pager, final CallBack<Expand> c) {
        Request.Builder requestBuilder = new Request.Builder().url(Constants.EXPAND + count + "/" + pager);
        Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                c.onFinish(false, null, e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String str = response.body().string();
                Expand bean = new Gson().fromJson(str, Expand.class);
                c.onFinish(true, bean, str);
            }
        });
    }

    @Override
    public void getApp(int count, int pager, final CallBack<App> c) {
        Request.Builder requestBuilder = new Request.Builder().url(Constants.APP + count + "/" + pager);
        Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                c.onFinish(false, null, e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String str = response.body().string();
                App bean = new Gson().fromJson(str, App.class);
                c.onFinish(true, bean, str);
            }
        });
    }

    @Override
    public void getRecommend(int count, int pager, final CallBack<Recommend> c) {
        Request.Builder requestBuilder = new Request.Builder().url(Constants.RECOMMEND + count + "/" + pager);
        Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                c.onFinish(false, null, e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String str = response.body().string();
                Recommend bean = new Gson().fromJson(str, Recommend.class);
                c.onFinish(true, bean, str);
            }
        });
    }

    @Override
    public void getWelfare(int count, int pager, final CallBack<Welfare> c) {
        Request.Builder requestBuilder = new Request.Builder().url(Constants.WELFARE + count + "/" + pager);
        Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                c.onFinish(false, null, e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String str = response.body().string();
                Welfare bean = new Gson().fromJson(str, Welfare.class);
                c.onFinish(true, bean, str);
            }
        });
    }

    @Override
    public void getHistory(int count, int pager, final CallBack<History> c) {
        Request.Builder requestBuilder = new Request.Builder().url(Constants.HISTORY + count + "/" + pager);
        Request request = requestBuilder.build();
        Call mCall = mOkHttpClient.newCall(request);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                c.onFinish(false, null, e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                String str = response.body().string();
                History bean = new Gson().fromJson(str, History.class);
                c.onFinish(true, bean, str);
            }
        });
    }

}
