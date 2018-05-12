package com.dating.jd_home.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by android_ls on 2018/3/21.
 */

public class HomeIndex implements Parcelable {

    public String code;
    public List<ItemInfoListBean> itemInfoList;

    public HomeIndex() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeTypedList(this.itemInfoList);
    }

    protected HomeIndex(Parcel in) {
        this.code = in.readString();
        this.itemInfoList = in.createTypedArrayList(ItemInfoListBean.CREATOR);
    }

    public static final Creator<HomeIndex> CREATOR = new Creator<HomeIndex>() {
        @Override
        public HomeIndex createFromParcel(Parcel source) {
            return new HomeIndex(source);
        }

        @Override
        public HomeIndex[] newArray(int size) {
            return new HomeIndex[size];
        }
    };
}
