package com.trident.beyond.tab;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.trident.beyond.R;
import com.trident.beyond.adapter.BaseListAdapter;
import com.trident.beyond.host.PageTabHost;
import com.trident.beyond.list.helper.BeyondListAdapter;
import com.trident.beyond.model.BaseListRequest;
import com.trident.beyond.model.TabData;
import com.trident.beyond.view.BaseListView;
import com.trident.beyond.viewmodel.BaseListViewModel;
import com.trident.beyond.widgets.BackToTopView;
import com.trident.beyond.widgets.PtrHeaderView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 *
 * Created by android_ls on 16/7/25.
 */
public abstract class BaseListTab<M extends BaseListRequest<?, ?>, V extends BaseListView<M>,
        VM extends BaseListViewModel<M, V>> extends MvvmTab<M, V, VM>
        implements BaseListView<M> {

    protected PtrFrameLayout mPtrFrameLayout;
    protected RecyclerView mRecyclerView;
    protected BaseListAdapter<M> mAdapter;
    protected BackToTopView mBackToTopView;
    protected PtrHeaderView mPtrHeaderView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected TabData tabData;
    protected PageTabHost mPageHost;

    private Parcelable mListState;
    private static final String RECYCLER_VIEW_STATE_KEY = "tab_recycler_view_state_key";
    protected Handler mHandler;

    public BaseListTab(Context context, TabData tabData) {
        super(context);
        this.mPageHost = tabData.mHost;
        this.tabData = tabData;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.page_list_fragment;
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if(viewModel != null) {
            viewModel.loadListData(pullToRefresh);
        }
    }

    @Override
    public void setData(M data) {
        rebindAdapter(data);
    }

    public void onRefreshComplete() {
        if(mPtrFrameLayout != null) {
            mPtrFrameLayout.refreshComplete();
        }
    }

    @SuppressWarnings("unchecked")
    protected BaseListAdapter createAdapter(M data) {
        return new BeyondListAdapter(data);
    }

    @SuppressWarnings("unchecked")
    protected void rebindAdapter(M data) {
        if (mAdapter == null || mRecyclerView.getAdapter() == null) {
            mAdapter = createAdapter(data);
            mRecyclerView.setAdapter(mAdapter);
            setOnItemClickListeners();
        } else {
            mAdapter.updateAdapterData(data);
        }
    }

    public void onViewCreated() {
        super.onViewCreated();
        mHandler = new Handler(Looper.getMainLooper());
        mPtrFrameLayout = findViewById(R.id.ptr_refresh_layout);
        setRefreshEnabled();

        mPtrHeaderView = new PtrHeaderView(mContext);
        mPtrFrameLayout.setHeaderView(mPtrHeaderView);
        mPtrFrameLayout.addPtrUIHandler(mPtrHeaderView);

        // 设置下拉刷新监听
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadData(true);
                    }
                }, 200);
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = getLayoutManager();
        if(mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
            mListState = null;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        addItemDecoration();
        decorateRecyclerView();

        mBackToTopView = findViewById(R.id.up_to_top);
        setBackToTopView();
    }

    /**
     * 若想禁用下拉刷新功能，请重写此方法
     * 示例：
     *  mPtrFrameLayout.setEnabled(false);
     */
    protected void setRefreshEnabled() {
        mPtrFrameLayout.setEnabled(true);
    }

    protected void decorateRecyclerView() {
//        RecycleViewItemInfoHelper.printItemInfo(mRecyclerView);
    }

    /***
     * Cell 之间的分割线
     */
    protected void addItemDecoration() {

    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    public void setBackToTopView() {
        mBackToTopView.setRecyclerView(mRecyclerView);
        mBackToTopView.setVisiblePosition(mContext.getResources().getInteger(R.integer.up_to_top_count));
    }

    @Override
    public Bundle onSaveInstanceState() {
        Bundle savedInstanceState = new Bundle();
        savedInstanceState.putParcelable(RECYCLER_VIEW_STATE_KEY, mLayoutManager.onSaveInstanceState());
        return savedInstanceState;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        mListState = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE_KEY);
    }

    @Override
    public void onActivityCreated() {
        viewModel.setList((M) tabData.dataSource);
        loadData(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPtrFrameLayout != null) {
            if(mPtrHeaderView != null) {
                mPtrFrameLayout.removeView(mPtrHeaderView);
                mPtrHeaderView = null;
            }

            mPtrFrameLayout = null;
        }

        if (mAdapter != null) {
            mAdapter.recycler();
            mAdapter = null;
        }

        if(mRecyclerView != null) {
            mRecyclerView.setAdapter(null);
            mRecyclerView = null;
        }
    }

    @Override
    public void onDestroy() {
        tabData = null;
        mListState = null;
        mLayoutManager = null;
        super.onDestroy();
    }

    /**
     * 凡是列表界面，想设置列表中Item View的各种事件处理监听器，请在子类中重写该个方法
     * <p>
     * mAdapter.setOnViewCallback(this);
     * mAdapter.setOnItemClickListener(this);
     * mAdapter.setOnItemClickListener2(this)
     */
    protected void setOnItemClickListeners() {

    }

    @Override
    public void showBannerTips(String message) {
        if (mPageHost != null) {
            mPageHost.showBannerTips(message);
        }
    }

}
