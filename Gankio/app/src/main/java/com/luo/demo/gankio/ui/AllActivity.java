package com.luo.demo.gankio.ui;

import android.os.Bundle;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.api.Api;
import com.luo.demo.gankio.api.CallBack;
import com.luo.demo.gankio.base.BaseActivity;
import com.luo.demo.gankio.bean.History;
import com.socks.library.KLog;

public class AllActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        KLog.d("onCreate");
        Api.getInstance().getHistory(2, 1, new CallBack<History>() {
            @Override
            public void onFinish(boolean isSuccess, History bean, String error) {
                if (isSuccess) {
                    History b = bean;
                    String content = b.getResults().get(0).getContent();
                    int indexOf = content.indexOf("http");
                    int lastOf = content.indexOf(".jpg");
                    KLog.d(content.substring(indexOf, lastOf + 4));
                }
            }
        });

    }
}
