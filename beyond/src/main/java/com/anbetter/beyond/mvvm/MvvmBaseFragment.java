package com.anbetter.beyond.mvvm;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anbetter.beyond.rxbus.RxBus;

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

    protected StatusLayout mStatusLayout;

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
        RxBus.get().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mStatusLayout = new StatusLayout();
        return mStatusLayout.onCreateView(inflater, container, getLayoutRes());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStatusLayout.onViewCreated(new View.OnClickListener() {
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
        if (mStatusLayout != null) {
            mStatusLayout = null;
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
        RxBus.get().unregister(this);
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
            if (mStatusLayout != null) {
                mStatusLayout.showLoading();
            }
        }
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
        if (error instanceof UnknownHostException) {
            errorMsg = "网络不给力，请检查网络设置。";
        }

        if (pullToRefresh && getActivity() != null) {
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

        if (getActivity() != null) {
            // 显示从上往下飘动的提示信息
            showBannerTips(errorMsg);
        }
    }

    protected abstract String getErrorMessage(Throwable e, boolean pullToRefresh);

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int viewId) {
        return (T) mStatusLayout.findViewById(viewId);
    }

    /**
     * 设置出错或者空白页显示的图标和文字
     *
     * @param resId
     * @param message
     */
    public void showErrorMessage(int resId, String message) {
        if (mStatusLayout != null) {
            mStatusLayout.setErrorMessage(resId, message);
        }
    }

    /**
     * 设置Page的背景色
     *
     * @param resId
     */
    protected void setBackground(int resId) {
        if (mStatusLayout != null) {
            mStatusLayout.getRootView().setBackgroundColor(ContextCompat.getColor(getActivity(), resId));
        }
    }

    public ViewGroup getContentView() {
        return mStatusLayout.getContentView();
    }

    public void showEmptyMessage(int resId, String message) {
        if (mStatusLayout != null) {
            mStatusLayout.addEmptyView(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEmptyViewClicked();
                }
            });
            mStatusLayout.setEmptyMessage(resId, message);
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
        if (mStatusLayout != null) {
            mStatusLayout.addEmptyView(container, index, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEmptyViewClicked();
                }
            });
            mStatusLayout.setEmptyMessage(resId, message);
        }
    }

    public void showEmptyMessage(ViewGroup container, int resId, String message) {
        showEmptyMessage(container, 0, resId, message);
    }

    /**
     * 用户点击空白页事件处理
     */
    protected void onEmptyViewClicked() {

    }

}
