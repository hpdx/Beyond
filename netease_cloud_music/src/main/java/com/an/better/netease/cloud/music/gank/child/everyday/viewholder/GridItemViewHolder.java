package com.an.better.netease.cloud.music.gank.child.everyday.viewholder;

import android.text.TextUtils;

import com.an.better.netease.cloud.music.databinding.ItemEverydayGridItemBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.anbetter.beyond.viewholder.BaseVdbViewHolder;
import com.bumptech.glide.Glide;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class GridItemViewHolder extends BaseVdbViewHolder<GanKInfo, ItemEverydayGridItemBinding> {

    public GridItemViewHolder(ItemEverydayGridItemBinding viewDataBinding) {
        super(viewDataBinding);
    }

    @Override
    public void bind(GanKInfo cellModel, int position) {
        super.bind(cellModel, position);

        Glide.with(binding.ivThreeOneOne.getContext())
                .load(cellModel.imageUrl)
                .into(binding.ivThreeOneOne);

        if(!TextUtils.isEmpty(cellModel.desc)) {
            binding.tvThreeOneOneTitle.setText(cellModel.desc);
        }
        binding.executePendingBindings();
    }

}
