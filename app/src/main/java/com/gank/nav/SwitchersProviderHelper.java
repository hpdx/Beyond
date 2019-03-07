package com.gank.nav;

import com.anbetter.beyond.router.SwitchersProvider;
import com.gank.nav.switchers.BlankSwitcher;
import com.gank.nav.switchers.GanKMultiTypeTabSwitcher;
import com.gank.nav.switchers.GanKTabListSwitcher;
import com.gank.nav.switchers.GanKTabPagingListSwitcher;
import com.gank.nav.switchers.GankDaySwitcher;
import com.gank.nav.switchers.GankListSwitcher;
import com.gank.nav.switchers.GankPagingListSwitcher;
import com.gank.nav.switchers.WebSwitcher;

/**
 * Created by android_ls on 2016/12/26.
 */

public final class SwitchersProviderHelper {

    /**
     * hd://dating.app/blank
     */
    public final static String PAGE_NAME_BLANK = "blank";

    /**
     * hd://dating.app/web
     */
    public final static String PAGE_NAME_WEB = "web";

    /**
     * hd://dating.app/gank_list
     */
    public final static String PAGE_NAME_GANK_LIST = "gank_list";

    /**
     * hd://dating.app/gank_paging_list
     */
    public final static String PAGE_NAME_GANK_PAGING_LIST = "gank_paging_list";

    /**
     * hd://dating.app/gank_day
     */
    public final static String PAGE_NAME_GANK_DAY = "gank_day";

    /**
     * hd://dating.app/gank_tab_list
     */
    public final static String PAGE_NAME_GANK_TAB_LIST = "gank_tab_list";

    /**
     * hd://dating.app/gank_tab_paging_list
     */
    public final static String PAGE_NAME_GANK_TAB_PAGING_LIST = "gank_tab_paging_list";

    /**
     * hd://dating.app/gank_multi_type_tab
     */
    public final static String PAGE_NAME_GANK_MULTI_TYPE_TAB = "gank_multi_type_tab";

    public static void register() {
        SwitchersProvider.addSwitcher(PAGE_NAME_BLANK, new BlankSwitcher());
        SwitchersProvider.addSwitcher(PAGE_NAME_WEB, new WebSwitcher());
        SwitchersProvider.addSwitcher(PAGE_NAME_GANK_LIST, new GankListSwitcher());
        SwitchersProvider.addSwitcher(PAGE_NAME_GANK_PAGING_LIST, new GankPagingListSwitcher());
        SwitchersProvider.addSwitcher(PAGE_NAME_GANK_DAY, new GankDaySwitcher());
        SwitchersProvider.addSwitcher(PAGE_NAME_GANK_TAB_LIST, new GanKTabListSwitcher());
        SwitchersProvider.addSwitcher(PAGE_NAME_GANK_TAB_PAGING_LIST, new GanKTabPagingListSwitcher());
        SwitchersProvider.addSwitcher(PAGE_NAME_GANK_MULTI_TYPE_TAB, new GanKMultiTypeTabSwitcher());

    }

}
