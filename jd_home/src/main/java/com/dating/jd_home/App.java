package com.dating.jd_home;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.fresco.helper.Phoenix;

/**
 *
 * Created by android_ls on 2018/3/13.
 */

public class App extends Application {

    private static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.printStackTrace();

        ARouter.init(this);
        Phoenix.init(this);

    }

    public static App get() {
        return sApp;
    }

}
