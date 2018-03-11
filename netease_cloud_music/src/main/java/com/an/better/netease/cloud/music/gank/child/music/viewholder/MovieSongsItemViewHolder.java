package com.an.better.netease.cloud.music.gank.child.music.viewholder;

import android.view.View;

import com.an.better.netease.cloud.music.databinding.ItemMoveSongsBinding;
import com.an.better.netease.cloud.music.gank.child.music.model.MovieSongs;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

/**
 *
 * Created by android_ls on 2018/3/2.
 */

public class MovieSongsItemViewHolder extends BaseVdbViewHolder<MovieSongs, ItemMoveSongsBinding> {

    public MovieSongsItemViewHolder(ItemMoveSongsBinding viewDataBinding, OnItemClickListener<MovieSongs> listener) {
        super(viewDataBinding, listener);
    }

    @Override
    public void bind(MovieSongs cellModel, int position) {
        super.bind(cellModel, position);

        binding.ivOnePhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(binding.ivOnePhoto, cellModel, position);
                }
            }
        });

        binding.setModel(cellModel);
        binding.executePendingBindings();
    }

}
