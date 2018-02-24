package com.an.better.netease.cloud.music.gank.child.everyday.viewholder;

import com.an.better.netease.cloud.music.databinding.ItemEverydayTitleBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKDayCategory;
import com.an.better.netease.cloud.music.utils.CommonUtils;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class TitleViewHolder extends BaseVdbViewHolder<GanKDayCategory, ItemEverydayTitleBinding> {

    public TitleViewHolder(ItemEverydayTitleBinding viewDataBinding) {
        super(viewDataBinding);
    }

    @Override
    public void bind(GanKDayCategory cellModel, int position) {
        super.bind(cellModel, position);

        binding.tvTitleType.setText(cellModel.title);
        binding.ivTitleType.setImageDrawable(CommonUtils.getDrawable(cellModel.resId));
        binding.executePendingBindings();
    }

}
