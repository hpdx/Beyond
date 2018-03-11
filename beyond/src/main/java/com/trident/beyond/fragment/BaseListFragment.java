package com.trident.beyond.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.trident.beyond.R;
import com.trident.beyond.adapter.BaseListAdapter;
import com.trident.beyond.list.helper.BeyondListAdapter;
import com.trident.beyond.model.BaseListRequest;
import com.trident.beyond.view.BaseListView;
import com.trident.beyond.viewmodel.BaseListViewModel;
import com.trident.beyond.widgets.BackToTopView;
import com.trident.beyond.widgets.PtrHeaderView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by android_ls on 16/7/22.
 */
public abstract class BaseListFragment<M extends BaseListRequest<?, ?>, V extends BaseListView<M>, VM extends BaseListViewModel<M, V>>
        extends MvvmPageFragment<M, V, VM> implements BaseListView<M> {

    private static final String RECYCLER_VIEW_STATE_KEY = "recycler_view_state_key";

    protected PtrFrameLayout mPtrFrameLayout;
    protected RecyclerView mRecyclerView;
    protected BackToTopView mBackToTopView;
    protected PtrHeaderView mPtrHeaderView;

    protected M mList;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected BaseListAdapter mAdapter;
    private Parcelable mListState;

    @Override
    protected int getLayoutRes() {
        return R.layout.page_list_fragment;
    }

    @Override
    public void rebindActionBar() {
        mPageFragmentHost.toggleActionBar(true);
        mPageFragmentHost.setActionBarTitle("");
        mPageFragmentHost.displayActionBarBack(false);
    }

    protected abstract M getList();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPtrFrameLayout = (PtrFrameLayout) view.findViewById(R.id.ptr_refresh_layout);
        if (mPtrFrameLayout != null) {
            mPtrHeaderView = new PtrHeaderView(view.getContext());
            mPtrFrameLayout.setHeaderView(mPtrHeaderView);
            mPtrFrameLayout.addPtrUIHandler(mPtrHeaderView);

            // 设置下拉刷新监听
            mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onPullRefreshComplete();
                        }
                    }, 200);
                }
            });
        }

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLayoutManager = createLayoutManager();
        mRecyclerView.setLayoutManager(mLayoutManager);
        addItemDecoration(mRecyclerView);

        if (showBackToTopView()) {
            mBackToTopView = (BackToTopView) view.findViewById(R.id.up_to_top);
            setupBackToTopView();
        }
    }

    public void onPullRefreshComplete() {
        loadData(true);
    }

    /**
     * 下拉刷新，会显示下拉的状态
     */
    public void autoRefresh() {
        if (mPtrFrameLayout != null) {
            mPtrFrameLayout.autoRefresh();
        }
    }

    protected void decorateRecyclerView() {
        // RecycleViewItemInfoHelper.printItemInfo(mRecyclerView);
    }

    /***
     * Cell 之间的分割线
     */
    protected void addItemDecoration(RecyclerView recyclerView) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mList == null) {
            mList = getList();
        }
        viewModel.setList(mList);
        loadData();
    }

    /**
     * 页面刚初始化时，进行数据加载.
     */
    public void loadData() {
        loadData(false);
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    /**
     * @param pullToRefresh 是否是下拉刷新当前页面
     */
    @Override
    public void loadData(boolean pullToRefresh) {
        if (viewModel != null) {
            viewModel.loadListData(pullToRefresh);
        }
    }

    @Override
    public void setData(M data) {
        rebindAdapter(data);
    }

    public void onRefreshComplete() {
        if (mPtrFrameLayout != null) {
            mPtrFrameLayout.refreshComplete();
        }
    }

    /**
     * 禁用下拉刷新功能
     */
    public void setRefreshEnabled(boolean enabled) {
        if (mPtrFrameLayout != null) {
            mPtrFrameLayout.setEnabled(enabled);
        }
    }

    @SuppressWarnings("unchecked")
    protected BaseListAdapter createAdapter(M data) {
        return new BeyondListAdapter(data);
    }

    @SuppressWarnings("unchecked")
    private void rebindAdapter(M data) {
        if (mAdapter == null || mRecyclerView.getAdapter() == null) {
            mAdapter = createAdapter(data);
            mRecyclerView.setAdapter(mAdapter);
            setOnItemClickListeners();
        } else {
            mAdapter.updateAdapterData(data);
        }
    }

    protected void recordState(Bundle outState) {
        outState.putParcelable(RECYCLER_VIEW_STATE_KEY, mLayoutManager.onSaveInstanceState());
    }

    protected void restoreState(Bundle savedInstanceState) {
        mListState = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE_KEY);
        if (mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
            mListState = null;
        }
    }

    public void setupBackToTopView() {
        if (mBackToTopView != null) {
            mBackToTopView.setRecyclerView(mRecyclerView);
            mBackToTopView.setVisiblePosition(getResources().getInteger(R.integer.up_to_top_count));
        }
    }

    /**
     * 是否显示从底部返回到顶部的按钮，默认显示
     *
     * @return
     */
    public boolean showBackToTopView() {
        return true;
    }

    @Override
    public void onDestroyView() {
        if (mPtrFrameLayout != null) {
            if (mPtrHeaderView != null) {
                mPtrFrameLayout.removeView(mPtrHeaderView);
                mPtrHeaderView = null;
            }
            mPtrFrameLayout = null;
        }

        if (mRecyclerView != null) {
            mRecyclerView.clearOnScrollListeners();
            mRecyclerView.setAdapter(null);
            mRecyclerView = null;
        }

        if (mAdapter != null) {
            mAdapter.recycler();
            mAdapter = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mListState = null;
        mLayoutManager = null;
        super.onDestroy();
    }

    /**
     * 该方法请慎重调用，执行过后会清空如下数据及状态：
     * 1、当前列表中item滚动的位置
     * 2、当前Page的数据源
     * <p/>
     * 推荐在子类的{@link #onDestroy()}中调用，示例如下
     *
     * @Override public void onDestroy() {
     * super.onDestroy();
     * // 重置当前Page的所有状态
     * resetInstanceState();
     * }
     */
    protected void resetInstanceState() {
        mList = null;
    }

    /**
     * 滚动到列表底部
     */
    protected void scrollToBottom() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mRecyclerView != null && mAdapter != null) {
                    mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                }
            }
        }, 300);
    }

    /**
     * 滚动到顶部
     */
    protected void scrollToTop() {
        if (mRecyclerView != null && mAdapter != null) {
            mRecyclerView.scrollToPosition(0);
        }
    }

    /**
     * 凡是列表界面，像设置列表中Item View的各种事件处理监听器，请在子类中重写该个方法
     * <p>
     * mAdapter.setOnViewCallback(this);
     * mAdapter.setOnItemClickListener(this);
     * mAdapter.setOnItemClickListener2(this)
     */
    protected void setOnItemClickListeners() {

    }

}
