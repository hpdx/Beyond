package com.gank.tab.multi.type.viewmodel;

import com.anbetter.beyond.view.BasePagingListView;
import com.anbetter.beyond.viewmodel.BasePagingListViewModel;
import com.gank.tab.multi.type.model.MTGanKPagingListRequest;

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
