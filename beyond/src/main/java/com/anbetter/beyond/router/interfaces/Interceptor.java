package com.anbetter.beyond.router.interfaces;

import com.anbetter.beyond.router.model.Chain;

/**
 *
 * Created by android_ls on 16/12/26.
 */
public interface Interceptor {

    void intercept(INavigation navigation, Chain chain);
}
