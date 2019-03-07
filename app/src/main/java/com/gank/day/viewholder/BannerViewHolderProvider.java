package com.gank.day.viewholder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.anbetter.beyond.helper.ViewHolderProvider;
import com.anbetter.beyond.viewholder.BaseVdbViewHolder;
import com.gank.databinding.VdbGankDayBannerItemBinding;
import com.gank.day.model.GanKDayBanner;

public class BannerViewHolderProvider extends ViewHolderProvider<GanKDayBanner,
        BannerViewHolderProvider.GanKDayViewHolder> {

    @Override
    public GanKDayViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new GanKDayViewHolder(VdbGankDayBannerItemBinding.inflate(inflater, parent, false));
    }

    public static class GanKDayViewHolder extends BaseVdbViewHolder<GanKDayBanner, VdbGankDayBannerItemBinding> {

        public GanKDayViewHolder(VdbGankDayBannerItemBinding viewDataBinding) {
            super(viewDataBinding);
        }

        @Override
        public void bind(GanKDayBanner cellModel, int position) {
            super.bind(cellModel, position);
            binding.setBanner(cellModel);
            binding.executePendingBindings();
        }
    }

}