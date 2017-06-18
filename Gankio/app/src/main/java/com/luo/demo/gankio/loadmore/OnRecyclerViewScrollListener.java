package com.luo.demo.gankio.loadmore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-06-18 21:17
 * Email：175262808@qq.com
 * TODO:
 * FIXME:
 */

public abstract class OnRecyclerViewScrollListener<T> extends RecyclerView.OnScrollListener implements OnLoadMoreListener<T> {

    private enum layoutManagerType {
        LINEAR_LAYOUT,
        GRID_LAYOUT,
        STAGGERED_GRID_LAYOUT
    }

    private boolean mIsLoadingMore = false;

    public boolean isLoadingMore() {
        return mIsLoadingMore;
    }

    public void setLoadingMore(boolean loadingMore) {
        mIsLoadingMore = loadingMore;
    }

    protected layoutManagerType mLayoutManagerType;

    private int[] lastPositions;
    private int lastVisibleItemPosition;
    private int currentScrollState = 0;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (mLayoutManagerType == null) {
            if (layoutManager instanceof LinearLayoutManager) {
                mLayoutManagerType = layoutManagerType.LINEAR_LAYOUT;
            } else if (layoutManager instanceof GridLayoutManager) {
                mLayoutManagerType = layoutManagerType.GRID_LAYOUT;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                mLayoutManagerType = layoutManagerType.STAGGERED_GRID_LAYOUT;
            } else {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }
        }

        switch (mLayoutManagerType) {
            case LINEAR_LAYOUT:
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case GRID_LAYOUT:
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                break;
            case STAGGERED_GRID_LAYOUT:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                if (lastPositions == null) {
                    lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                lastVisibleItemPosition = findMax(lastPositions);
                break;
            default:
                break;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        currentScrollState = newState;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if (visibleItemCount > 0 && currentScrollState == RecyclerView.SCROLL_STATE_IDLE
                && lastVisibleItemPosition >= totalItemCount - 1) {
            if (!isLoadingMore()) {
                mIsLoadingMore = true;
                onStart();
                onLoadMore();
            }
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
