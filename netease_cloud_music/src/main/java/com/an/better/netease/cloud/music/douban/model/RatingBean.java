package com.an.better.netease.cloud.music.douban.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by android_ls on 2018/3/6.
 */

public class RatingBean implements Parcelable {

    /**
     * max : 10
     * average : 8.5
     * stars : 45
     * min : 0
     */

    public int max;
    public double average;
    public String stars;
    public int min;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.max);
        dest.writeDouble(this.average);
        dest.writeString(this.stars);
        dest.writeInt(this.min);
    }

    public RatingBean() {
    }

    protected RatingBean(Parcel in) {
        this.max = in.readInt();
        this.average = in.readDouble();
        this.stars = in.readString();
        this.min = in.readInt();
    }

    public static final Parcelable.Creator<RatingBean> CREATOR = new Parcelable.Creator<RatingBean>() {
        @Override
        public RatingBean createFromParcel(Parcel source) {
            return new RatingBean(source);
        }

        @Override
        public RatingBean[] newArray(int size) {
            return new RatingBean[size];
        }
    };
}
