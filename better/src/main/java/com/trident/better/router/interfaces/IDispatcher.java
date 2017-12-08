package com.trident.better.router.interfaces;

import android.net.Uri;
import android.os.Bundle;

/**
 *
 * Created by android_ls on 16/12/26.
 */

public interface IDispatcher {

    void dispatch(INavigation navigation, Uri uri, Bundle bundle);

}
