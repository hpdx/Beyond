package com.an.better.netease.cloud.music.gank.child.everyday.model;


import com.trident.beyond.core.IModel;

/**
 * Created by android_ls on 2016/12/29.
 */

public class GanKDayCategory implements IModel {

    public String title;

    public int resId;

    public GanKDayCategory(String title) {
        this.title = title;
    }

    public GanKDayCategory(String title, int resId) {
        this.title = title;
        this.resId = resId;
    }

}
