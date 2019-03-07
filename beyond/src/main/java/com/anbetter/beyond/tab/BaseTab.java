package com.anbetter.beyond.tab;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.anbetter.beyond.host.PageTabHost;
import com.anbetter.beyond.model.BaseModel;
import com.anbetter.beyond.model.TabData;
import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.view.BaseView;
import com.anbetter.beyond.viewmodel.BaseViewModel;


/**
 * Created by android_ls on 16/7/25.
 */
public abstract class BaseTab extends MvvmTab<BaseModel, BaseView, BaseViewModel> implements MvvmBaseView<BaseModel> {

    protected TabData tabData;
    protected PageTabHost mPageHost;
    protected Handler mHandler;

    public BaseTab(Context context, TabData tabData) {
        super(context);
        this.mPageHost = tabData.mHost;
        this.tabData = tabData;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    protected final Handler getHandler() {
        return mHandler;
    }

    protected String getErrorMessage(Throwable error, boolean pullToRefresh) {
        return error.getMessage();
    }

    @Override
    protected BaseViewModel createViewModel() {
        return new BaseViewModel();
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public void setData(BaseModel data) {

    }

    @Override
    public void showBannerTips(String message) {
        if (mPageHost != null) {
            mPageHost.showBannerTips(message);
        }
    }

}
