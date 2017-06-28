package com.luo.demo.gankio.ui;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.luo.demo.gankio.R;
import com.luo.demo.gankio.base.BaseActivity;
import com.socks.library.KLog;

public class PictureActivity extends BaseActivity {

    public static final String PIC_URL = "pic_url";
    private PhotoView mPhotoView;
    private String mPicUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        initData();
        initView();
    }

    private void initData() {
        mPicUrl = getIntent().getStringExtra(PIC_URL);
        KLog.d(mPicUrl);
    }

    private void initView() {

        mPhotoView = (PhotoView) findViewById(R.id.pic_photoview);
        Glide.with(this).load(mPicUrl).centerCrop().into(mPhotoView);
        mPhotoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                onBackPressed();
            }
        });
    }
}
