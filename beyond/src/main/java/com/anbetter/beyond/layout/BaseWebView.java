package com.anbetter.beyond.layout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.webkit.WebSettings;

import com.anbetter.beyond.R;
import com.anbetter.beyond.view.IWebView;
import com.anbetter.beyond.viewmodel.BaseWebViewViewModel;
import com.anbetter.beyond.widgets.BWebView;
import com.anbetter.beyond.widgets.PtrHeaderView;
import com.anbetter.log.MLog;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by android_ls on 2017/3/21.
 */
public abstract class BaseWebView<V extends IWebView, VM extends BaseWebViewViewModel<V>>
        extends BaseLinearLayout<String, V, VM> implements IWebView {

    private static final String TAG = "BaseWebView";

    protected BWebView mWebView;
    protected PtrFrameLayout mPtrFrameLayout;
    protected PtrHeaderView mPtrHeaderView;
    public String mUrl;

    // 是否是下拉刷新
    protected boolean mPullToRefresh;

    public BaseWebView(@NonNull Context context) {
        super(context);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * WebView所在的基础布局.
     *
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.page_web_fragment;
    }

    @Override
    protected abstract VM createViewModel();

    @Override
    protected void setupViews(@NonNull Context context) {
        super.setupViews(context);
        mWebView = (BWebView) mStatusLayout.findViewById(R.id.wb_browse);
        setWebSetting();

        mPtrFrameLayout = (PtrFrameLayout) mStatusLayout.findViewById(R.id.ptr_refresh_layout);
        mPtrHeaderView = new PtrHeaderView(mContext);
        mPtrFrameLayout.setHeaderView(mPtrHeaderView);
        mPtrFrameLayout.addPtrUIHandler(mPtrHeaderView);
        setRefreshEnabled();

        // 设置下拉刷新监听
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mWebView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                }, 200);
            }
        });

    }

    /**
     * 下拉刷新，会显示下拉的状态
     */
    public void autoRefresh() {
        if (mPtrFrameLayout != null) {
            mPtrFrameLayout.autoRefresh();
        }
    }

    public void onRefreshComplete() {
        mPtrFrameLayout.refreshComplete();
    }

    protected void setWebSetting() {
        WebSettings webSettings = mWebView.getSettings();
        viewModel.setWebSetting(mContext, webSettings);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        MLog.i(TAG, "loadData mUrl = " + mUrl);
        mWebView.loadUrl(mUrl);
    }

    public void loadUrl(String url) {
        mPullToRefresh = false;
        this.mUrl = url;
        loadData(false);
    }

    public void refresh() {
        mPullToRefresh = true;
        loadData(true);
    }

    public void destroy() {
        if (viewModel != null) {
            viewModel.destroyWebView(mWebView);
        }
    }

    /**
     * 若想禁用下拉刷新功能，请重写此方法
     * 示例：
     * mPtrFrameLayout.setEnabled(false);
     */
    protected void setRefreshEnabled() {
        mPtrFrameLayout.setEnabled(true);
    }

    @Override
    public boolean isPullToRefresh() {
        return mPullToRefresh;
    }

    @Override
    public void setData(String data) {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showBannerTips(String message) {

    }

    public abstract void permissionAgree(int type);

}
