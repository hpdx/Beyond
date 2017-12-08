package com.gank.nav.switchers;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gank.nav.SwitchersProviderHelper;
import com.gank.tab.multi.type.MultiTypeTabFragment;
import com.trident.better.router.FragmentSwitcher;


/**
 *
 *  Created by android_ls on 2017/1/3.
 */

public class GanKMultiTypeTabSwitcher extends FragmentSwitcher {

    @Override
    public String getPageName() {
        return SwitchersProviderHelper.PAGE_NAME_GANK_MULTI_TYPE_TAB;
    }

    @Override
    public Fragment generateInstance(Uri uri, Bundle bundle) {
        return MultiTypeTabFragment.newInstance();
    }

}
