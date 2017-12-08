package com.gank.nav;

import android.os.Bundle;

import com.trident.better.router.Router;

/**
 * Created by android_ls on 2016/12/26.
 */

public class Navigator {

    public static void goToBlankFragment(String title) {
//        Router.navigation()
//                .setPageName(SwitchersProviderHelper.PAGE_NAME_BLANK)
//                .setTitle(title)
//                .start();

//        Router.navigation()
//                .setUri("hd://dating.app/blank")
//                .start();

//        Router.navigation()
//                .setUri("hd://dating.app/blank")
//                .setTitle(title)
//                .start();

        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("name", "小A");
        bundle.putInt("age", 28);

        Router.navigation()
                .setUri("hd://dating.app/blank")
                .setBundle(bundle)
                .start();
    }

    public static void goToWebFragment(String url) {
        Router.navigation()
                .setUri(url)
                .start();
    }

    public static void goToGanKListFragment() {
        Router.navigation()
                .setPageName(SwitchersProviderHelper.PAGE_NAME_GANK_LIST)
                .setTitle("不带分页的List示例")
                .start();
    }

    public static void goToGanKPagingListFragment() {
        Router.navigation()
                .setPageName(SwitchersProviderHelper.PAGE_NAME_GANK_PAGING_LIST)
                .setTitle("带分页的List示例")
                .start();
    }

    public static void goToGanKDayFragment() {
        Router.navigation()
                .setPageName(SwitchersProviderHelper.PAGE_NAME_GANK_DAY)
                .setTitle("多ViewHolder示例")
                .start();
    }

    public static void goToGanKTabListFragment() {
        Router.navigation()
                .setPageName(SwitchersProviderHelper.PAGE_NAME_GANK_TAB_LIST)
                .start();
    }

    public static void goToGanKTabPagingListFragment() {
        Router.navigation()
                .setPageName(SwitchersProviderHelper.PAGE_NAME_GANK_TAB_PAGING_LIST)
                .start();
    }

    public static void goToGanKMultiTypeTabFragment() {
        Router.navigation()
                .setPageName(SwitchersProviderHelper.PAGE_NAME_GANK_MULTI_TYPE_TAB)
                .start();
    }

    /**
     * 返回事件的处理
     *
     * 导航栏上的返回按钮和系统的返回键，默认已做过处理。
     * 提供该方法是为了满足其它应用场景，比如：用户使用手机号码进行登录，登录成功后，在自动切换到Home界面之前，
     * 需要将登录界面所占用的资源释放掉，请调用该方法。
     */
    public static void goBack() {
        Router.navigation().goBack();
    }

}
