package com.gank.tab.multi.type.tab;

import android.content.Context;

import com.gank.day.model.GanKDayRequest;
import com.gank.day.view.GanKDayView;
import com.gank.day.viewmodel.GanKDayListViewModel;
import com.trident.beyond.model.TabData;
import com.trident.beyond.tab.BaseListTab;

/**
 * Created by android_ls on 2017/1/3.
 */

public class GanKDayListTab extends BaseListTab<GanKDayRequest, GanKDayView, GanKDayListViewModel> {

    public GanKDayListTab(Context context, TabData tabData) {
        super(context, tabData);
    }

    @Override
    protected GanKDayListViewModel createViewModel() {
        return new GanKDayListViewModel();
    }

}
