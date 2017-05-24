package com.luo.demo.gankio.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.luo.demo.gankio.R;
import com.luo.demo.gankio.bean.Android;
import com.luo.demo.gankio.ui.DetailActivity;
import com.luo.demo.gankio.util.StringUtil;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_android2, parent, false);
        // view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mTvTitle.setText(mAndroid.getResults().get(position).getDesc());

        Object[] obj = StringUtil.getCategory(mAndroid.getResults().get(position).getUrl());

        holder.mTvCategory.setText(obj[0].toString());
        holder.mLayout.setBackgroundColor((Integer) obj[1]);
        holder.mLayout.setBackground(mContext.getResources().getDrawable((Integer) obj[1]));

        holder.mTvAuthor.setText(mAndroid.getResults().get(position).getWho());
        // holder.mTvTime.setText(TimeUtils.getFormatDate(mAndroid.getResults().get(position).getCreatedAt()));

        holder.mTvTime.setText(StringUtil.getTime( mAndroid.getResults().get(position).getCreatedAt() ));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                // intent.putExtra(DetailActivity.LOAD_URL, mAndroid.getResults().get(position).getUrl());
                intent.putExtra(DetailActivity.MODEL, mAndroid.getResults().get(position));
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
        TextView mTvCategory;
        TextView mTvTime;
        FrameLayout mLayout;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mLayout = (FrameLayout) view.findViewById(R.id.item_android_category_bg);
            mTvTitle = (TextView) view.findViewById(R.id.item_android_title);
            mTvAuthor = (TextView) view.findViewById(R.id.item_android_author);
            mTvCategory = (TextView) view.findViewById(R.id.item_android_category);
            mTvTime = (TextView) view.findViewById(R.id.item_android_time);
        }

    }
}
