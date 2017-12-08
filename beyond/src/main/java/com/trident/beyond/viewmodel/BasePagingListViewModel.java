package com.trident.beyond.viewmodel;

import com.trident.beyond.model.BasePaginatedListRequest;
import com.trident.beyond.view.BasePagingListView;

/**
 * Created by android_ls on 16/7/4.
 */
public abstract class BasePagingListViewModel<M extends BasePaginatedListRequest<?, ?>,
        V extends BasePagingListView<M>> extends BaseListViewModel<M, V> {

}
