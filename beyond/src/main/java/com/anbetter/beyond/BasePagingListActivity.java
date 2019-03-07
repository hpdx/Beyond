package com.anbetter.beyond;

import com.anbetter.beyond.adapter.BaseListAdapter;
import com.anbetter.beyond.helper.BeyondPagingListAdapter;
import com.anbetter.beyond.model.BasePaginatedListRequest;
import com.anbetter.beyond.view.BasePagingListView;
import com.anbetter.beyond.viewmodel.BasePagingListViewModel;

/**
 * <p>
 * Created by android_ls on 2018/11/7.
 *
 * @author 李松
 * @version 1.0
 */
public abstract class BasePagingListActivity<M extends BasePaginatedListRequest<?, ?>,
        V extends BasePagingListView<M>,
        VM extends BasePagingListViewModel<M, V>> extends BaseListActivity<M, V, VM> {

    @SuppressWarnings("unchecked")
    protected BaseListAdapter createAdapter(M data) {
        return new BeyondPagingListAdapter(data);
    }

}
