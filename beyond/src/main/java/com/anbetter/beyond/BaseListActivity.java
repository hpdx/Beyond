package com.anbetter.beyond;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.anbetter.beyond.adapter.BaseListAdapter;
import com.anbetter.beyond.helper.BeyondListAdapter;
import com.anbetter.beyond.model.BaseListRequest;
import com.anbetter.beyond.mvvm.MvvmBaseActivity;
import com.anbetter.beyond.view.BaseListView;
import com.anbetter.beyond.viewmodel.BaseListViewModel;
import com.anbetter.beyond.widgets.BackToTopView;
import com.facebook.fresco.helper.utils.DensityUtil;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * <p>
 * Created by android_ls on 2018/11/7.
 *
 * @author 李松
 * @version 1.0
 */
public abstract class BaseListActivity<M extends BaseListRequest<?, ?>, V extends BaseListView<M>, VM extends BaseListViewModel<M, V>>
        extends MvvmBaseActivity<M, V, VM> implements BaseListView<M> {

    private static final String RECYCLER_VIEW_STATE_KEY = "recycler_view_state_key";

    protected PtrFrameLayout mPtrFrameLayout;
    protected RecyclerView mRecyclerView;
    protected BackToTopView mBackToTopView;
    protected PtrUIHandler mPtrHeaderView;

    protected M mList;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected BaseListAdapter mAdapter;
    private Parcelable mListState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.ptr_refresh_layout);
        if (mPtrFrameLayout != null) {
            setPtrUIHandler(this);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = createLayoutManager();
        mRecyclerView.setLayoutManager(mLayoutManager);
        addItemDecoration();

        if (showBackToTopView()) {
            mBackToTopView = (BackToTopView) findViewById(R.id.up_to_top);
            setupBackToTopView();
        }

        initData();
        if (mList == null) {
            mList = getList();
        }
        viewModel.setList(mList);

        if (isAutoLoadData()) {
            loadData();
        }
    }

    /**
     * 处理是否自动加载请求
     * modify by 王刚
     */
    protected boolean isAutoLoadData() {
        return true;
    }

    /**
     * 初始化变量、解析传递参数在该方法中处理
     */
    protected void initData() {

    }

    @Override
    protected boolean hasStatusLayout() {
        return true;
    }

    @Override
    protected int getContentLayoutRes() {
        return R.layout.activity_list_layout;
    }

    protected abstract M getList();

    protected void setPtrUIHandler(Context context) {
//        mPtrHeaderView = new PtrHeaderView(context);
        mPtrHeaderView = getPullRefreshHeaderView();
        mPtrFrameLayout.setHeaderView((View)mPtrHeaderView);
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

    protected PtrUIHandler getPullRefreshHeaderView() {
        // mPtrHeaderView = new PtrHeaderView(mContext);

        final MaterialHeader header = new MaterialHeader(this);
        int[] colors = this.getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtil.dipToPixels(this, 15), 0, DensityUtil.dipToPixels(this, 10));
        header.setPtrFrameLayout(mPtrFrameLayout);

        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setDurationToCloseHeader(500);
        return header;
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
    protected void addItemDecoration() {

    }

    public void loadData() {
        loadData(false);
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        recordState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        restoreState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
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
        return false;
    }

    @Override
    public void onDestroy() {
        if (mPtrFrameLayout != null) {
            if (mPtrHeaderView != null) {
                mPtrFrameLayout.removeView((View)mPtrHeaderView);
                mPtrFrameLayout.removePtrUIHandler(mPtrHeaderView);
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
     * mAdapter.setOnItemClickListener(this);
     */
    protected void setOnItemClickListeners() {

    }

}
