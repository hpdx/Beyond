package com.gank.nav.switchers;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gank.nav.SwitchersProviderHelper;
import com.gank.web.WebFragment;
import com.trident.better.router.FragmentSwitcher;


/**
 *
 *  Created by android_ls on 2016/12/26.
 */

public class WebSwitcher extends FragmentSwitcher {

    @Override
    public String getPageName() {
        return SwitchersProviderHelper.PAGE_NAME_WEB;
    }

    @Override
    public Fragment generateInstance(Uri uri, Bundle bundle) {
        return WebFragment.newInstance(uri);
    }

}
