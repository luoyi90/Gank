package com.luo.demo.gankio.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.base.BaseActivity;

import java.util.Calendar;

public class SplashActivity extends BaseActivity implements Animation.AnimationListener {

    ImageView mImageView;
    private TextView mVersionName;
    private TextView mCopyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        initData();
    }

    private void initData() {
        mCopyright.setText(getResources().getString(R.string.splash_copyright));
        try {
            mVersionName.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        mImageView.startAnimation(animation);
        animation.setAnimationListener(this);
    }

    private void initView() {
        View decorView = getWindow().getDecorView();
        int option = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
        }
        decorView.setSystemUiVisibility(option);

        mImageView = (ImageView) findViewById(R.id.splash_image);
        mVersionName = (TextView) findViewById(R.id.splash_version_name);
        mCopyright = (TextView) findViewById(R.id.splash_copyright);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 12) {
            mImageView.setImageResource(R.drawable.morning);
        } else if (hour > 12 && hour <= 18) {
            mImageView.setImageResource(R.drawable.afternoon);
        } else {
            mImageView.setImageResource(R.drawable.night);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onAnimationStart(Animation animation) { }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) { }
}
