package com.dating.jd_test;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 *
 * Created by android_ls on 2018/3/13.
 */

public class TestApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);

    }

}
