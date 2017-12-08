package com.trident.better.router.interfaces;

import android.net.Uri;
import android.os.Bundle;

/**
 *
 * Created by android_ls on 16/12/26.
 */

public interface ISwitcher<T> {

    String getPageName();

    T generateInstance(Uri uri, Bundle bundle);

    void switchTo(INavigation<T> navigation, Uri uri, Bundle bundle);
}
