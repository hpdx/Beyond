package com.dating.jd_home;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 *
 * Created by android_ls on 2018/3/13.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.printStackTrace();

        ARouter.init(this);


    }

}
