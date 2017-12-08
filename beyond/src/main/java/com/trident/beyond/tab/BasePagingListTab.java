package com.trident.beyond.tab;

import android.content.Context;

import com.trident.beyond.adapter.BaseListAdapter;
import com.trident.beyond.list.helper.BeyondPagingListAdapter;
import com.trident.beyond.model.BasePaginatedListRequest;
import com.trident.beyond.model.TabData;
import com.trident.beyond.view.BasePagingListView;
import com.trident.beyond.viewmodel.BasePagingListViewModel;


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
