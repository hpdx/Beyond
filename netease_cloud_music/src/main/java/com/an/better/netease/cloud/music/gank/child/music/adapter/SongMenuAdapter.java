package com.an.better.netease.cloud.music.gank.child.music.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.databinding.ItemSongMenuBinding;
import com.an.better.netease.cloud.music.gank.child.music.model.SongMenuBean;
import com.an.better.netease.cloud.music.gank.child.music.viewholder.SongMenuItemViewHolder;
import com.anbetter.beyond.adapter.BaseAdapter;

import java.util.List;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class SongMenuAdapter extends BaseAdapter<SongMenuBean> {

    public SongMenuAdapter(Context context, List<SongMenuBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongMenuItemViewHolder(ItemSongMenuBinding.inflate(mLayoutInflater, parent, false), null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SongMenuItemViewHolder)holder).bind(mData.get(position), position);
    }

}
