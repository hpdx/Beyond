package com.gank.day.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gank.common.model.GanKInfo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by android_ls on 2016/12/29.
 */

public class GanKDayInfo implements Parcelable {

    @SerializedName("Android")
    public ArrayList<GanKInfo> Android;

    public ArrayList<GanKInfo> iOS;

    public ArrayList<GanKInfo> App;

    public ArrayList<GanKInfo> 前端;

    public ArrayList<GanKInfo> 休息视频;

    public ArrayList<GanKInfo> 福利;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.Android);
        dest.writeTypedList(this.iOS);
        dest.writeTypedList(this.App);
        dest.writeTypedList(this.前端);
        dest.writeTypedList(this.休息视频);
        dest.writeTypedList(this.福利);
    }

    public GanKDayInfo() {
    }

    protected GanKDayInfo(Parcel in) {
        this.Android = in.createTypedArrayList(GanKInfo.CREATOR);
        this.iOS = in.createTypedArrayList(GanKInfo.CREATOR);
        this.App = in.createTypedArrayList(GanKInfo.CREATOR);
        this.前端 = in.createTypedArrayList(GanKInfo.CREATOR);
        this.休息视频 = in.createTypedArrayList(GanKInfo.CREATOR);
        this.福利 = in.createTypedArrayList(GanKInfo.CREATOR);
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
