package com.an.better.netease.cloud.music.gank.child.everyday.viewholder;

import com.an.better.netease.cloud.music.databinding.ItemEverydayTwoBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.an.better.netease.cloud.music.utils.Utils;
import com.bumptech.glide.Glide;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

import java.util.ArrayList;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class TwoViewHolder extends BaseVdbViewHolder<ArrayList<GanKInfo>, ItemEverydayTwoBinding> {

    public TwoViewHolder(ItemEverydayTwoBinding viewDataBinding) {
        super(viewDataBinding);
    }

    @Override
    public void bind(ArrayList<GanKInfo> cellModel, int position) {
        super.bind(cellModel, position);

        Glide.with(binding.ivTwoOneOne.getContext())
                .load(Utils.getTwoImageUrl())
                .into(binding.ivTwoOneOne);

        Glide.with(binding.ivTwoOneTwo.getContext())
                .load(Utils.getTwoImageUrl())
                .into(binding.ivTwoOneTwo);

        if(cellModel.size() >=2) {
            binding.tvTwoOneOneTitle.setText(cellModel.get(0).desc);
            binding.tvTwoOneTwoTitle.setText(cellModel.get(1).desc);
        }
    }

}
