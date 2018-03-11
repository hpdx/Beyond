package com.an.better.netease.cloud.music.gank.child.music.viewholder;

import android.support.v7.widget.LinearLayoutManager;

import com.an.better.netease.cloud.music.databinding.FirstPublishBlockBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.event.EverydayEventHandler;
import com.an.better.netease.cloud.music.gank.child.music.adapter.SongMenuAdapter;
import com.an.better.netease.cloud.music.gank.child.music.model.SongMenuBean;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

import java.util.List;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class SongMenuViewHolder extends BaseVdbViewHolder<List<SongMenuBean>, FirstPublishBlockBinding> {

    private SongMenuAdapter mAdapter;
    private EverydayEventHandler mEventHandler;

    public SongMenuViewHolder(FirstPublishBlockBinding viewDataBinding, EverydayEventHandler eventHandler) {
        super(viewDataBinding);
        this.mEventHandler = eventHandler;
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.tvTitle.setText("歌单精选");
    }

    @Override
    public void bind(List<SongMenuBean> movieSongsList, int position) {
        super.bind(movieSongsList, position);
        if (mAdapter == null) {
            mAdapter = new SongMenuAdapter(mContext, movieSongsList);
            binding.recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.updateAdapterData(movieSongsList);
        }
        binding.executePendingBindings();
    }

}
