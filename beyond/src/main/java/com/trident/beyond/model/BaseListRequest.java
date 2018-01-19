package com.trident.beyond.model;

import com.trident.beyond.core.IModel;
import com.trident.beyond.listener.OnDataChangedListener;
import com.trident.beyond.listener.OnRefreshCompleteListener;
import com.trident.dating.libcommon.IRequest;
import com.trident.dating.libcommon.listener.ResponseErrorListener;
import com.trident.dating.libcommon.listener.ResponseListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by android_ls on 16/7/22.
 */
public abstract class BaseListRequest<T, D extends IModel> implements ResponseListener<T> {

    protected OnDataChangedListener mOnDataChangedListener;
    protected ResponseErrorListener mResponseErrorListener;
    protected OnRefreshCompleteListener mOnRefreshCompleteListener;

    /**
     * 实体封装对象，每一个Item对应一个布局.
     */
    protected List<D> mItems;

    /**
     * 当前的请求操作是否是下拉刷新数据
     */
    protected boolean mPullToRefresh;

    protected String mUrl;

    public BaseListRequest() {
        mItems = createList();
        mUrl = getUrl();
    }

    protected List<D> createList() {
        return new ArrayList<>();
    }

    public abstract String getUrl();

    public D getItem(int position) {
        return mItems.get(position);
    }

    public void loadItems() {
        mPullToRefresh = false;
        makeRequest(mUrl);
    }

    public void pullToRefreshItems() {
        mPullToRefresh = true;
        makeRequest(mUrl);
    }

    public List<D> getItems() {
        return mItems;
    }

    public void add(D obj) {
        if(!mItems.contains(obj)) {
            mItems.add(obj);
        }
    }

    public int getCount() {
        return mItems.size();
    }

    public boolean isReady() {
        return mItems.size() > 0;
    }

    protected abstract IRequest makeRequest(String url);

    protected abstract List<D> getItemsFromResponse(T response);

    /**
     * 是否需要显示列表底部的，已加载完成所有数据的提示View，默认不显示
     * @return
     */
    public boolean showFooterNoneView() {
        return false;
    }

    /**
     * 网络请求回调.
     *
     * @param response
     */
    @Override
    public void onResponse(T response) {
        if (mPullToRefresh) {
            if (mItems.size() > 0) {
                mItems.clear();
            }
        }

        List<D> items = getItemsFromResponse(response);
        if (items != null && items.size() > 0) {
            mItems.addAll(items);
        }

        notifyDataChanged();
    }

    @Override
    public void onErrorResponse(Exception error) {
        if (mResponseErrorListener != null) {
            mResponseErrorListener.onErrorResponse(error);
        }
    }

    public final void addRefreshCompleteListener(OnRefreshCompleteListener listener) {
        this.mOnRefreshCompleteListener = listener;
    }

    /**
     * 网络请求数据解析完毕后，通知ViewModel刷新页面展示数据.
     *
     * @param listener
     */
    public final void addDataChangedListener(OnDataChangedListener listener) {
        this.mOnDataChangedListener = listener;
    }

    public final void addErrorListener(ResponseErrorListener errorListener) {
        this.mResponseErrorListener = errorListener;
    }

    public final void clearListener() {
        mResponseErrorListener = null;
        mOnDataChangedListener = null;
        mOnRefreshCompleteListener = null;
    }

    /**
     * 清空数据源中的所有数据
     */
    public final void clear() {
        if(mItems != null && mItems.size() > 0) {
            mItems.clear();
        }
    }

    /**
     * 当前执行的是否是下拉刷新操作
     *
     * @return true 表示是下拉刷新
     */
    public boolean isPullToRefresh() {
        return mPullToRefresh;
    }

    /**
     * 数据源发生了变化，通知UI刷新
     */
    public void notifyDataChanged() {
        if (mOnDataChangedListener != null) {
            mOnDataChangedListener.onDataChanged();
        }
    }

    /**
     * 更新下拉刷新的状态到初始
     */
    public void refreshComplete() {
        if (mOnRefreshCompleteListener != null) {
            mOnRefreshCompleteListener.onRefreshComplete();
        }
    }

}
