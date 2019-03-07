package com.gank.tab.multi.type.tab;

import android.content.Context;

import com.anbetter.beyond.adapter.BaseListAdapter;
import com.anbetter.beyond.model.TabData;
import com.anbetter.beyond.tab.BaseListTab;
import com.gank.list.model.GanKListRequest;
import com.gank.list.view.GankListView;
import com.gank.list.viewmodel.GankListViewModel;
import com.gank.tab.multi.type.adapter.MTListAdapter;

/**
 * Created by android_ls on 2017/1/3.
 */

public class MTGanKListTab extends BaseListTab<GanKListRequest, GankListView, GankListViewModel> {

    public MTGanKListTab(Context context, TabData tabData) {
        super(context, tabData);
    }

    @Override
    protected GankListViewModel createViewModel() {
        return new GankListViewModel();
    }

    @Override
    protected BaseListAdapter createAdapter(GanKListRequest data) {
        return new MTListAdapter(data);
    }

}
