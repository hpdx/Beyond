package com.anbetter.beyond.layout;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.anbetter.beyond.dialog.LoadingDialog;
import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.anbetter.beyond.mvvm.MvvmBaseViewModel;
import com.anbetter.beyond.mvvm.MvvmLinearLayout;
import com.anbetter.beyond.mvvm.StatusLayout;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;

/**
 * Created by android_ls on 2017/3/20.
 */

public abstract class BaseLinearLayout<M, V extends MvvmBaseView<M>, VM extends MvvmBaseViewModel<M, V>>
        extends MvvmLinearLayout<M, V, VM> {

    protected StatusLayout mStatusLayout;
    protected LoadingDialog mProgressDialog;

    public BaseLinearLayout(@NonNull Context context) {
        super(context);
    }

    public BaseLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected abstract View createActionbar();

    @Override
    protected void createView() {
        addView(createActionbar());

        mStatusLayout = new StatusLayout();
        mStatusLayout.onCreateView(mLayoutInflater, this, getLayoutRes());
        mStatusLayout.onViewCreated(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorViewClicked();
            }
        });

        addView(mStatusLayout.getRootView(),
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

        if (mStatusLayout != null) {
            mStatusLayout.setErrorMessage(errorMsg);
            showErrorView();
        }
    }

    @Override
    public void showErrorMessage(Throwable error) {
        String errorMsg = error.getMessage();
        if(error instanceof UnknownHostException
                || error instanceof EOFException
                || error instanceof ConnectException
                || error instanceof SocketException) {
            errorMsg = "网络不给力，请检查网络设置。";
        }

        // 显示从上往下飘动的提示信息
        showBannerTips(errorMsg);
    }

    protected String getErrorMessage(Throwable error, boolean pullToRefresh) {
        return error.getMessage();
    }

    protected void animateLoadingViewIn() {
        mStatusLayout.showLoading();
    }

    protected void animateContentViewIn() {
        mStatusLayout.showContent();
    }

    protected void animateErrorViewIn() {
        mStatusLayout.showError();
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
        // 设置是否返回键，可以隐藏Dialog
        mProgressDialog.setCancelable(cancel);
        // 屏幕之外区域点击，不隐藏
        mProgressDialog.setCanceledOnTouchOutside(false);
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

    public int dipToPixels(Context context, float dip) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(COMPLEX_UNIT_DIP, dip,
                r.getDisplayMetrics());
        return (int) px;
    }

}
