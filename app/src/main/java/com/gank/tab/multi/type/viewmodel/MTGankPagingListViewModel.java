package com.gank.tab.multi.type.viewmodel;

import com.gank.tab.multi.type.model.MTGanKPagingListRequest;
import com.trident.beyond.view.BasePagingListView;
import com.trident.beyond.viewmodel.BasePagingListViewModel;

/**
 * Created by android_ls on 2016/12/28.
 */

public class MTGankPagingListViewModel extends BasePagingListViewModel<MTGanKPagingListRequest,
        BasePagingListView<MTGanKPagingListRequest>> {

    @Override
    public boolean needsToRefresh() {
        return false;
    }

    @Override
    public boolean needsClearState() {
        return false;
    }
}
