package com.anbetter.beyond.view;

import com.anbetter.beyond.mvvm.MvvmBaseView;

/**
 * Created by android_ls on 2017/3/21.
 */

public interface IWebView extends MvvmBaseView<String> {

    boolean isPullToRefresh();

    void loadHtml(String html);

    void errorLoad();
}
