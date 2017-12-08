package com.trident.beyond.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trident.beyond.host.BinderTab;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * Created by android_ls on 16/1/5.
 */
public abstract class MvvmBaseTab<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>>
        implements MvvmBaseView<M>, BinderTab {

    protected VM viewModel;

    protected LayoutInflater mLayoutInflater;

    protected RootViewProxy mRootViewProxy;
    protected Context mContext;

    public MvvmBaseTab(Context context) {
        this.mContext = context;
        this.viewModel = createViewModel();
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    protected abstract int getLayoutRes();

    protected abstract VM createViewModel();

    @Override
    public View getView() {
        if (mRootViewProxy != null) {
            return mRootViewProxy.getRootView();
        }
        return null;
    }

    public void onViewCreated() {
        mRootViewProxy = new RootViewProxy();
        mRootViewProxy.onCreateView(mLayoutInflater, null, getLayoutRes());
        mRootViewProxy.onViewCreated(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorViewClicked();
            }
        });
        viewModel.attachView((V) this);
    }

    @Override
    public void onDestroyView() {
        if (mRootViewProxy != null) {
            mRootViewProxy = null;
        }
        viewModel.detachView();
    }

    @Override
    public void onDestroy() {
        if (viewModel != null) {
            viewModel.removeModel();
            viewModel = null;
        }
    }

    /**
     * 当加载数据出错时，默认情况下用户单击errorView进行重新加载数据
     */
    protected void onErrorViewClicked() {
        loadData(false);
    }

    /**
     * 设置Page的背景色
     *
     * @param resId
     */
    protected void setBackground(int resId) {
        if (mRootViewProxy != null) {
            mRootViewProxy.getRootView().setBackgroundColor(ContextCompat.getColor(mContext, resId));
        }
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

    @Override
    public void showError(Throwable error, boolean pullToRefresh) {
        String errorMsg = getErrorMessage(error, pullToRefresh);
        if(error instanceof UnknownHostException) {
            errorMsg = "网络不给力，请检查网络设置。";
        }

        if (pullToRefresh) {
            showBannerTips(errorMsg);
        } else {
            if (mRootViewProxy != null) {
                mRootViewProxy.showErrorMessage(errorMsg);
            }
            animateErrorViewIn();
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

        // 显示从上往下飘动的提示信息
        showBannerTips(errorMsg);
    }

    protected abstract String getErrorMessage(Throwable e, boolean pullToRefresh);

    protected void animateLoadingViewIn() {
        if (mRootViewProxy != null) {
            mRootViewProxy.showLoading();
        }
    }

    protected void animateContentViewIn() {
        if (mRootViewProxy != null) {
            mRootViewProxy.showContent();
        }
    }

    protected void animateErrorViewIn() {
        if (mRootViewProxy != null) {
            mRootViewProxy.showErrorView();
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int viewId) {
        return (T) mRootViewProxy.findViewById(viewId);
    }

    public ViewGroup getContentView() {
        if (mRootViewProxy != null) {
            return mRootViewProxy.getContentView();
        }
        return null;
    }

    public void showEmptyMessage(int resId, String message) {
        if (mRootViewProxy != null) {
            mRootViewProxy.addEmptyView(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEmptyViewClicked();
                }
            });
            mRootViewProxy.showEmptyMessage(resId, message);
        }
    }

    public void showEmptyMessage(Bitmap bitmap, String message) {
        if (mRootViewProxy != null) {
            mRootViewProxy.addEmptyView(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEmptyViewClicked();
                }
            });
            mRootViewProxy.showEmptyMessage(bitmap, message);
        }
    }

    public void showEmptyMessage(int index, int resId, String message) {
        if (getContentView() != null) {
            showEmptyMessage(getContentView(), index, resId, message);
        }
    }

    /**
     * 添加空白页面，将空白页添加到子ViewGroup中，使用该方法
     *
     * @param container
     * @param index
     * @param resId
     * @param message
     */
    public void showEmptyMessage(ViewGroup container, int index, int resId, String message) {
        if (mRootViewProxy != null) {
            mRootViewProxy.addEmptyView(container, index, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEmptyViewClicked();
                }
            });
            mRootViewProxy.showEmptyMessage(resId, message);
        }
    }

    public void showEmptyMessage(ViewGroup container, int index, Bitmap bitmap, String message) {
        if (mRootViewProxy != null) {
            mRootViewProxy.addEmptyView(container, index, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEmptyViewClicked();
                }
            });
            mRootViewProxy.showEmptyMessage(bitmap, message);
        }
    }

    public void showEmptyMessage(ViewGroup container, int resId, String message) {
        showEmptyMessage(container, 0, resId, message);
    }

    /**
     * 隐藏空数据提示view
     */
    public void hideEmptyView() {
        if (mRootViewProxy != null) {
            mRootViewProxy.hideEmptyView();
        }
    }

    /**
     * 用户点击空白页事件处理
     */
    protected void onEmptyViewClicked() {

    }

    @Override
    public void dismissProgressDialog() {

    }

}
