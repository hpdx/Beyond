package com.gank.databinding;

import android.databinding.BindingAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.ImageLoader;

/**
 * Created by android_ls on 16/12/28.
 */
public class ImageBindingAdapter {

    @BindingAdapter({"url"})
    public static void loadImage(SimpleDraweeView simpleDraweeView, String url) {
        ImageLoader.loadImage(simpleDraweeView, url);
    }

    @BindingAdapter({"url_small"})
    public static void loadImageSmall(SimpleDraweeView simpleDraweeView, String url) {
        ImageLoader.loadImageSmall(simpleDraweeView, url);
    }

}
