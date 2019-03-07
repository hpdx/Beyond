package com.anbetter.beyond.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.anbetter.beyond.model.BaseModel;
import com.anbetter.beyond.view.BaseView;
import com.anbetter.beyond.viewmodel.BaseViewModel;

/**
 * Created by android_ls on 16/6/3.
 */
public abstract class BaseTabFragment extends BaseViewPagerFragment<BaseModel, BaseView, BaseViewModel> implements BaseView {

    @Override
    protected BaseViewModel createViewModel() {
        return new BaseViewModel();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showTabs();

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public void setData(BaseModel data) {

    }

}
