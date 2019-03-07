package com.anbetter.beyond.fragment;

import android.os.Bundle;

import com.anbetter.beyond.model.BaseModel;
import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.view.BaseView;
import com.anbetter.beyond.viewmodel.BaseViewModel;

/**
 * Created by android_ls on 16/2/17.
 */
public abstract class BasePageFragment extends MvvmPageFragment<BaseModel, BaseView, BaseViewModel>
        implements MvvmBaseView<BaseModel> {

    @Override
    protected BaseViewModel createViewModel() {
        return new BaseViewModel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public void setData(BaseModel data) {

    }

    @Override
    protected void recordState(Bundle outState) {

    }

    @Override
    protected void restoreState(Bundle savedInstanceState) {

    }

}
