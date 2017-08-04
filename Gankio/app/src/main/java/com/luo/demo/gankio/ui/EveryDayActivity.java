package com.luo.demo.gankio.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.api.Api;
import com.luo.demo.gankio.api.CallBack;
import com.luo.demo.gankio.base.BaseActivity;
import com.luo.demo.gankio.bean.History;
import com.socks.library.KLog;

public class EveryDayActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);

        initView();
        getData();

    }

    private void getData() {
        Api.getInstance().getHistory(10, 1, new CallBack<History>() {
            @Override
            public void onFinish(boolean isSuccess, History bean, String error) {
                if (isSuccess) {
                    History b = bean;
                    String content = b.getResults().get(0).getContent();
                    int indexOf = content.indexOf("http");
                    int lastOf = content.indexOf(".jpg");
                    KLog.d(content.substring(indexOf, lastOf + 4));

                    /*int indexOf2 = text2.indexOf("www.bilibili");
                    int lastOf2 = text2.indexOf("</a>");
                    System.out.println(indexOf2);
                    System.out.println(lastOf2);
                    String temp = text2.substring(indexOf2, lastOf2);
                    System.out.println(temp);
                    String[] split = temp.split("target=_blank>");*/
                }
            }
        });
    }

    @Override
    public void onRefresh() {

    }

    private void initView() {
        SwipeRefreshLayout mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.ed_swip);
        mRefreshLayout.setColorSchemeResources(R.color.red, R.color.blue, R.color.green);
        mRefreshLayout.setOnRefreshListener(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.ed_toolbar);
        mToolbar.setTitle("福利");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.ed_rv);
    }
}
