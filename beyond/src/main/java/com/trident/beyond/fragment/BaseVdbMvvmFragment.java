package com.trident.beyond.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trident.beyond.core.MvvmBaseView;
import com.trident.beyond.core.MvvmBaseViewModel;
import com.trident.beyond.core.RootViewProxy;

public abstract class BaseVdbMvvmFragment<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends MvvmPageFragment<M, V, VM> {

    protected ViewDataBinding mViewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootViewProxy = new RootViewProxy();
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return mRootViewProxy.onCreateView(inflater, container, mViewDataBinding.getRoot());
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
