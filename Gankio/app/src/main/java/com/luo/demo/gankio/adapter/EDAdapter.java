package com.luo.demo.gankio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luo.demo.gankio.R;
import com.luo.demo.gankio.bean.EveryDay;
import com.luo.demo.gankio.util.TimeUtils;

import java.util.List;

/**
 * 包名:  com.luo.demo.gankio.adapter
 * 作者:  Mr.Luo
 * 时间:  2017/8/9 16:01
 * 描述:  TODO
 * 联系:  175262808@qq.com
 */

public class EDAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<EveryDay> mDatas;
    private Context mContext;

    public EDAdapter(Context c, List<EveryDay> datas) {
        mContext = c;
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EDHolder edHolder = new EDHolder(LayoutInflater.from(mContext).inflate(R.layout.everyday_adapter_item, parent, false));
        return edHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(mContext).load(mDatas.get(position).getImg()).centerCrop().into(((EDHolder) holder).mImageView);
        ((EDHolder) holder).mTvTitle.setText(mDatas.get(position).getB().getTitle());
        // ((EDHolder) holder).mTvDate.setText(mDatas.get(position).getB().getCreated_at());
        ((EDHolder) holder).mTvDate.setText(TimeUtils.getFormatDate2(mDatas.get(position).getB().getCreated_at()));
    }

    @Override
    public int getItemCount() {
        if (mDatas.isEmpty())
            return 0;
        return mDatas.size();
    }

    private class EDHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTvTitle;
        TextView mTvDate;

        EDHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.ed_item_image);
            mTvTitle = (TextView) view.findViewById(R.id.ed_item_title);
            mTvDate = (TextView) view.findViewById(R.id.ed_item_date);
        }
    }
}
