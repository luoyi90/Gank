package com.luo.demo.gankio.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.api.Api;
import com.luo.demo.gankio.api.CallBack;
import com.luo.demo.gankio.base.BaseActivity;
import com.luo.demo.gankio.bean.History;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class EveryDayActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_day);
        initView();
        getData();

    }

    private List<EveryDay> mData;

    private void getData() {
        mData = new ArrayList<>();

        Api.getInstance().getHistory(10, 1, new CallBack<History>() {
            @Override
            public void onFinish(boolean isSuccess, History bean, String error) {
                if (isSuccess) {

                    History b = bean;
                    EveryDay ed;
                    for (int i = 0; i < b.getResults().size(); i++) {

                        String content = b.getResults().get(i).getContent();
                        int indexOf = content.indexOf("http");
                        int lastOf = content.indexOf(".jpg");
                        String img = content.substring(indexOf, lastOf + 4);

                        ed = new EveryDay();
                        ed.setImg(img);
                        ed.setB(b.getResults().get(i));
                        mData.add(ed);
                    }

                    for (int i = 0; i < mData.size(); i++) {
                        KLog.d(mData.get(i).getImg());
                        KLog.d(mData.get(i).getB().getTitle());
                    }

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
        mToolbar.setTitle("每日数据");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.ed_rv);
    }

    private class EveryDay {

        private String img;
        private History.ResultsBean b;

        public History.ResultsBean getB() {
            return b;
        }

        public void setB(History.ResultsBean b) {
            this.b = b;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
