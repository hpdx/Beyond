package com.trident.beyond.fragment;

import android.os.Bundle;

import com.dating.rxbus.RxBus;
import com.trident.beyond.core.MvvmBaseView;
import com.trident.beyond.model.BaseModel;
import com.trident.beyond.view.BaseView;
import com.trident.beyond.viewmodel.BaseViewModel;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
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
