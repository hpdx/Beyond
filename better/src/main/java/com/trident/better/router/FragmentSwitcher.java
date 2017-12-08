package com.trident.better.router;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.trident.better.router.interfaces.INavigation;
import com.trident.better.router.interfaces.ISwitcher;

/**
 *
 * Created by android_ls on 16/12/26.
 */

public abstract class FragmentSwitcher implements ISwitcher<Fragment> {

    public static final String DEFAULT = "";
    public static final String TITLE = "title";
    public static final String KEY_URL = "url";

    protected INavigation<Fragment> navigation;
    protected Uri uri;
    protected Bundle bundle;

    @Override
    public abstract Fragment generateInstance(Uri uri, Bundle bundle);

    @Override
    public void switchTo(INavigation<Fragment> nav, Uri uri, Bundle bundle) {
        this.uri = uri;
        this.navigation = nav;
        this.bundle = bundle;
        Fragment fragment = generateInstance(uri, bundle);
        if (fragment != null) {
            navigation.showPage(getPageName(), fragment);
        }
    }

    public String getTitle() {
        if (bundle != null && bundle.containsKey(TITLE)) {
            return bundle.getString(TITLE);
        }
        return DEFAULT;
    }

}
