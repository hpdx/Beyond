package com.anbetter.beyond.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.anbetter.beyond.dialog.LoadingDialog;
import com.anbetter.beyond.host.BinderFragment;
import com.anbetter.beyond.host.PageFragmentHost;
import com.anbetter.beyond.mvvm.MvvmBaseFragment;
import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.mvvm.MvvmBaseViewModel;

import java.lang.reflect.Field;

/**
 * Created by android_ls on 16/1/3.
 */
public abstract class MvvmPageFragment<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends MvvmBaseFragment<M, V, VM> implements BinderFragment {

    protected Context mContext;
    protected PageFragmentHost mPageFragmentHost;
    protected LoadingDialog mProgressDialog;
    private Bundle mSavedInstanceState;

    protected MvvmPageFragment() {
        mSavedInstanceState = getArguments();
        if (mSavedInstanceState == null) {
            mSavedInstanceState = new Bundle();
        }
        setArguments(mSavedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        recordState(outState);
        outState.putAll(mSavedInstanceState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        if (mPageFragmentHost == null) {
            if (getActivity() instanceof PageFragmentHost) {
                mPageFragmentHost = (PageFragmentHost) getActivity();
            }
        }

        if (savedInstanceState != null) {
            mSavedInstanceState = savedInstanceState;
        } else {
            mSavedInstanceState = getArguments();
        }
        restoreState(mSavedInstanceState);

        if(mPageFragmentHost != null) {
            rebindActionBar();
        }
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
    public void onDestroyView() {
        recordState(mSavedInstanceState);
        hideSoftInputFromWindow();
        dismissProgressDialog();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public boolean onMenuBackClick(MenuItem item) {
        return onBackPressed();
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputFromWindow() {
        InputMethodManager im = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im.isActive() && mStatusLayout != null
                && mStatusLayout.getRootView() != null) {
            im.hideSoftInputFromWindow(mStatusLayout.getRootView().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 弹出软键盘
     *
     * @param view EditText
     */
    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

    /**
     * 重写onDetach()回调方法，是为了解决在Fragment彼此替换时，系统报的如下Exception
     * java.lang.IllegalStateException: No host
     * at android.support.v4.app.FragmentManagerImpl.moveToState(FragmentManager.java:1235)
     */
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public void showBannerTips(String message) {
        if (mPageFragmentHost != null) {
            mPageFragmentHost.showGlobalBannerTips(message);
        }
    }
}
