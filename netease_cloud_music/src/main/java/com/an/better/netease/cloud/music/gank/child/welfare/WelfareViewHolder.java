package com.an.better.netease.cloud.music.gank.child.welfare;

import android.view.View;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.databinding.ItemWelfareBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.an.better.netease.cloud.music.utils.CommonUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.fresco.helper.utils.DensityUtil;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

/**
 *
 * Created by android_ls on 2018/2/24.
 */

public class WelfareViewHolder extends BaseVdbViewHolder<GanKInfo, ItemWelfareBinding> {

    public WelfareViewHolder(ItemWelfareBinding viewDataBinding, OnItemClickListener<GanKInfo> listener) {
        super(viewDataBinding, listener);
    }

    @Override
    public void bind(final GanKInfo cellModel, final int position) {
        super.bind(cellModel, position);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.img_default_meizi)
                .override(itemView.getWidth(), DensityUtil.dip2px(mContext, 250));

        Glide.with(binding.ivWelfare.getContext())
                .load(cellModel.url)
                .apply(options)
                .into(binding.ivWelfare);

        if (position % 2 == 0) {
            CommonUtils.setViewMargin(itemView, false, 12, 6, 12, 0);
        } else {
            CommonUtils.setViewMargin(itemView, false, 6, 12, 12, 0);
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(binding.ivWelfare, cellModel, position);
                }
            }
        });

        binding.executePendingBindings();
    }

}
