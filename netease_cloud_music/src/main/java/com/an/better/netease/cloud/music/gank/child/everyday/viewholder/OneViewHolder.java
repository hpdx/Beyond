package com.an.better.netease.cloud.music.gank.child.everyday.viewholder;

import com.an.better.netease.cloud.music.databinding.ItemEverydayBannerBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.event.EverydayEventHandler;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKDayBanner;
import com.bumptech.glide.Glide;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class OneViewHolder extends BaseVdbViewHolder<GanKDayBanner, ItemEverydayBannerBinding> {

    private EverydayEventHandler mEventHandler;
    public OneViewHolder(ItemEverydayBannerBinding viewDataBinding, EverydayEventHandler eventHandler) {
        super(viewDataBinding);
        this.mEventHandler = eventHandler;
    }

    @Override
    public void bind(GanKDayBanner cellModel, int position) {
        super.bind(cellModel, position);

        Glide.with(binding.ivOnePhoto.getContext())
                .load(cellModel.url)
                .into(binding.ivOnePhoto);
        binding.executePendingBindings();
    }

}
