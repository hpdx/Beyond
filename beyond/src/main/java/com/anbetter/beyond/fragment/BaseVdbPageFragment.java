package com.anbetter.beyond.fragment;

import android.os.Bundle;

import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.model.BaseModel;
import com.anbetter.beyond.view.BaseView;
import com.anbetter.beyond.viewmodel.BaseViewModel;

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
