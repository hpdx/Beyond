package com.gank.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by android_ls on 2016/12/28.
 */

public class GanKBlock implements Parcelable {
    public static final Creator<GanKBlock> CREATOR = new Creator<GanKBlock>() {
        @Override
        public GanKBlock createFromParcel(Parcel source) {
            return new GanKBlock(source);
        }

        @Override
        public GanKBlock[] newArray(int size) {
            return new GanKBlock[size];
        }
    };

    public boolean error;
    public ArrayList<GanKInfo> results;

    public GanKBlock() {
    }

    protected GanKBlock(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = in.createTypedArrayList(GanKInfo.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.results);
    }
}
