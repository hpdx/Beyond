package com.trident.beyond.tab;

import android.content.Context;

import com.dating.rxbus.RxBus;
import com.trident.beyond.core.MvvmBaseTab;
import com.trident.beyond.core.MvvmBaseView;
import com.trident.beyond.core.MvvmBaseViewModel;


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

}
