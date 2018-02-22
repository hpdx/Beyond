package com.an.better.netease.cloud.music.gank.child.everyday.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by android_ls on 2016/12/29.
 */

public class GanKDayInfo implements Parcelable {

    @SerializedName("Android")
    public ArrayList<GanKInfo> android;

    @SerializedName("iOS")
    public ArrayList<GanKInfo> ios;

    @SerializedName("App")
    public ArrayList<GanKInfo> app;

    @SerializedName("前端")
    public ArrayList<GanKInfo> front;

    @SerializedName("休息视频")
    public ArrayList<GanKInfo> restMovie;

    @SerializedName("福利")
    public ArrayList<GanKInfo> welfare;

    @SerializedName("拓展资源")
    public ArrayList<GanKInfo> resource;

    @SerializedName("瞎推荐")
    public ArrayList<GanKInfo> recommend;

    public GanKDayInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.android);
        dest.writeTypedList(this.ios);
        dest.writeTypedList(this.app);
        dest.writeTypedList(this.front);
        dest.writeTypedList(this.restMovie);
        dest.writeTypedList(this.welfare);
        dest.writeTypedList(this.resource);
        dest.writeTypedList(this.recommend);
    }

    protected GanKDayInfo(Parcel in) {
        this.android = in.createTypedArrayList(GanKInfo.CREATOR);
        this.ios = in.createTypedArrayList(GanKInfo.CREATOR);
        this.app = in.createTypedArrayList(GanKInfo.CREATOR);
        this.front = in.createTypedArrayList(GanKInfo.CREATOR);
        this.restMovie = in.createTypedArrayList(GanKInfo.CREATOR);
        this.welfare = in.createTypedArrayList(GanKInfo.CREATOR);
        this.resource = in.createTypedArrayList(GanKInfo.CREATOR);
        this.recommend = in.createTypedArrayList(GanKInfo.CREATOR);
    }

    public static final Creator<GanKDayInfo> CREATOR = new Creator<GanKDayInfo>() {
        @Override
        public GanKDayInfo createFromParcel(Parcel source) {
            return new GanKDayInfo(source);
        }

        @Override
        public GanKDayInfo[] newArray(int size) {
            return new GanKDayInfo[size];
        }
    };
}
