package com.anbetter.beyond.mvvm;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.anbetter.beyond.BaseActivity;

/**
 * Created by android_ls on 2017/7/31.
 */

public abstract class MvvmActivity<M, V extends MvvmView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends BaseActivity implements MvvmView<M> {

    protected VM viewModel;

    protected Handler mHandler;

    protected abstract int getLayoutRes();

    protected abstract VM createViewModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        mHandler = new Handler(Looper.getMainLooper());
        viewModel = createViewModel();
        viewModel.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }

        if(viewModel != null) {
            viewModel.detachView();
            viewModel.removeModel();
            viewModel = null;
        }
        super.onDestroy();
    }

}
