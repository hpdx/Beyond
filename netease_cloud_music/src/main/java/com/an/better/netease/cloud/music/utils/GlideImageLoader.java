package com.an.better.netease.cloud.music.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by jingbin on 2016/11/30.
 * 首页轮播图
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

}
