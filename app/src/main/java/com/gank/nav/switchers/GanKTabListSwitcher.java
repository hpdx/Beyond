package com.gank.nav.switchers;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gank.nav.SwitchersProviderHelper;
import com.gank.tab.list.GanKViewPagerFragment;
import com.trident.better.router.FragmentSwitcher;


/**
 *
 *  Created by android_ls on 2017/1/3.
 */

public class GanKTabListSwitcher extends FragmentSwitcher {

    @Override
    public String getPageName() {
        return SwitchersProviderHelper.PAGE_NAME_GANK_TAB_LIST;
    }

    @Override
    public Fragment generateInstance(Uri uri, Bundle bundle) {
        return GanKViewPagerFragment.newInstance();
    }

}
