package com.gank.tab.multi.type.viewholder;

import com.anbetter.beyond.viewholder.BaseVdbViewHolder;
import com.gank.databinding.VdbGankDayBannerItemBinding;
import com.gank.day.model.GanKDayBanner;

/**
 * Created by android_ls on 2017/1/3.
 */

public class MTGanKDayViewHolder extends BaseVdbViewHolder<GanKDayBanner, VdbGankDayBannerItemBinding> {

    public MTGanKDayViewHolder(VdbGankDayBannerItemBinding viewDataBinding) {
        super(viewDataBinding);
    }

    @Override
    public void bind(GanKDayBanner cellModel, int position) {
        super.bind(cellModel, position);
        binding.setBanner(cellModel);
        binding.executePendingBindings();
    }
}
