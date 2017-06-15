package com.luo.demo.gankio.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luo.demo.gankio.R;
import com.luo.demo.gankio.base.BaseActivity;

import java.util.Calendar;

public class SplashActivity extends BaseActivity implements Animator.AnimatorListener, View.OnClickListener {

    private ImageView mImageView;
    private TextView mVersionName;
    private TextView mCopyright;
    private ObjectAnimator mAnimatorX;
    private ObjectAnimator mAnimatorY;
    private boolean isStop;

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
        mAnimatorY = ObjectAnimator.ofFloat(mImageView, "scaleY", 1.5f, 1f, 1.2f);
        mAnimatorY.setDuration(3000);
        mAnimatorY.start();
        mAnimatorX = ObjectAnimator.ofFloat(mImageView, "scaleX", 1.5f, 1f, 1.2f);
        mAnimatorX.setDuration(3000);
        mAnimatorX.start();
        mAnimatorX.addListener(this);
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
        mImageView.setOnClickListener(this);
        mVersionName = (TextView) findViewById(R.id.splash_version_name);
        mCopyright = (TextView) findViewById(R.id.splash_copyright);
        findViewById(R.id.splash_click).setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 12) {
            Glide.with(this).load(R.drawable.morning).into(mImageView);
        } else if (hour > 12 && hour <= 18) {
            Glide.with(this).load(R.drawable.afternoon).into(mImageView);
        } else {
            Glide.with(this).load(R.drawable.night).into(mImageView);
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
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (!isStop) {
            openMainUI();
            finish();
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        openMainUI();
        Intent intent2 = new Intent(SplashActivity.this, ClickActivity.class);
        startActivity(intent2);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.splash_image:
                isStop = true;
                mAnimatorX.cancel();
                mAnimatorY.cancel();
                break;

            case R.id.splash_click:
                isStop = true;
                openMainUI();
                finish();
                break;
        }
    }

    private void openMainUI() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
