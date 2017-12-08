package com.trident.beyond.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.trident.beyond.dialog.SafeDialogFragment;

/**
 *
 * Created by android_ls on 16/1/2.
 */
public abstract class MvvmBaseDialogFragment<M, V extends MvvmView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends SafeDialogFragment implements MvvmView<M> {

    protected VM viewModel;

    protected abstract VM createViewModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createViewModel();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.attachView((V)this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            viewModel.removeModel();
            viewModel = null;
        }
    }

}
