package com.trident.beyond.mvvm;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Created by android_ls on 16/1/2.
 */
public class MvvmBaseViewModel<M, V extends MvvmView<M>> implements MvvmViewModel<M, V> {

    private WeakReference<V> viewRef;

    public MvvmBaseViewModel() {

    }

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
    }

    public final boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    protected boolean isDataReady() {
        return false;
    }

    @Nullable
    public final V getView() {
        return viewRef == null ? null : viewRef.get();
    }

    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    /**
     * 移除viewModel持有的所有models
     */
    public void removeModel() {

    }

    public boolean isEmpty() {
        return false;
    }

}
