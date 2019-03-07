package com.gank.tab.multi.type.adapter;

import android.content.Context;

import com.anbetter.beyond.adapter.BaseViewPagerAdapter;
import com.anbetter.beyond.host.BinderTab;
import com.anbetter.beyond.model.TabData;
import com.gank.day.model.GanKDayRequest;
import com.gank.list.model.GanKListRequest;
import com.gank.paginglist.model.GanKPagingListRequest;
import com.gank.tab.list.GanKListTab;
import com.gank.tab.multi.type.model.MTGanKPagingListRequest;
import com.gank.tab.multi.type.tab.GanKDayListTab;
import com.gank.tab.multi.type.tab.MTGanKListTab;
import com.gank.tab.multi.type.tab.MTGanKPagingListTab;
import com.gank.tab.multi.type.tab.WebTab;
import com.gank.tab.paginglist.GanKPagingListTab;

import java.util.List;

/**
 * Created by android_ls on 2017/1/3.
 */

public class MultiTypeAdapter extends BaseViewPagerAdapter<Object, BinderTab> {

    public MultiTypeAdapter(Context context, List<TabData> tabDataList) {
        super(context, tabDataList);
    }

    @Override
    public Object getDataSource(TabData tabData) {
        if(tabData.type == 1) {
            // 多中类型的ViewHolder
            return new GanKDayRequest();
        }

        if(tabData.type == 2) {
            // List
            return new GanKListRequest();
        }

        if(tabData.type == 3) {
            // Paging List
            return new GanKPagingListRequest();
        }

        if(tabData.type == 4) {
            // web page
            return null;
        }

        if(tabData.type == 5) {
            // 手写List Adapter
            return new GanKListRequest();
        }

        if(tabData.type == 6) {
            // 手写Paging List Adapter
            return new MTGanKPagingListRequest();
        }


        return null;
    }



    @Override
    public BinderTab getTabLayout(Context context, TabData tabData) {
        if(tabData.type == 1) {
            // 多中类型的ViewHolder
            return new GanKDayListTab(context, tabData);
        }

        if(tabData.type == 2) {
            // List
            return new GanKListTab(context, tabData);
        }

        if(tabData.type == 3) {
            // Paging List
            return new GanKPagingListTab(context, tabData);
        }

        if(tabData.type == 4) {
            // web page
            return new WebTab(context, tabData);
        }

        if(tabData.type == 5) {
            // 手写List Adapter
            return new MTGanKListTab(context, tabData);
        }

        if(tabData.type == 6) {
            // 手写Paging List Adapter
            return new MTGanKPagingListTab(context, tabData);
        }


        return null;
    }

}
