package com.trident.beyond.tab;

import android.content.Context;

import com.trident.beyond.mvvm.MvvmBaseView;
import com.trident.beyond.model.BaseModel;
import com.trident.beyond.view.BaseView;
import com.trident.beyond.viewmodel.BaseViewModel;


/**
 * Created by android_ls on 16/7/25.
 */
public abstract class BaseTab extends MvvmTab<BaseModel, BaseView, BaseViewModel> implements MvvmBaseView<BaseModel> {

    public BaseTab(Context context) {
        super(context);
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

}
