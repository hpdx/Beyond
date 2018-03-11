package com.an.better.netease.cloud.music.binding.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.an.better.netease.cloud.music.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.fresco.helper.utils.DensityUtil;

/**
 * Created by android_ls on 2018/3/2.
 */

public class ImageBindingAdapter {

    @BindingAdapter({"android:url"})
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    /**
     * 电影列表图片
     */
    @BindingAdapter("android:showMovieImg")
    public static void showMovieImg(ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
//                .centerCrop()
                .placeholder(R.drawable.img_default_movie)
                .override(DensityUtil.dip2px(imageView.getContext(), 125),
                        DensityUtil.dip2px(imageView.getContext(), 165));

        Glide.with(imageView.getContext())
                .load(url)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(imageView);
    }

    /**
     * 电影详情页显示高斯背景图
     */
    @BindingAdapter("android:showImgBg")
    public static void showImgBg(ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .optionalTransform(new BlurTransformation(imageView.getContext(), 23, 3))
                .placeholder(R.drawable.stackblur_default);

        Glide.with(imageView.getContext())
                .load(url)
                .apply(options)
                .into(imageView);
    }

    /**
     * 演员列表图片
     */
    @BindingAdapter("android:showImg")
    public static void showImg(ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.img_default_movie)
                .override(DensityUtil.dip2px(imageView.getContext(), 70),
                        DensityUtil.dip2px(imageView.getContext(), 70));

        Glide.with(imageView.getContext())
                .load(url)
                .apply(options)
                .into(imageView);
    }

}
