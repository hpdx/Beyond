package com.an.better.netease.cloud.music.douban.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.douban.model.HotMovieTitle;
import com.bumptech.glide.Glide;
import com.trident.beyond.listener.OnItemClickListener;
import com.trident.beyond.viewholder.BaseViewHolder;

/**
 * Created by android_ls on 2018/3/2.
 */

public class HeaderViewHolder extends BaseViewHolder<HotMovieTitle> {

    public HeaderViewHolder(View itemView, OnItemClickListener<HotMovieTitle> onItemClickListener) {
        super(itemView, onItemClickListener);
    }

    @Override
    public void bind(HotMovieTitle cellModel, int position) {
        super.bind(cellModel, position);
        ImageView imageView = findViewById(R.id.iv_img);
        Glide.with(mContext)
                .load(cellModel.icon)
                .into(imageView);
    }

}
