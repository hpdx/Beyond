package com.anbetter.beyond.viewmodel;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.anbetter.beyond.mvvm.MvvmBaseViewModel;
import com.anbetter.beyond.view.IWebView;

/**
 * Created by android_ls on 2017/3/21.
 */

public class BaseWebViewViewModel<V extends IWebView> extends MvvmBaseViewModel<String, V> {

    /**
     * WebView相关缓存等设置.
     *
     * @param webSetting
     */
    public void setWebSetting(Context context, WebSettings webSetting) {
        if (webSetting == null) {
            return;
        }

        // 开启DOM storage API 功能
        // 有时候网页需要自己保存一些关键数据,Android WebView 需要自己设置
        webSetting.setDomStorageEnabled(true);
        // 开启database storage API功能
        webSetting.setDatabaseEnabled(true);
        // 开启Application Cache功能
        webSetting.setAppCacheEnabled(true);
        String appCachePath = context.getApplicationContext().getCacheDir().getAbsolutePath();

        // 设置数据库缓存路径
        webSetting.setDatabasePath(appCachePath); // API 19 deprecated
        // 设置Application caches缓存目录 HTML5数据存储
        webSetting.setAppCachePath(appCachePath);

        webSetting.setLoadWithOverviewMode(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webSetting.setDefaultTextEncodingName("utf-8");
        webSetting.setLoadsImagesAutomatically(true);

        webSetting.setAppCacheMaxSize(20 * 1024 * 1024);
        webSetting.setSavePassword(false);
        webSetting.setSaveFormData(false);
        webSetting.setSupportZoom(true);

        // 设置4.2以后版本支持autoPlay，非用户手势促发
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSetting.setMediaPlaybackRequiresUserGesture(false);
        }

        // 调用JS方法.安卓版本大于17,加上注解 @JavascriptInterface
        webSetting.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webSetting.setPluginState(WebSettings.PluginState.ON);
        webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置此属性，可任意比例缩放
        webSetting.setUseWideViewPort(true);
        webSetting.setAllowFileAccess(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        newWin(webSetting);
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

    public void destroyWebView(WebView webView) {
        if (webView == null) {
            return;
        }

        webView.clearHistory();
        webView.clearCache(true);
        webView.removeAllViews();
    }

}
