package com.gank.nav.navigation;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Created by android_ls on 2016/12/26.
 */
public class NavigationState implements Parcelable {

    public final String backStackName;
    public final String pageName;
    public boolean isActionBarOverlayEnabled;

    public static final String TOP_STATE = "top_state";

    public static final String NON_TOP_STATE = "non_top_state";

    public NavigationState(String pageName, String backStackName) {
        this.pageName = pageName;
        this.backStackName = backStackName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backStackName);
        dest.writeString(this.pageName);
        dest.writeByte(this.isActionBarOverlayEnabled ? (byte) 1 : (byte) 0);
    }

    protected NavigationState(Parcel in) {
        this.backStackName = in.readString();
        this.pageName = in.readString();
        this.isActionBarOverlayEnabled = in.readByte() != 0;
    }

    public static final Creator<NavigationState> CREATOR = new Creator<NavigationState>() {
        @Override
        public NavigationState createFromParcel(Parcel source) {
            return new NavigationState(source);
        }

        @Override
        public NavigationState[] newArray(int size) {
            return new NavigationState[size];
        }
    };
}
