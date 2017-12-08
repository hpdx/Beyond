package com.trident.beyond.host;

import android.support.v4.app.FragmentManager;

/**
 * Tab所依赖的容器ViewPager所属页面，对Tab提供的接口
 *
 * Created by android_ls on 2017/8/7.
 */

public interface PageTabHost {

    void showBannerTips(String message);

    FragmentManager getChildFragmentManager();

}
