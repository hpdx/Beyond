package com.gank.paginglist.viewmodel;

import com.anbetter.beyond.viewmodel.BasePagingListViewModel;
import com.gank.paginglist.model.GanKPagingListRequest;
import com.gank.paginglist.view.GankPagingListView;

/**
 * Created by android_ls on 2016/12/28.
 */

public class GankPagingListViewModel extends BasePagingListViewModel<GanKPagingListRequest, GankPagingListView> {

    @Override
    public boolean needsToRefresh() {
        return false;
    }

    @Override
    public boolean needsClearState() {
        return false;
    }
}
