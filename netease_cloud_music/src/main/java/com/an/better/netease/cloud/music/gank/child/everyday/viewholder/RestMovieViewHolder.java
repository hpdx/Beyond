package com.an.better.netease.cloud.music.gank.child.everyday.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.an.better.netease.cloud.music.databinding.ItemEverydayOneBinding;
import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;
import com.an.better.netease.cloud.music.utils.Utils;
import com.bumptech.glide.Glide;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

import java.util.ArrayList;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class RestMovieViewHolder extends BaseVdbViewHolder<ArrayList<GanKInfo>, ItemEverydayOneBinding> {

    public RestMovieViewHolder(ItemEverydayOneBinding viewDataBinding) {
        super(viewDataBinding);
    }

    @Override
    public void bind(ArrayList<GanKInfo> cellModel, int position) {
        super.bind(cellModel, position);

        if(cellModel.size() > 0) {
            binding.tvOnePhotoTitle.setVisibility(View.VISIBLE);
            binding.tvOnePhotoTitle.setText(cellModel.get(0).desc);
        }

        binding.ivOnePhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(binding.ivOnePhoto.getContext())
                .load(Utils.getTwoImageUrl())
                .into(binding.ivOnePhoto);
    }

}
