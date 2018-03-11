package com.an.better.netease.cloud.music.gank.child.music.viewholder;

import android.support.v7.widget.LinearLayoutManager;

import com.an.better.netease.cloud.music.databinding.FirstPublishBlockBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.event.EverydayEventHandler;
import com.an.better.netease.cloud.music.gank.child.music.adapter.FirstPublishAdapter;
import com.an.better.netease.cloud.music.gank.child.music.model.Mix1Bean;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class FirstPublishViewHolder extends BaseVdbViewHolder<Mix1Bean, FirstPublishBlockBinding> {

    private FirstPublishAdapter mAdapter;
    private EverydayEventHandler mEventHandler;

    public FirstPublishViewHolder(FirstPublishBlockBinding viewDataBinding,
                                  EverydayEventHandler eventHandler) {
        super(viewDataBinding);
        this.mEventHandler = eventHandler;
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void bind(Mix1Bean cellModel, int position) {
        super.bind(cellModel, position);

        if (mAdapter == null) {
            mAdapter = new FirstPublishAdapter(mContext, cellModel.result);
            binding.recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.updateAdapterData(cellModel.result);
        }
        binding.executePendingBindings();
    }

}
