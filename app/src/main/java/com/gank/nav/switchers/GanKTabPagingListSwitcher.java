package com.gank.nav.switchers;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gank.nav.SwitchersProviderHelper;
import com.gank.tab.paginglist.GanKPagingListTabFragment;
import com.trident.better.router.FragmentSwitcher;


/**
 *
 *  Created by android_ls on 2017/1/3.
 */

public class GanKTabPagingListSwitcher extends FragmentSwitcher {

    @Override
    public String getPageName() {
        return SwitchersProviderHelper.PAGE_NAME_GANK_TAB_PAGING_LIST;
    }

    @Override
    public Fragment generateInstance(Uri uri, Bundle bundle) {
        return GanKPagingListTabFragment.newInstance();
    }

}
