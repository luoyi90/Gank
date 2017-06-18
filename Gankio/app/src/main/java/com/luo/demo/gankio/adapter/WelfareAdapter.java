package com.luo.demo.gankio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.luo.demo.gankio.R;
import com.luo.demo.gankio.bean.ResultsBean;

import java.util.List;

/**
 * 包名:  com.luo.demo.gankio.adapter
 * 作者:  Mr.Luo
 * 时间:  2017/6/16 16:37
 * 描述:  TODO
 * 联系:  175262808@qq.com
 */

public class WelfareAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ResultsBean> mDatas;

    public WelfareAdapter(Context c, List<ResultsBean> l) {
        this.mDatas = l;
        this.mContext = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WelfareHolder holder = new WelfareHolder(LayoutInflater.from(
                mContext).inflate(R.layout.welfare_adapter_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewGroup.LayoutParams params = ((WelfareHolder) holder).mImageView.getLayoutParams();
        if (position % 2 == 0) {
            params.height = 700;
        } else {
            params.height = 500;
        }
        ((WelfareHolder) holder).mImageView.setLayoutParams(params);

        Glide.with(mContext)
                .load(mDatas.get(position).getUrl())
                .centerCrop()
                .into(((WelfareHolder) holder).mImageView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    private class WelfareHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        WelfareHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.welfare_item_image);
        }
    }
}
