package com.trident.beyond.mvvm;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * Created by android_ls on 16/1/2.
 */
public abstract class MvvmBaseFragment<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends Fragment implements MvvmBaseView<M> {

    protected VM viewModel;

    protected RootViewProxy mRootViewProxy;

    protected Handler mHandler;

    /**
     * Fragment 根布局
     *
     * @return
     */
    protected abstract int getLayoutRes();

    protected abstract VM createViewModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        viewModel = createViewModel();
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootViewProxy = new RootViewProxy();
        return mRootViewProxy.onCreateView(inflater, container, getLayoutRes());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        super.onDestroyView();
        if (mRootViewProxy != null) {
            mRootViewProxy = null;
        }

        if (viewModel != null) {
            viewModel.detachView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        removeViewModel();
    }

    public void removeViewModel() {
        if (viewModel != null) {
            viewModel.removeModel();
            viewModel = null;
        }
    }

    /**
     * 当加载数据出错时，默认情况下用户单击errorView进行重新加载数据
     */
    protected void onErrorViewClicked() {
        showLoading(false);
        loadData(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        if (!pullToRefresh) {
            if (mRootViewProxy != null) {
                mRootViewProxy.showLoading();
            }
        }
    }

    @Override
    public void showContent() {
        if (mRootViewProxy != null) {
            mRootViewProxy.showContent();
        }
    }

    public void showErrorView() {
        if (mRootViewProxy != null) {
            mRootViewProxy.showErrorView();
        }
    }

    @Override
    public void showError(Throwable error, boolean pullToRefresh) {
        String errorMsg = getErrorMessage(error, pullToRefresh);
        if (error instanceof UnknownHostException) {
            errorMsg = "网络不给力，请检查网络设置。";
        }

        if (pullToRefresh && getActivity() != null) {
            // 显示从上往下飘动的提示信息
            showBannerTips(errorMsg);
        } else {
            if (mRootViewProxy != null) {
                mRootViewProxy.showErrorMessage(errorMsg);
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

        if (getActivity() != null) {
            // 显示从上往下飘动的提示信息
            showBannerTips(errorMsg);
        }
    }

    protected abstract String getErrorMessage(Throwable e, boolean pullToRefresh);

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int viewId) {
        return (T) mRootViewProxy.findViewById(viewId);
    }

    /**
     * 设置出错或者空白页显示的图标和文字
     *
     * @param resId
     * @param message
     */
    public void showErrorMessage(int resId, String message) {
        if (mRootViewProxy != null) {
            mRootViewProxy.showErrorMessage(resId, message);
        }
    }

    /**
     * 设置Page的背景色
     *
     * @param resId
     */
    protected void setBackground(int resId) {
        if (mRootViewProxy != null) {
            mRootViewProxy.getRootView().setBackgroundColor(ContextCompat.getColor(getActivity(), resId));
        }
    }

    public ViewGroup getContentView() {
        return mRootViewProxy.getContentView();
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

    public void showEmptyMessage(int index, int resId, String message) {
        showEmptyMessage(getContentView(), index, resId, message);
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

}
