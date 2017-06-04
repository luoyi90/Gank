package com.luo.demo.gankio.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.adapter.MyFragmentPagerAdapter;
import com.luo.demo.gankio.fragment.IOSFragment;
import com.luo.demo.gankio.widget.MyViewPager;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private MyViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);

        mVp = (MyViewPager) findViewById(R.id.vp);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new IOSFragment(), "IOS");
        adapter.addFragment(new IOSFragment(), "IOS");
        adapter.addFragment(new IOSFragment(), "IOS");
        mVp.setAdapter(adapter);
        TabLayout t;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                mVp.setCurrentItem(0, true);
                break;
            case R.id.btn2:
                mVp.setCurrentItem(1, true);
                break;
            case R.id.btn3:
                mVp.setCurrentItem(2, true);
                break;
        }
    }
}
