package com.gank.tab.paginglist;

import android.content.Context;

import com.gank.paginglist.model.GanKPagingListRequest;
import com.trident.beyond.adapter.BaseViewPagerAdapter;
import com.trident.beyond.model.TabData;

import java.util.List;

/**
 * Created by android_ls on 2017/1/3.
 */

public class GanKPagingListAdapter extends BaseViewPagerAdapter<GanKPagingListRequest, GanKPagingListTab> {

    public GanKPagingListAdapter(Context context, List<TabData> tabDataList) {
        super(context, tabDataList);
    }

    @Override
    public GanKPagingListRequest getDataSource(TabData tabData) {
        return new GanKPagingListRequest();
    }

    @Override
    public GanKPagingListTab getTabLayout(Context context, TabData tabData) {
        return new GanKPagingListTab(context, tabData);
    }
}
