package com.trident.better.router.dispatcher;

import android.net.Uri;
import android.os.Bundle;

import com.anbetter.log.MLog;
import com.trident.better.router.interfaces.IDispatcher;
import com.trident.better.router.interfaces.INavigation;

/**
 *
 * Created by android_ls on 2016/12/26.
 */

public class ErrorDispatcher implements IDispatcher {

    @Override
    public void dispatch(INavigation navigation, Uri uri, Bundle bundle) {
        // handle error
        MLog.i("-----------ErrorDispatcher---------");

    }

}
