package com.trident.beyond.fragment;


import com.trident.beyond.adapter.BaseListAdapter;
import com.trident.beyond.list.helper.BeyondPagingListAdapter;
import com.trident.beyond.model.BasePaginatedListRequest;
import com.trident.beyond.view.BasePagingListView;
import com.trident.beyond.viewmodel.BasePagingListViewModel;

/**
 * 基于Fragment的单个界面，数据需要分页加载，请继承此类。
 * <p/>
 * Created by android_ls on 16/6/30.
 */
public abstract class BasePagingListFragment<M extends BasePaginatedListRequest<?, ?>, V extends BasePagingListView<M>, VM extends BasePagingListViewModel<M, V>>
        extends BaseListFragment<M, V, VM> {

    @SuppressWarnings("unchecked")
    protected BaseListAdapter createAdapter(M data) {
        return new BeyondPagingListAdapter(data);
    }

}
