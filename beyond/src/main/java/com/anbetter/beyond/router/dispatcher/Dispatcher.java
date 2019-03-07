package com.anbetter.beyond.router.dispatcher;

import android.net.Uri;
import android.os.Bundle;

import com.anbetter.log.MLog;
import com.anbetter.beyond.router.SwitchersProvider;
import com.anbetter.beyond.router.interfaces.IDispatcher;
import com.anbetter.beyond.router.interfaces.INavigation;
import com.anbetter.beyond.router.interfaces.ISwitcher;


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