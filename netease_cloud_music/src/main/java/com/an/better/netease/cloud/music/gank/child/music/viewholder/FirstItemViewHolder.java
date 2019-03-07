package com.an.better.netease.cloud.music.gank.child.music.viewholder;

import com.an.better.netease.cloud.music.databinding.FirstPublishItemBinding;
import com.an.better.netease.cloud.music.gank.child.music.model.ResultBean;
import com.anbetter.beyond.viewholder.BaseVdbViewHolder;

/**
 *
 * Created by android_ls on 2018/2/12.
 */

public class FirstItemViewHolder extends BaseVdbViewHolder<ResultBean, FirstPublishItemBinding> {

    public FirstItemViewHolder(FirstPublishItemBinding viewDataBinding) {
        super(viewDataBinding);
    }

    @Override
    public void bind(ResultBean cellModel, int position) {
        super.bind(cellModel, position);
        binding.setModel(cellModel);
        binding.executePendingBindings();
    }

}
