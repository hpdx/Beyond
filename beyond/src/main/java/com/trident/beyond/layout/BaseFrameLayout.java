package com.trident.beyond.layout;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.trident.beyond.core.MvvmBaseView;
import com.trident.beyond.core.MvvmBaseViewModel;
import com.trident.beyond.core.MvvmFrameLayout;
import com.trident.beyond.core.RootViewProxy;
import com.trident.beyond.dialog.LoadingDialog;

/**
 * Created by android_ls on 2017/3/20.
 */

public abstract class BaseFrameLayout<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>> extends MvvmFrameLayout<M, V, VM> {

    protected RootViewProxy mRootViewProxy;
    protected LoadingDialog mProgressDialog;

    public BaseFrameLayout(@NonNull Context context) {
        super(context);
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void createView() {
        mRootViewProxy = new RootViewProxy();
        mRootViewProxy.onCreateView(mLayoutInflater, this, getLayoutRes());
        mRootViewProxy.onViewCreated(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorViewClicked();
            }
        });

        addView(mRootViewProxy.getRootView(),
                new LinearLayoutCompat.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    @Override
    protected void onDetachedFromWindow() {
        dismissProgressDialog();
        super.onDetachedFromWindow();
    }

    /**
     * 当加载数据出错时，默认情况下用户单击errorView进行重新加载数据
     */
    protected void onErrorViewClicked() {
        loadData(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        if (!pullToRefresh) {
            animateLoadingViewIn();
        }
    }

    @Override
    public void showContent() {
        animateContentViewIn();
    }

    public void showErrorView() {
        animateErrorViewIn();
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        String errorMsg = getErrorMessage(e, pullToRefresh);
        if(!TextUtils.isEmpty(errorMsg)) {
            // 显示从上往下飘动的提示信息
            showBannerTips(errorMsg);
        }

        if (mRootViewProxy != null) {
            mRootViewProxy.showErrorMessage(errorMsg);
            showErrorView();
        }
    }

    protected String getErrorMessage(Throwable error, boolean pullToRefresh) {
        return error.getMessage();
    }

    protected void animateLoadingViewIn() {
        mRootViewProxy.showLoading();
    }

    protected void animateContentViewIn() {
        mRootViewProxy.showContent();
    }

    protected void animateErrorViewIn() {
        mRootViewProxy.showErrorView();
    }

    protected void showProgressDialog() {
        showProgressDialog(null, false);
    }

    protected void showProgressDialog(boolean cancel) {
        showProgressDialog(null, cancel);
    }

    protected void showProgressDialog(String title) {
        showProgressDialog(title, false);
    }

    protected void showProgressDialog(String title, boolean cancel) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            return;
        }

        LoadingDialog.Builder builder = new LoadingDialog.Builder(mContext);
        if (!TextUtils.isEmpty(title)) {
            builder.setMessage(title);
        }

        mProgressDialog = builder.create();
        mProgressDialog.setCancelable(cancel);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null) {
                    mProgressDialog.show();
                }
            }
        }, 100);
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            mProgressDialog = null;
        }
    }

}
