package com.gank.common.viewholder;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.anbetter.beyond.helper.ViewHolderProvider;
import com.anbetter.beyond.viewholder.BaseVdbViewHolder;
import com.gank.common.model.GanKCellModel;
import com.gank.databinding.VdbGankItemBinding;


/**
 * Created by android_ls on 2016/12/28.
 */

public class DBGanKViewHolderProvider extends ViewHolderProvider<GanKCellModel, DBGanKViewHolderProvider.GanKDBViewHolder> {

    @Override
    public GanKDBViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new GanKDBViewHolder(VdbGankItemBinding.inflate(inflater, parent, false));
    }

    public static class GanKDBViewHolder extends BaseVdbViewHolder<GanKCellModel, VdbGankItemBinding> {

        public GanKDBViewHolder(VdbGankItemBinding viewDataBinding) {
            super(viewDataBinding);
        }

        @Override
        public void bind(GanKCellModel cellModel, int position) {
            super.bind(cellModel, position);
            binding.setCellModel(cellModel);
            binding.executePendingBindings();
        }
    }

}
