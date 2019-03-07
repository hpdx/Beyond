package com.gank.nav.switchers;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.anbetter.beyond.router.FragmentSwitcher;
import com.gank.BlankFragment;
import com.gank.nav.SwitchersProviderHelper;

/**
 *
 *  Created by android_ls on 2016/12/26.
 */

public class BlankSwitcher extends FragmentSwitcher {

    @Override
    public String getPageName() {
        return SwitchersProviderHelper.PAGE_NAME_BLANK;
    }

    @Override
    public Fragment generateInstance(Uri uri, Bundle bundle) {
        return BlankFragment.newInstance(bundle);
    }

}
