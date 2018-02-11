package com.an.better.netease.cloud.music;

import android.app.Application;

import com.anbetter.log.MLog;

/**
 * Created by android_ls on 2018/1/26.
 */

public class App extends Application {

    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        MLog.init(BuildConfig.DEBUG, "MLog");



    }

    public static App get() {
        return mApp;
    }

}
