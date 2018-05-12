package com.dating.jd_home.home.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by android_ls on 2018/3/21.
 */

public class ItemContentListBean implements Parcelable {

    /**
     * imageUrl : https://m.360buyimg.com/mobilecms/s720x322_jfs/t4903/41/12296166/85214/15205dd6/58d92373N127109d8.jpg!q70.jpg
     * clickUrl : 男装超级品牌类日
     */

    public String imageUrl;
    public String clickUrl;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeString(this.clickUrl);
    }

    public ItemContentListBean() {
    }

    protected ItemContentListBean(Parcel in) {
        this.imageUrl = in.readString();
        this.clickUrl = in.readString();
    }

    public static final Parcelable.Creator<ItemContentListBean> CREATOR = new Parcelable.Creator<ItemContentListBean>() {
        @Override
        public ItemContentListBean createFromParcel(Parcel source) {
            return new ItemContentListBean(source);
        }

        @Override
        public ItemContentListBean[] newArray(int size) {
            return new ItemContentListBean[size];
        }
    };
}
