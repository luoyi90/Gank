package com.luo.demo.gankio.loadmore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.luo.demo.gankio.R;
import com.luo.demo.gankio.bean.ResultsBean;
import com.luo.demo.gankio.ui.PictureActivity;
import com.socks.library.KLog;

import java.util.List;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-06-24 13:47
 * Email：175262808@qq.com
 * TODO:
 * FIXME:
 */

public class LoadMoreRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ResultsBean> mDatas;
    private boolean hasFooter = false;
    private final int TYPE_FOOTER = 100001;
    private final int TYPE_CONTENT = 0;
    private LoadMoreHolder mLoadMoreHolder;
    private OnErrorClickListener mErrorListener;

    public void addAll(List<ResultsBean> datas) {
        mDatas.addAll(datas);
    }

    public interface OnErrorClickListener{
        void onReload();
    }

    public LoadMoreRvAdapter(Context c, List<ResultsBean> l) {
        mContext = c;
        mDatas = l;
    }

    public void setFooterView() {
        KLog.d("setFooterView");
        if (!hasFooter) {
            hasFooter = true;
            notifyItemInserted(mDatas.size());
        } else {
            notifyItemRemoved(mDatas.size());
            hasFooter = false;
        }
    }

    public void showError() {
        mLoadMoreHolder.mProgressBar.setVisibility(View.GONE);
        mLoadMoreHolder.mReLoadTv.setVisibility(View.VISIBLE);
    }

    public void showProgressBar() {
        mLoadMoreHolder.mProgressBar.setVisibility(View.VISIBLE);
        mLoadMoreHolder.mReLoadTv.setVisibility(View.GONE);
    }

    public void setOnErrorClickListener(OnErrorClickListener l) {
        mErrorListener = l;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // KLog.d("onCreateViewHolder viewType : " + viewType);

        if (viewType == TYPE_FOOTER) {
            mLoadMoreHolder = new LoadMoreHolder(LayoutInflater.from(
                    mContext).inflate(R.layout.loadmore_adapter_item, parent,
                    false));
            return mLoadMoreHolder;
        }

        ContentHolder holder = new ContentHolder(LayoutInflater.from(
                mContext).inflate(R.layout.welfare_adapter_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        // KLog.d("onBindViewHolder position : " + position);

        if (getItemViewType(position) == TYPE_FOOTER) {
            ((LoadMoreHolder) holder).mReLoadTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showProgressBar();
                    if (mErrorListener != null) {
                        mErrorListener.onReload();
                    }
                }
            });
        } else {
            ViewGroup.LayoutParams params = ((ContentHolder) holder).mImageView.getLayoutParams();
            if (position % 2 == 0) {
                params.height = 700;
            } else {
                params.height = 500;
            }
            ((ContentHolder) holder).mImageView.setLayoutParams(params);

            Glide.with(mContext)
                    .load(mDatas.get(position).getUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(((ContentHolder) holder).mImageView);

            ((ContentHolder) holder).mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, PictureActivity.class);
                    i.putExtra(PictureActivity.PIC_URL, mDatas.get(position).getUrl());

                    Bundle optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            (Activity) mContext, ((ContentHolder) holder).mImageView, "iv").toBundle();

                    mContext.startActivity(i, optionsCompat);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas.isEmpty())
            return 0;
        return hasFooter ? mDatas.size() + 1 : mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        KLog.d("hasFooter : " + hasFooter + " position : " + position);
        if (hasFooter && position == mDatas.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }

    private boolean isFooterViewPos(int position) {
        return hasFooter && position == mDatas.size();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isFooterViewPos(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    /*@Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (hasFooter) {
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    }
                    if (spanSizeLookup != null)
                        return spanSizeLookup.getSpanSize(position);
                    return 1;
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }*/

    private class LoadMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBar;
        TextView mReLoadTv;

        LoadMoreHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.loadmore_item_pb);
            mReLoadTv = (TextView) itemView.findViewById(R.id.loadmore_item_tv);
        }
    }

    private class ContentHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        ContentHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.welfare_item_image);
        }
    }

}
