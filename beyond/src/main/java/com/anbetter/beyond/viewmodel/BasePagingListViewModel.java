package com.anbetter.beyond.viewmodel;

import com.anbetter.beyond.model.BasePaginatedListRequest;
import com.anbetter.beyond.view.BasePagingListView;

/**
 * Created by android_ls on 16/7/4.
 */
public abstract class BasePagingListViewModel<M extends BasePaginatedListRequest<?, ?>,
        V extends BasePagingListView<M>> extends BaseListViewModel<M, V> {

}
