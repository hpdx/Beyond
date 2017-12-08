package com.trident.better.router.interfaces;

/**
 * Created by android_ls on 2016/12/27.
 */

public interface INavigation<T> {

    boolean canNavigate();

    void showPage(String pageName, T pageObj);

    boolean goBack();

    void goHome();

    String getCurrentPage();
}
