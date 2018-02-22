package com.an.better.netease.cloud.music.gank.child.everyday.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by android_ls on 2016/12/29.
 */

public class GanKDayBlock implements Parcelable {

    public  boolean error;

    public GanKDayInfo results;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.results, flags);
    }

    public GanKDayBlock() {
    }

    protected GanKDayBlock(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = in.readParcelable(GanKDayInfo.class.getClassLoader());
    }

    public static final Creator<GanKDayBlock> CREATOR = new Creator<GanKDayBlock>() {
        @Override
        public GanKDayBlock createFromParcel(Parcel source) {
            return new GanKDayBlock(source);
        }

        @Override
        public GanKDayBlock[] newArray(int size) {
            return new GanKDayBlock[size];
        }
    };
}
