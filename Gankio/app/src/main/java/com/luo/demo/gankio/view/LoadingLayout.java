package com.luo.demo.gankio.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.luo.demo.gankio.R;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-06-11 14:27
 * Email：175262808@qq.com
 * TODO:
 * FIXME:
 */

public class LoadingLayout extends FrameLayout {
    private int emptyView, errorView, loadingView;
    private OnClickListener onRetryClickListener;

    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LoadingLayout, 0, 0);
        try {
            emptyView = a.getResourceId(R.styleable.LoadingLayout_emptyView, R.layout.loadinglayout_empty);
            errorView = a.getResourceId(R.styleable.LoadingLayout_errorView, R.layout.loadinglayout_error);
            loadingView = a.getResourceId(R.styleable.LoadingLayout_loadingView, R.layout.loadinglayout_loading);

            LayoutInflater inflater = LayoutInflater.from(getContext());
            inflater.inflate(emptyView, this, true);
            inflater.inflate(errorView, this, true);
            inflater.inflate(loadingView, this, true);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        for (int i = 0; i < getChildCount() - 1; i++) {
            getChildAt(i).setVisibility(GONE);
        }

        findViewById(R.id.btn_empty_retry).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onRetryClickListener) {
                    onRetryClickListener.onClick(v);
                }
            }
        });

        findViewById(R.id.btn_error_retry).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onRetryClickListener) {
                    onRetryClickListener.onClick(v);
                }
            }
        });

    }

    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }

    public void showEmpty() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 0) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    public void showError() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 1) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    public void showLoading() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 2) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }

    public void showContent() {
        for (int i = 0; i < this.getChildCount(); i++) {
            View child = this.getChildAt(i);
            if (i == 3) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }
}
