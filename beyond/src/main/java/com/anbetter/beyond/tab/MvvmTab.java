package com.anbetter.beyond.tab;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.anbetter.beyond.mvvm.MvvmBaseTab;
import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.mvvm.MvvmBaseViewModel;
import com.anbetter.beyond.rxbus.RxBus;


/**
 * Created by android_ls on 17/1/3.
 */
public abstract class MvvmTab<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends MvvmBaseTab<M, V, VM> {

    public MvvmTab(Context context) {
        super(context);
        RxBus.get().register(this);
    }

    protected String getErrorMessage(Throwable error, boolean pullToRefresh) {
        return error.getMessage();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputFromWindow() {
        InputMethodManager im = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im.isActive() && mStatusLayout != null
                && mStatusLayout.getRootView() != null) {
            im.hideSoftInputFromWindow(mStatusLayout.getRootView().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
