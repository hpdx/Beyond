package com.trident.better.router.interfaces;

import com.trident.better.router.model.Chain;

/**
 *
 * Created by android_ls on 16/12/26.
 */
public interface Interceptor {

    void intercept(INavigation navigation, Chain chain);
}
