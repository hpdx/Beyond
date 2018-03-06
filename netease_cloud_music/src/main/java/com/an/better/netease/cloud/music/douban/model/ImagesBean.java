package com.an.better.netease.cloud.music.douban.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by android_ls on 2018/3/6.
 */

public class ImagesBean implements Parcelable {
    /**
     * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg
     * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg
     * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2514175916.jpg
     */

    public String small;
    public String large;
    public String medium;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.small);
        dest.writeString(this.large);
        dest.writeString(this.medium);
    }

    public ImagesBean() {
    }

    protected ImagesBean(Parcel in) {
        this.small = in.readString();
        this.large = in.readString();
        this.medium = in.readString();
    }

    public static final Parcelable.Creator<ImagesBean> CREATOR = new Parcelable.Creator<ImagesBean>() {
        @Override
        public ImagesBean createFromParcel(Parcel source) {
            return new ImagesBean(source);
        }

        @Override
        public ImagesBean[] newArray(int size) {
            return new ImagesBean[size];
        }
    };
}
