package com.an.better.netease.cloud.music.douban.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.douban.model.HotMovieTitle;
import com.anbetter.beyond.listener.OnItemClickListener;
import com.anbetter.beyond.viewholder.BaseViewHolder;
import com.bumptech.glide.Glide;

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

       itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(itemView, cellModel, position);
                }
            }
        });

        ImageView imageView = findViewById(R.id.iv_img);
        Glide.with(mContext)
                .load(cellModel.icon)
                .into(imageView);
    }

}
