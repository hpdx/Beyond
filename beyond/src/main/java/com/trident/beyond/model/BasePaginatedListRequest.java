package com.trident.beyond.model;

import android.text.TextUtils;

import com.trident.dating.libcommon.IRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android_ls on 16/8/1.
 */
public abstract class BasePaginatedListRequest<T, D extends IModel> extends BaseListRequest<T, D> {

    /**
     * 每页取20条数据
     */
    protected static final int PAGINATED_COUNT = 20;

    /**
     * 离已请求到的数据，还有多少条未显示时，去发出下一页的数据请求
     */
    protected static final int LOAD_ITEM_OFFSET_TOTAL = 5;

    /**
     * 滚动列表，还剩多少条未显示时，请求下一页的数据
     */
    protected int mLoadOffset;

    /**
     * 每页取多少条数据，默认为20条
     */
    protected int mPaginatedCount;

    /**
     * 是否还有下一页
     */
    protected boolean mMoreAvailable;

    protected ArrayList<UrlOffsetPair> mUrlOffsetList;
    protected int mCurrentOffset;

    /**
     * 当前请求Request对象.
     */
    protected IRequest mCurrentRequest;

    protected T mLastResponse;

    protected Exception mException;

    public BasePaginatedListRequest() {
        super();
        mUrlOffsetList = new ArrayList<>();
        mMoreAvailable = true;
        mPaginatedCount = PAGINATED_COUNT; // 初始赋值，也是默认值
        mLoadOffset = LOAD_ITEM_OFFSET_TOTAL;
        mUrlOffsetList.add(new UrlOffsetPair(0, getUrl()));
    }

    /**
     * 设置每页加载多少条数据
     * @param paginatedCount
     */
    public void setPaginatedCount(int paginatedCount) {
        mPaginatedCount = paginatedCount;
    }

    /**
     * 设置还剩多少条，开始加载下一页数据，默认是5条
     * @param loadOffset
     */
    public void setLoadOffset(int loadOffset) {
        mLoadOffset = loadOffset;
    }

    @Override
    public void loadItems() {
        reset();
        mPullToRefresh = false;
        request(mUrlOffsetList.get(0));
    }

    public void pullToRefreshItems() {
        reset();
        mPullToRefresh = true;
        request(mUrlOffsetList.get(0));
    }

    public void retryLoadItems() {
        if (inErrorState()) {
            mCurrentRequest = null;
            mException = null;
            mPullToRefresh = false;

            UrlOffsetPair wrapper = null;
            for (UrlOffsetPair currentWrapper : mUrlOffsetList) {
                if (mCurrentOffset == currentWrapper.offset) {
                    wrapper = currentWrapper;
                    break;
                }
            }

            if (wrapper == null) {
                wrapper = mUrlOffsetList.get(mUrlOffsetList.size() - 1);
            }

            request(wrapper);
        }
    }

    @Override
    public D getItem(int position) {
        if (position < getCount()) {
            if (mMoreAvailable && (getCount() - position) <= mLoadOffset) {
                mPullToRefresh = false;
                UrlOffsetPair urlOffsetPair = mUrlOffsetList.get(mUrlOffsetList.size() - 1);
                request(urlOffsetPair);
            }
            return mItems.get(position);
        }
        return null;
    }

    protected void request(UrlOffsetPair urlOffsetPair) {
        if (inErrorState()) {
            // 上一次的网络请求发生了异常，后面的请求也不要发送了（服务器可能挂了）
            return;
        }

        if (mCurrentRequest != null) {
            cancelNextRequest(urlOffsetPair.url);
        } else {
            mCurrentOffset = urlOffsetPair.offset;
            mCurrentRequest = makeRequest(urlOffsetPair.url);
        }
    }

    /**
     * 上一次发起的请求服务端还未响应（或者服务端已响应，但是还未处理完），后面的请求先都取消掉
     */
    protected void cancelNextRequest(String url) {
        if (!mCurrentRequest.getUrl().equals(url)) {
            mCurrentRequest.cancel();
        }
    }

    /**
     * 网络请求数据解析.
     *
     * @param response
     */
    @Override
    public void onResponse(T response) {
        mLastResponse = response;
        if (mCurrentOffset == 0) {
            if (mItems.size() > 0) {
                mItems.clear();
                for (int i = mUrlOffsetList.size() - 1; i >= 0; i--) {
                    UrlOffsetPair currentWrapper = mUrlOffsetList.get(i);
                    if (currentWrapper.offset != 0) {
                        mUrlOffsetList.remove(i);
                    }
                }
            }
        } else {
            mPullToRefresh = false;
        }

        List<D> items = getItemsFromResponse(response);
        if (items != null && items.size() > 0) {
            mItems.addAll(items);

            String nextPageUrl = getNextPageUrl();
            if (!TextUtils.isEmpty(nextPageUrl)) {
                mUrlOffsetList.add(new UrlOffsetPair(mItems.size(), nextPageUrl));
            }

            mMoreAvailable = isNextRequestEnabled(items);
        } else {
            mMoreAvailable = false;
        }

        notifyDataChanged();
        mCurrentRequest = null;
    }

    @Override
    public void onErrorResponse(Exception error) {
        mException = error;
        mCurrentRequest = null;
        if (mResponseErrorListener != null) {
            mResponseErrorListener.onErrorResponse(error);
        }
    }

    /**
     * 判断加载下一页数据的请求是否存在，即：是否还有下一页数据.
     *
     * @param items
     * @return
     */
    protected boolean isNextRequestEnabled(List<D> items) {
        return items.size() >= mPaginatedCount;
    }

    /**
     * 请求下一页数据的URL
     * @return
     */
    protected abstract String getNextPageUrl();

    /**
     * 服务器请求是否出错了
     *
     * @return true 表示出错了
     */
    public boolean inErrorState() {
        return mException != null;
    }

    /**
     * 是否需要请求下一页数据
     *
     * @return true 表示需要，用户看到的是正在加载数据的状态
     */
    public boolean isMoreAvailable() {
        return mMoreAvailable;
    }

    protected void reset() {
        mLastResponse = null;
        mCurrentRequest = null;
        mException = null;
    }

}
