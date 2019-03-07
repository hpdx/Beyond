package com.an.better.netease.cloud.music.douban.model;

import com.anbetter.beyond.model.IModel;

/**
 * Created by android_ls on 2018/3/2.
 */

public class HotMovieTitle implements IModel {

    public String title;

    public String icon = "http://ojyz0c8un.bkt.clouddn.com/one_01.png";

    public HotMovieTitle(String title) {
        this.title = title;
    }

}
