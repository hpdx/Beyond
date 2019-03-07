package com.anbetter.beyond.viewmodel;

import com.anbetter.beyond.listener.OnDataChangedListener;
import com.anbetter.beyond.listener.OnRefreshCompleteListener;
import com.anbetter.beyond.listener.ResponseErrorListener;
import com.anbetter.beyond.model.BaseListRequest;
import com.anbetter.beyond.mvvm.MvvmBaseViewModel;
import com.anbetter.beyond.view.BaseListView;

/**
 * Created by android_ls on 16/2/17.
 */
public abstract class BaseListViewModel<M extends BaseListRequest<?, ?>, V extends BaseListView<M>> extends MvvmBaseViewModel<M, V>
        implements OnDataChangedListener, ResponseErrorListener, OnRefreshCompleteListener {

    protected M mList;

    public void setList(M data) {
        this.mList = data;
        this.mList.addDataChangedListener(this);
        this.mList.addErrorListener(this);
        this.mList.addRefreshCompleteListener(this);
    }

    @Override
    public boolean isDataReady() {
        return mList.isReady();
    }

    @Override
    public boolean isEmpty() {
        return !mList.isReady();
    }

    /**
     * 列表数据请求.
     *
     * @param pullToRefresh
     */
    public void loadListData(boolean pullToRefresh) {
        if (isDataReady()) {
            if (isViewAttached()) {
                getView().setData(mList);
                getView().showContent();
            }

            if (needsToRefresh() || pullToRefresh) {
                mList.pullToRefreshItems();
                if (isViewAttached()) {
                    getView().showLoading(true);
                }
            }
        } else {
            if (pullToRefresh) {
                mList.pullToRefreshItems();
            } else {
                mList.loadItems();
            }

            if (isViewAttached()) {
                getView().showLoading(pullToRefresh);
            }
        }
    }

    @Override
    public void onDataChanged() {
        if (isViewAttached()) {
            getView().setData(mList);
            getView().showContent();

            if (mList.isPullToRefresh()) {
                getView().onRefreshComplete();
            }
        }

    }

    @Override
    public void onRefreshComplete() {
        if (isViewAttached()) {
            getView().onRefreshComplete();
        }
    }

    @Override
    public void onErrorResponse(Exception error) {
        if (isViewAttached()) {
            if (mList.isPullToRefresh()) {
                getView().showError(error, mList.isPullToRefresh());
                getView().onRefreshComplete();
            } else {
                if (!mList.isReady()) {
                    getView().showError(error, mList.isPullToRefresh());
                } else {
                    getView().setData(mList);
                    getView().showContent();
                }
            }
        }
    }

    public abstract boolean needsToRefresh();

    public abstract boolean needsClearState();

    public void clearState() {
        if (mList != null) {
            mList.clear();
            mList.clearListener();
            mList = null;
        }
    }

    @Override
    public void detachView() {
        super.detachView();
        if (needsClearState()) {
            clearState();
        }
    }

}
