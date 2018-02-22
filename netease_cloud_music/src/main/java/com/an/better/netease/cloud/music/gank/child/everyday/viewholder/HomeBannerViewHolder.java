package com.an.better.netease.cloud.music.gank.child.everyday.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.an.better.netease.cloud.music.R;
import com.an.better.netease.cloud.music.gank.child.everyday.model.ting.BannerBean;
import com.an.better.netease.cloud.music.gank.child.everyday.model.ting.FocusBean;
import com.an.better.netease.cloud.music.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;


/**
 *
 * Created by android_ls on 2018/2/9.
 */

public class HomeBannerViewHolder extends RecyclerView.ViewHolder {

    private Banner mBannerView;
    public HomeBannerViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        super(inflater.inflate(R.layout.home_banner_items, parent, false));
        mBannerView = itemView.findViewById(R.id.banner);
        mBannerView.setIndicatorGravity(BannerConfig.RIGHT);
        mBannerView.setImageLoader(new GlideImageLoader());
    }

    public void bind(FocusBean focusBean, int position) {
        if (focusBean.result != null && focusBean.result.size() > 0) {
            ArrayList<String> bannerImages = new ArrayList<>();
            for (int i = 0; i < focusBean.result.size(); i++) {
                BannerBean bannerBean = focusBean.result.get(i);
                bannerImages.add(bannerBean.randpic);
            }
            mBannerView.setImages(bannerImages).start();
        }
    }

}
