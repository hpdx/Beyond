package com.an.better.netease.cloud.music.gank.child.welfare;

import android.os.Parcel;
import android.os.Parcelable;

import com.an.better.netease.cloud.music.gank.child.everyday.model.GanKInfo;

import java.util.ArrayList;

/**
 * Created by android_ls on 2018/2/23.
 */

public class GankDataBlock implements Parcelable {

    public  boolean error;

    public ArrayList<GanKInfo> results;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.results);
    }

    public GankDataBlock() {
    }

    protected GankDataBlock(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = in.createTypedArrayList(GanKInfo.CREATOR);
    }

    public static final Parcelable.Creator<GankDataBlock> CREATOR = new Parcelable.Creator<GankDataBlock>() {
        @Override
        public GankDataBlock createFromParcel(Parcel source) {
            return new GankDataBlock(source);
        }

        @Override
        public GankDataBlock[] newArray(int size) {
            return new GankDataBlock[size];
        }
    };
}
