package com.an.better.netease.cloud.music.douban.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by android_ls on 2018/3/6.
 */

public class CastsBean implements Parcelable {

    public static final Creator<CastsBean> CREATOR = new Creator<CastsBean>() {
        @Override
        public CastsBean createFromParcel(Parcel source) {
            return new CastsBean(source);
        }

        @Override
        public CastsBean[] newArray(int size) {
            return new CastsBean[size];
        }
    };
    /**
     * alt : https://movie.douban.com/celebrity/1274761/
     * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1489386626.47.jpg"}
     * name : 张译
     * id : 1274761
     */

    public String alt;
    public ImagesBean avatars;
    public String name;
    public String id;

    public CastsBean() {
    }

    protected CastsBean(Parcel in) {
        this.alt = in.readString();
        this.avatars = in.readParcelable(ImagesBean.class.getClassLoader());
        this.name = in.readString();
        this.id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.alt);
        dest.writeParcelable(this.avatars, flags);
        dest.writeString(this.name);
        dest.writeString(this.id);
    }
}
