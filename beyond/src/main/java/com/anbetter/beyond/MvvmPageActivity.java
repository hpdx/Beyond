package com.anbetter.beyond;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.anbetter.beyond.dialog.LoadingDialog;
import com.anbetter.beyond.mvvm.MvvmBaseActivity;
import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.mvvm.MvvmBaseViewModel;

/**
 * Created by android_ls on 16/1/3.
 */
public abstract class MvvmPageActivity<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends MvvmBaseActivity<M, V, VM> {

    protected Context mContext;
    protected LoadingDialog mProgressDialog;
    private Bundle mSavedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        if (savedInstanceState != null) {
            mSavedInstanceState = savedInstanceState;
        } else {
            mSavedInstanceState = new Bundle();
        }
        restoreState(mSavedInstanceState);

        initLocalData();

        setUpView();

        initLogic();
    }

    /**
     * 初始化业务逻辑具体实现
     */
    protected void initLogic() {

    }

    /**
     * 填充本地数据
     */
    protected void initLocalData() {

    }

    /**
     * 布局结构初始化
     */
    protected void setUpView() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        recordState(outState);
        outState.putAll(mSavedInstanceState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected int getContentLayoutRes() {
        return 0;
    }

    /**
     * 保存当前界面的数据
     *
     * @param outState
     */
    protected abstract void recordState(Bundle outState);

    /**
     * 恢复当前界面的数据
     *
     * @param savedInstanceState Bundle
     */
    protected abstract void restoreState(Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        dismissProgressDialog();
        super.onDestroy();
    }

    protected String getErrorMessage(Throwable error, boolean pullToRefresh) {
        return error.getMessage();
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

        builder.setCancelable(cancel);
        mProgressDialog = builder.create();
        mHandler.postDelayed(new Runnable() {
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
