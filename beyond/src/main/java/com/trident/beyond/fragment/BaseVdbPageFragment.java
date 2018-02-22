package com.trident.beyond.fragment;

import android.os.Bundle;

import com.trident.beyond.mvvm.MvvmBaseView;
import com.trident.beyond.model.BaseModel;
import com.trident.beyond.view.BaseView;
import com.trident.beyond.viewmodel.BaseViewModel;

/**
 * Created by android_ls on 2017/1/10.
 */
public abstract class BaseVdbPageFragment
        extends BaseVdbMvvmFragment<BaseModel, BaseView, BaseViewModel>
        implements MvvmBaseView<BaseModel> {

    @Override
    protected BaseViewModel createViewModel() {
        return new BaseViewModel();
    }

    @Override
    protected void recordState(Bundle outState) {

    }

    @Override
    protected void restoreState(Bundle savedInstanceState) {

    }

}
