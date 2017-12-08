package com.gank.tab.list;

import android.content.Context;

import com.gank.list.model.GanKListRequest;
import com.gank.list.view.GankListView;
import com.gank.list.viewmodel.GankListViewModel;
import com.trident.beyond.model.TabData;
import com.trident.beyond.tab.BaseListTab;

/**
 * Created by android_ls on 2017/1/3.
 */

public class GanKListTab extends BaseListTab<GanKListRequest, GankListView, GankListViewModel> {

    public GanKListTab(Context context, TabData tabData) {
        super(context, tabData);
    }

    @Override
    protected GankListViewModel createViewModel() {
        return new GankListViewModel();
    }

}
