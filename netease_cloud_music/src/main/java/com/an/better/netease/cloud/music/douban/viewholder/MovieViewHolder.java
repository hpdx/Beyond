package com.an.better.netease.cloud.music.douban.viewholder;

import android.view.View;

import com.an.better.netease.cloud.music.databinding.ItemListDoubanBinding;
import com.an.better.netease.cloud.music.douban.model.SubjectsBean;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.beyond.viewholder.BaseVdbViewHolder;

/**
 *
 * Created by android_ls on 2018/3/2.
 */

public class MovieViewHolder extends BaseVdbViewHolder<SubjectsBean, ItemListDoubanBinding> {

    public MovieViewHolder(ItemListDoubanBinding viewDataBinding, OnItemClickListener<SubjectsBean> listener) {
        super(viewDataBinding, listener);
    }

    @Override
    public void bind(SubjectsBean cellModel, int position) {
        super.bind(cellModel, position);

        binding.ivOnePhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(binding.ivOnePhoto, cellModel, position);
                }
            }
        });

        binding.setSubjectsBean(cellModel);
        binding.executePendingBindings();
    }

}
