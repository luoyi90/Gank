package com.luo.demo.gankio.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.bean.Android;
import com.luo.demo.gankio.ui.DetailActivity;

/**
 * 包名:  com.luo.demo.gankio.adapter
 * 作者:  Mr.Luo
 * 时间:  2017/5/22 15:53
 * 描述:  TODO
 * 联系:  175262808@qq.com
 */

public class AndroidRvAdapter extends RecyclerView.Adapter<AndroidRvAdapter.ViewHolder> {

    private Android mAndroid;
    private final TypedValue mTypedValue = new TypedValue();
    private Context mContext;
    private int mBackground;

    public AndroidRvAdapter(Context context, Android android) {
        // context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        // mBackground = mTypedValue.resourceId;
        mContext = context;
        this.mAndroid = android;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_android, parent, false);
        // view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTvTitle.setText(mAndroid.getResults().get(position).getDesc());
        holder.mTvAuthor.setText(mAndroid.getResults().get(position).getWho());
        holder.mTvCreateDate.setText(mAndroid.getResults().get(position).getCreatedAt());

        /*if (mAndroid.getResults().get(position).getImages() != null) {
            Glide.with(mContext)
                    .load(mAndroid.getResults().get(position).getImages().get(0))
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(holder.mImageView);
        }*/

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Snackbar.make(v, mAndroid.getResults().get(position).getUrl(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(DetailActivity.LOAD_URL, mAndroid.getResults().get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAndroid.getResults().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView mTvTitle;
        TextView mTvAuthor;
        TextView mTvCreateDate;
        /// ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            // mImageView = (ImageView) view.findViewById(R.id.item_android_img);
            mTvTitle = (TextView) view.findViewById(R.id.item_android_title);
            mTvAuthor = (TextView) view.findViewById(R.id.item_android_author);
            mTvCreateDate = (TextView) view.findViewById(R.id.item_android_createdate);
        }

    }
}
