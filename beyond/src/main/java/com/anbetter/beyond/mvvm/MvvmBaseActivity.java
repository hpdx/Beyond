package com.anbetter.beyond.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.anbetter.beyond.R;
import com.anbetter.beyond.actionbar.NavActionBar;
import com.anbetter.beyond.utils.ToastUtils;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * <p>
 * Created by android_ls on 2018/11/7.
 *
 * @author 李松
 * @version 1.0
 */
public abstract class MvvmBaseActivity<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>> extends MvvmActivity<M, V, VM>
        implements MvvmBaseView<M> {

    protected NavActionBar mNavActionBar;
    protected StatusLayout mStatusLayout;
    protected LinearLayout mRootLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNavActionBar = (NavActionBar) findViewById(com.anbetter.beyond.R.id.action_bar);
        if (mNavActionBar != null) {
            mNavActionBar.displayActionBarBack(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

        mRootLayout = (LinearLayout) findViewById(R.id.ll_content_layout);
        if (mRootLayout != null) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            if (hasStatusLayout()) {
                mStatusLayout = new StatusLayout();
                mStatusLayout.onCreateView(layoutInflater, mRootLayout, getContentLayoutRes());
                mStatusLayout.onViewCreated(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        showLoading(false);
                        loadData(false);
                    }
                });
                mRootLayout.addView(mStatusLayout.getRootView());
            } else {
                if (getContentLayoutRes() != 0) {
                    View view = layoutInflater.inflate(getContentLayoutRes(), mRootLayout, false);
                    mRootLayout.addView(view);
                }
            }
        }
    }

    /**
     * 是否需要多状态切换的Layout
     *
     * @return
     */
    protected boolean hasStatusLayout() {
        return false;
    }

    /**
     * 设置导航栏标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (mNavActionBar != null) {
            mNavActionBar.setActionBarTitle(title);
        }
    }

    /**
     * 是否要显示顶部的导航栏（ActionBar）
     *
     * @param visible
     */
    protected void toggleActionBar(boolean visible) {
        if (mNavActionBar != null) {
            mNavActionBar.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    protected void displayActionBarRightText(CharSequence title, View.OnClickListener listener) {
        if (mNavActionBar != null) {
            mNavActionBar.displayActionBarRightText(title, listener);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_base_layout;
    }

    /**
     * 获取内容布局文件
     *
     * @return
     */
    protected abstract int getContentLayoutRes();

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
    public void showLoading(boolean pullToRefresh) {
        if (!pullToRefresh) {
            if (mStatusLayout != null) {
                mStatusLayout.showLoading();
            }
        }
    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public void setData(M data) {

    }

    @Override
    public void showContent() {
        if (mStatusLayout != null) {
            mStatusLayout.showContent();
        }
    }

    public void showErrorView() {
        if (mStatusLayout != null) {
            mStatusLayout.showError();
        }
    }

    @Override
    public void showError(Throwable error, boolean pullToRefresh) {
        String errorMsg = getErrorMessage(error, pullToRefresh);
        if (error instanceof UnknownHostException
                || error instanceof EOFException
                || error instanceof ConnectException
                || error instanceof SocketException) {
            errorMsg = "网络不给力，请检查网络设置。";
        }

        if (pullToRefresh) {
            // 显示从上往下飘动的提示信息
            showBannerTips(errorMsg);
        } else {
            if (mStatusLayout != null) {
                mStatusLayout.setErrorMessage(errorMsg);
                showErrorView();
            }

            // 显示从上往下飘动的提示信息
            showBannerTips(errorMsg);
        }
    }

    @Override
    public void showError(Throwable e) {
        showError(e, false);
    }

    @Override
    public void showErrorMessage(Throwable error) {
        String errorMsg = error.getMessage();
        if (error instanceof UnknownHostException
                || error instanceof EOFException
                || error instanceof ConnectException
                || error instanceof SocketException) {
            errorMsg = "网络不给力，请检查网络设置。";
        }

        showBannerTips(errorMsg);
    }

    protected String getErrorMessage(Throwable error, boolean pullToRefresh) {
        return error.getMessage();
    }

    @Override
    public void showBannerTips(String message) {
        ToastUtils.show(this, message);
    }

}
