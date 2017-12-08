package com.trident.better.router.dispatcher;

import android.net.Uri;
import android.os.Bundle;

import com.anbetter.log.MLog;
import com.trident.better.router.SwitchersProvider;
import com.trident.better.router.interfaces.IDispatcher;
import com.trident.better.router.interfaces.INavigation;
import com.trident.better.router.interfaces.ISwitcher;


/**
 *
 * Created by android_ls on 16/12/26.
 */

public class Dispatcher implements IDispatcher {

    private IDispatcher errorDispatcher = new ErrorDispatcher();

    @Override
    public void dispatch(INavigation navigation, Uri uri, Bundle bundle) {
        try {
            ISwitcher switcher = SwitchersProvider.getSwitcher(uri);
            if (switcher != null && navigation.canNavigate()) {
                switcher.switchTo(navigation, uri, bundle);
            }
        } catch (SwitchersProvider.PageNotFoundException e) {
            MLog.e("PageNotFound", e.getMessage());
            errorDispatcher.dispatch(navigation, uri, bundle);
        }
    }

}