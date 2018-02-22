package com.an.better.netease.cloud.music.gank.child.everyday.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.an.better.netease.cloud.music.databinding.ItemEverydayOneBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKDayBanner;
import com.bumptech.glide.Glide;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class OneViewHolder extends BaseVdbViewHolder<GanKDayBanner, ItemEverydayOneBinding> {

    public OneViewHolder(ItemEverydayOneBinding viewDataBinding) {
        super(viewDataBinding);
    }

    @Override
    public void bind(GanKDayBanner cellModel, int position) {
        super.bind(cellModel, position);

        binding.tvOnePhotoTitle.setVisibility(View.GONE);
        binding.ivOnePhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(binding.ivOnePhoto.getContext())
                .load(cellModel.url)
                .into(binding.ivOnePhoto);
    }

}
