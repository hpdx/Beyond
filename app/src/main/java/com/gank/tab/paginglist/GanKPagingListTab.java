package com.gank.tab.paginglist;

import android.content.Context;

import com.gank.paginglist.model.GanKPagingListRequest;
import com.gank.paginglist.view.GankPagingListView;
import com.gank.paginglist.viewmodel.GankPagingListViewModel;
import com.trident.beyond.model.TabData;
import com.trident.beyond.tab.BasePagingListTab;

/**
 * Created by android_ls on 2017/1/3.
 */

public class GanKPagingListTab extends BasePagingListTab<GanKPagingListRequest, GankPagingListView, GankPagingListViewModel> {

    public GanKPagingListTab(Context context, TabData tabData) {
        super(context, tabData);
    }

    @Override
    protected GankPagingListViewModel createViewModel() {
        return new GankPagingListViewModel();
    }

}
