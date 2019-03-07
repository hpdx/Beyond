package com.an.better.netease.cloud.music.gank.child.music.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.databinding.ItemMoveSongsBinding;
import com.an.better.netease.cloud.music.gank.child.music.model.MovieSongs;
import com.an.better.netease.cloud.music.gank.child.music.viewholder.MovieSongsItemViewHolder;
import com.anbetter.beyond.adapter.BaseAdapter;

import java.util.List;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class MoveSongsAdapter extends BaseAdapter<MovieSongs> {

    public MoveSongsAdapter(Context context, List<MovieSongs> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieSongsItemViewHolder(ItemMoveSongsBinding.inflate(mLayoutInflater, parent, false), null);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieSongsItemViewHolder)holder).bind(mData.get(position), position);
    }

}
