package com.gank.day.viewmodel;

import com.anbetter.beyond.viewmodel.BaseListViewModel;
import com.gank.day.model.GanKDayRequest;
import com.gank.day.view.GanKDayView;

public class GanKDayListViewModel extends BaseListViewModel<GanKDayRequest, GanKDayView> {

    @Override
    public boolean needsToRefresh() {
        return false;
    }

    @Override
    public boolean needsClearState() {
        return false;
    }

}