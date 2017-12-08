package com.trident.beyond.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.MenuItem;

import com.dating.rxbus.RxBus;
import com.trident.beyond.dialog.LoadingDialog;
import com.trident.beyond.host.BinderFragment;
import com.trident.beyond.host.PageFragmentHost;

import java.io.EOFException;
import java.lang.reflect.Field;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * Created by android_ls on 2017/3/21.
 */

public abstract class BaseFragment extends Fragment implements BinderFragment {

    protected Context mContext;
    protected PageFragmentHost mPageFragmentHost;
    protected LoadingDialog mProgressDialog;
    private Bundle mSavedInstanceState;

    // UI线程Handler
    protected Handler mHandler;

    protected BaseFragment() {
        mSavedInstanceState = getArguments();
        if (mSavedInstanceState == null) {
            mSavedInstanceState = new Bundle();
        }
        setArguments(mSavedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mHandler = new Handler(Looper.getMainLooper());
        RxBus.get().register(this);
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
        if (mPageFragmentHost == null) {
            mContext = getActivity();
            mPageFragmentHost = (PageFragmentHost) getActivity();
        }

        if (savedInstanceState != null) {
            mSavedInstanceState = savedInstanceState;
        } else {
            mSavedInstanceState = getArguments();
        }
        restoreState(mSavedInstanceState);

        rebindActionBar();
    }


    public boolean onBackPressed() {
        return false;
    }

    @Override
    public boolean onKeyDownEvent(int code) {
        return false;
    }

    @Override
    public boolean onMenuBackClick(MenuItem item) {
        return onBackPressed();
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
        dismissLoadingDialog();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
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
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mProgressDialog != null) {
                    mProgressDialog.show();
                }
            }
        }, 100);
    }

    protected void dismissLoadingDialog() {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            mProgressDialog = null;
        }
    }

    public void showBannerTips(String message) {
        if (mPageFragmentHost != null) {
            mPageFragmentHost.showGlobalBannerTips(message);
        }
    }

    public void showErrorMessage(Throwable error) {
        String errorMsg = error.getMessage();
        if (error instanceof UnknownHostException
                || error instanceof EOFException
                || error instanceof ConnectException
                || error instanceof SocketException) {
            errorMsg = "网络不给力，请检查网络设置。";
        }

        if (getActivity() != null) {
            // 显示从上往下飘动的提示信息
            showBannerTips(errorMsg);
        }
    }

}
