package com.an.better.netease.cloud.music.gank.child.music.viewholder;

import android.support.v7.widget.LinearLayoutManager;

import com.an.better.netease.cloud.music.databinding.FirstPublishBlockBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.event.EverydayEventHandler;
import com.an.better.netease.cloud.music.gank.child.music.adapter.MoveSongsAdapter;
import com.an.better.netease.cloud.music.gank.child.music.model.MovieSongs;
import com.anbetter.beyond.viewholder.BaseVdbViewHolder;

import java.util.List;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class MovieSongsViewHolder extends BaseVdbViewHolder<List<MovieSongs>, FirstPublishBlockBinding> {

    private MoveSongsAdapter mAdapter;
    private EverydayEventHandler mEventHandler;

    public MovieSongsViewHolder(FirstPublishBlockBinding viewDataBinding, EverydayEventHandler eventHandler) {
        super(viewDataBinding);
        this.mEventHandler = eventHandler;
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void bind(List<MovieSongs> movieSongsList, int position) {
        super.bind(movieSongsList, position);
        if (mAdapter == null) {
            mAdapter = new MoveSongsAdapter(mContext, movieSongsList);
            binding.recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.updateAdapterData(movieSongsList);
        }
        binding.executePendingBindings();
    }

}
