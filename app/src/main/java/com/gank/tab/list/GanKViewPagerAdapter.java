package com.gank.tab.list;

import android.content.Context;

import com.gank.list.model.GanKListRequest;
import com.trident.beyond.adapter.BaseViewPagerAdapter;
import com.trident.beyond.model.TabData;

import java.util.List;

/**
 * Created by android_ls on 2017/1/3.
 */

public class GanKViewPagerAdapter extends BaseViewPagerAdapter<GanKListRequest, GanKListTab> {

    public GanKViewPagerAdapter(Context context, List<TabData> tabDataList) {
        super(context, tabDataList);
    }

    @Override
    public GanKListRequest getDataSource(TabData tabData) {
        return new GanKListRequest();
    }

    @Override
    public GanKListTab getTabLayout(Context context, TabData tabData) {
        return new GanKListTab(context, tabData);
    }

}
