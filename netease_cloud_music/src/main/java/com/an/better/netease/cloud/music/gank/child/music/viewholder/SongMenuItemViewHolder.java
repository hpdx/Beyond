package com.an.better.netease.cloud.music.gank.child.music.viewholder;

import android.view.View;

import com.an.better.netease.cloud.music.databinding.ItemSongMenuBinding;
import com.an.better.netease.cloud.music.gank.child.music.model.SongMenuBean;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

/**
 *
 * Created by android_ls on 2018/3/2.
 */

public class SongMenuItemViewHolder extends BaseVdbViewHolder<SongMenuBean, ItemSongMenuBinding> {

    public SongMenuItemViewHolder(ItemSongMenuBinding viewDataBinding, OnItemClickListener<SongMenuBean> listener) {
        super(viewDataBinding, listener);
    }

    @Override
    public void bind(SongMenuBean cellModel, int position) {
        super.bind(cellModel, position);

        binding.ivOnePhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(binding.ivOnePhoto, cellModel, position);
                }
            }
        });

        binding.setSong(cellModel);
        binding.executePendingBindings();
    }

}
