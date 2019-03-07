package com.anbetter.beyond.mvvm;

/**
 *
 * Created by android_ls on 16/1/2.
 */
public interface MvvmBaseView<M> extends MvvmView<M> {

    void showLoading(boolean pullToRefresh);

    void loadData(boolean pullToRefresh);

    void setData(M data);

    void showContent();

    void showError(Throwable e, boolean pullToRefresh);

    void showError(Throwable e);

    void showErrorMessage(Throwable e);

    void showBannerTips(String message);

}
