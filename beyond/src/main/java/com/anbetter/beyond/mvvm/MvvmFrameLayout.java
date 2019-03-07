package com.anbetter.beyond.mvvm;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

/**
 * Created by android_ls on 2017/3/20.
 */

public abstract class MvvmFrameLayout<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends FrameLayout implements MvvmBaseView<M> {

    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    protected VM viewModel;
    protected Handler mHandler;

    public MvvmFrameLayout(@NonNull Context context) {
        super(context);
        setupViews(context);
    }

    public MvvmFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupViews(context);
    }

    public MvvmFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupViews(context);
    }

    protected abstract int getLayoutRes();

    protected abstract VM createViewModel();

    protected void setupViews(@NonNull Context context) {
        this.mContext = context;
        this.viewModel = createViewModel();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mLayoutInflater = LayoutInflater.from(mContext);
        createView();
    }

    protected void createView() {
        this.mLayoutInflater.inflate(getLayoutRes(), this, true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(viewModel != null) {
            viewModel.attachView((V) this);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(viewModel != null) {
            viewModel.detachView();
        }
    }

}
