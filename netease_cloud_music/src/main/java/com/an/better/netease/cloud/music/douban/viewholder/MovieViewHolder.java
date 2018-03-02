package com.an.better.netease.cloud.music.douban.viewholder;

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

        binding.setSubjectsBean(cellModel);
        binding.executePendingBindings();
    }

}
