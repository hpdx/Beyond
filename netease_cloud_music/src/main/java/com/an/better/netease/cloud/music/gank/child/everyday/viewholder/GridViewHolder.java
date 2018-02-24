package com.an.better.netease.cloud.music.gank.child.everyday.viewholder;

import android.support.v7.widget.GridLayoutManager;

import com.an.better.netease.cloud.music.databinding.ItemEverydayGridBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.adapter.GridAdapter;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.facebook.fresco.helper.utils.DensityUtil;
import com.trident.beyond.viewholder.BaseVdbViewHolder;
import com.trident.beyond.widgets.decoration.GridItemDecoration;

import java.util.ArrayList;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class GridViewHolder extends BaseVdbViewHolder<ArrayList<GanKInfo>, ItemEverydayGridBinding> {

    private GridAdapter mAdapter;

    public GridViewHolder(ItemEverydayGridBinding viewDataBinding, int columnCount, float divider) {
        super(viewDataBinding);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), columnCount));
        binding.recyclerView.addItemDecoration(new GridItemDecoration(
                DensityUtil.dipToPixels(mContext, divider),
                DensityUtil.dipToPixels(mContext, divider)));
    }

    @Override
    public void bind(ArrayList<GanKInfo> cellModel, int position) {
        super.bind(cellModel, position);
        if (mAdapter == null) {
            mAdapter = new GridAdapter(mContext, cellModel);
            binding.recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.updateAdapterData(cellModel);
        }
        binding.executePendingBindings();
    }

}
