package com.gank.list.viewmodel;

import com.gank.list.model.GanKListRequest;
import com.gank.list.view.GankListView;
import com.trident.beyond.viewmodel.BaseListViewModel;

/**
 * Created by android_ls on 2016/12/28.
 */

public class GankListViewModel extends BaseListViewModel<GanKListRequest, GankListView> {

    @Override
    public boolean needsToRefresh() {
        return false;
    }

    @Override
    public boolean needsClearState() {
        return false;
    }
}
