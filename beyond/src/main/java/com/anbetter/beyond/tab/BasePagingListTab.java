package com.anbetter.beyond.tab;

import android.content.Context;

import com.anbetter.beyond.adapter.BaseListAdapter;
import com.anbetter.beyond.helper.BeyondPagingListAdapter;
import com.anbetter.beyond.model.BasePaginatedListRequest;
import com.anbetter.beyond.model.TabData;
import com.anbetter.beyond.view.BasePagingListView;
import com.anbetter.beyond.viewmodel.BasePagingListViewModel;


/**
 * Created by android_ls on 16/7/27.
 */
public abstract class BasePagingListTab<M extends BasePaginatedListRequest<?, ?>, V extends BasePagingListView<M>,
        VM extends BasePagingListViewModel<M, V>> extends BaseListTab<M, V, VM> {

    public BasePagingListTab(Context context, TabData tabData) {
        super(context, tabData);
    }

    @SuppressWarnings("unchecked")
    protected BaseListAdapter createAdapter(M data) {
        return new BeyondPagingListAdapter(data);
    }


}
