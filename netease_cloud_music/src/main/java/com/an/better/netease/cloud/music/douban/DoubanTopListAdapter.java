package com.an.better.netease.cloud.music.douban;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.databinding.ItemListDoubanBinding;
import com.an.better.netease.cloud.music.douban.model.SubjectsBean;
import com.an.better.netease.cloud.music.douban.viewholder.MovieViewHolder;
import com.trident.beyond.adapter.BaseAdapter;
import com.trident.beyond.listener.OnItemClickListener;

import java.util.List;

/**
 * Created by android_ls on 2018/3/6.
 */

public class DoubanTopListAdapter extends BaseAdapter<SubjectsBean> {

    public DoubanTopListAdapter(Context context, List<SubjectsBean> data, OnItemClickListener listener) {
        super(context, data, listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(ItemListDoubanBinding.inflate(mLayoutInflater, parent, false),
                mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder)holder).bind((SubjectsBean)mData.get(position), position);
    }

}
