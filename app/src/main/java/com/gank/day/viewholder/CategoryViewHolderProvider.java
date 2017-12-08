package com.gank.day.viewholder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gank.databinding.VdbGankDayCategoryItemBinding;
import com.gank.day.model.GanKDayCategory;
import com.trident.beyond.list.helper.ViewHolderProvider;
import com.trident.beyond.viewholder.BaseVdbViewHolder;


public class CategoryViewHolderProvider extends ViewHolderProvider<GanKDayCategory,
        CategoryViewHolderProvider.GanKDayViewHolder> {

    @Override
    public GanKDayViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new GanKDayViewHolder(VdbGankDayCategoryItemBinding.inflate(inflater, parent, false));
    }

    public static class GanKDayViewHolder extends BaseVdbViewHolder<GanKDayCategory, VdbGankDayCategoryItemBinding> {

        public GanKDayViewHolder(VdbGankDayCategoryItemBinding viewDataBinding) {
            super(viewDataBinding);
        }

        @Override
        public void bind(GanKDayCategory cellModel, int position) {
            super.bind(cellModel, position);
            binding.setCategory(cellModel);
            binding.executePendingBindings();
        }
    }

}