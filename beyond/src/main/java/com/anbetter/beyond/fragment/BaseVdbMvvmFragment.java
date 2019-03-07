package com.anbetter.beyond.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.mvvm.MvvmBaseViewModel;
import com.anbetter.beyond.mvvm.StatusLayout;

public abstract class BaseVdbMvvmFragment<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends MvvmPageFragment<M, V, VM> {

    protected ViewDataBinding mViewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mStatusLayout = new StatusLayout();
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return mStatusLayout.onCreateView(inflater, container, mViewDataBinding.getRoot());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindViews();
    }

    protected void unbindViews() {
        if (mViewDataBinding != null) {
            mViewDataBinding.unbind();
            mViewDataBinding = null;
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public void setData(M data) {

    }

    @Override
    protected void recordState(Bundle outState) {

    }

    @Override
    protected void restoreState(Bundle savedInstanceState) {

    }

}
