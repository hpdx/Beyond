package com.dating.jd_common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by android_ls on 2018/3/13.
 */

public class TestParcelable implements Parcelable {

    public String title;
    public String desc;

    public TestParcelable(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "TestParcelable{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
    }

    public TestParcelable() {
    }

    protected TestParcelable(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
    }

    public static final Creator<TestParcelable> CREATOR = new Creator<TestParcelable>() {
        @Override
        public TestParcelable createFromParcel(Parcel source) {
            return new TestParcelable(source);
        }

        @Override
        public TestParcelable[] newArray(int size) {
            return new TestParcelable[size];
        }
    };
}
