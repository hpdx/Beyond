package com.gank.web;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.anbetter.beyond.fragment.BasePageFragment;
import com.anbetter.log.MLog;
import com.gank.App;
import com.gank.R;

/**
 *
 * Created by android_ls on 2016/12/26.
 */

public class WebFragment extends BasePageFragment {

    private Uri mUri;
    public static WebFragment newInstance(Uri uri) {
        WebFragment fragment = new WebFragment();
        fragment.mUri = uri;
        return fragment;
    }

    @Override
    public void rebindActionBar() {
        mPageFragmentHost.toggleActionBar(true);
        mPageFragmentHost.setActionBarTitle("我是web浏览器");
        mPageFragmentHost.displayActionBarBack(true);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_web;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String url = mUri.toString();
        MLog.i("url = " + url);

        WebView webView = findViewById(R.id.wb_browse);
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLoadsImagesAutomatically(true);

        // 调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
        webSettings.setJavaScriptEnabled(true);

        // HTML5数据存储
        saveData(webSettings);

        // 多窗口的问题
        newWin(webSettings);

        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl(url);
    }

    /**
     * HTML5数据存储
     */
    private void saveData(WebSettings mWebSettings) {
        // 有时候网页需要自己保存一些关键数据,Android WebView 需要自己设置
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        String appCachePath = App.get().getApplicationContext().getCacheDir().getAbsolutePath();
        mWebSettings.setAppCachePath(appCachePath);
    }

    /**
     * 多窗口的问题
     */
    private void newWin(WebSettings mWebSettings) {
        // html中的_bank标签就是新建窗口打开，有时会打不开，需要加以下
        // 然后 复写 WebChromeClient的onCreateWindow方法
        mWebSettings.setSupportMultipleWindows(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    WebViewClient webViewClient = new WebViewClient(){

        /**
         * 多页面在同一个WebView中打开，就是不新建activity或者调用系统浏览器打开
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    };

    WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(view);
            resultMsg.sendToTarget();
            return true;
        }
    };

}
