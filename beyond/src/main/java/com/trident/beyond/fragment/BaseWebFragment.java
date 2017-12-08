package com.trident.beyond.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trident.beyond.layout.BaseWebView;

/**
 * 所有内嵌有WebView组件的Fragment的基类
 * <p>
 * Created by android_ls on 2017/3/20.
 */
public abstract class BaseWebFragment extends BaseFragment {

    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";

    protected String mTitle;
    protected String mUrl;
    protected BaseWebView mWebView;

    @Override
    public void rebindActionBar() {
        mPageFragmentHost.toggleActionBar(true);
        if (!TextUtils.isEmpty(mTitle)) {
            mPageFragmentHost.setActionBarTitle(mTitle);
        }
        mPageFragmentHost.displayActionBarBack(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mWebView == null) {
            mWebView = createWebView(container.getContext());
            mWebView.loadUrl(mUrl);
        }
        return mWebView;
    }

    public abstract BaseWebView createWebView(Context context);

    @Override
    protected void recordState(Bundle outState) {
        if (!TextUtils.isEmpty(mUrl)) {
            outState.putString(KEY_URL, mUrl);
        }

        if (!TextUtils.isEmpty(mTitle)) {
            outState.putString(KEY_TITLE, mTitle);
        }
    }

    @Override
    protected void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(KEY_URL)) {
            mUrl = savedInstanceState.getString(KEY_URL);
        }

        if (savedInstanceState.containsKey(KEY_TITLE)) {
            mTitle = savedInstanceState.getString(KEY_TITLE);
        }
    }

    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.destroy();
        }
        super.onDestroy();
    }

}
