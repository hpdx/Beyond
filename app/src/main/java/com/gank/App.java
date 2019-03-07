package com.gank;

import android.app.Application;

import com.anbetter.beyond.network.OKHttpHelper;
import com.anbetter.log.MLog;
import com.facebook.fresco.helper.Phoenix;
import com.gank.common.ViewHolderProviderHelper;
import com.gank.nav.SwitchersProviderHelper;

/**
 * Created by android_ls on 2016/12/25.
 */

public class App extends Application {

    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        MLog.init(BuildConfig.DEBUG, "MLog");
        Phoenix.init(this);
        OKHttpHelper.init();
        SwitchersProviderHelper.register();
        ViewHolderProviderHelper.register();

    }

    public static App get() {
        return mApp;
    }


}
