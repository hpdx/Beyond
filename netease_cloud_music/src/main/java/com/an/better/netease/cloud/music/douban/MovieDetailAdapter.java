package com.an.better.netease.cloud.music.douban;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.databinding.ItemMovieDetailBinding;
import com.an.better.netease.cloud.music.douban.model.CastsBean;
import com.trident.beyond.adapter.BaseAdapter;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

import java.util.List;

/**
 * Created by android_ls on 2018/3/6.
 */

public class MovieDetailAdapter extends BaseAdapter<CastsBean> {

    public MovieDetailAdapter(Context context, List<CastsBean> data, OnItemClickListener listener) {
        super(context, data, listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CastsViewHolder(ItemMovieDetailBinding.inflate(mLayoutInflater, parent, false), mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CastsViewHolder)holder).bind(mData.get(position), position);
    }

    public static class CastsViewHolder extends BaseVdbViewHolder<CastsBean, ItemMovieDetailBinding> {

        public CastsViewHolder(ItemMovieDetailBinding viewDataBinding, OnItemClickListener<CastsBean> listener) {
            super(viewDataBinding, listener);
        }

        @Override
        public void bind(CastsBean cellModel, int position) {
            super.bind(cellModel, position);
            binding.setCastsBean(cellModel);
            binding.executePendingBindings();
        }
    }

}
