package com.trident.beyond.mvvm;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trident.beyond.R;


/**
 *
 * Created by android_ls on 16/8/26.
 */
public class RootViewProxy {

    // 页面根布局View
    protected ViewGroup mRootView;

    // 页面内容加载出来之前展示的Loading控件
    private View mLoadingView;

    private View mContentView;

    private View mErrorView;
    private TextView tvErrorMessage;
    private ImageView ivErrorIcon;

    private View mEmptyView;
    private ImageView ivEmptyIcon;
    private TextView tvEmptyMessage;

    private LayoutInflater mLayoutInflater;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, View contentView) {
        mLayoutInflater = inflater;
        mRootView = (ViewGroup) inflater.inflate(R.layout.page_content_view, container, false);
        mContentView = contentView;
        mContentView.setId(R.id.page_content);
        mRootView.addView(contentView);
        return mRootView;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, int layoutResId) {
        mLayoutInflater = inflater;
        mRootView = (ViewGroup) inflater.inflate(R.layout.page_content_view, container, false);
        mContentView = inflater.inflate(layoutResId, container, false);
        mContentView.setId(R.id.page_content);
        mRootView.addView(mContentView);
        return mRootView;
    }

    public void onViewCreated(View.OnClickListener onErrorViewClickListener) {
        mLoadingView = mRootView.findViewById(R.id.loading_indicator);
        mErrorView = mRootView.findViewById(R.id.page_error_indicator);
        ivErrorIcon = (ImageView) mRootView.findViewById(R.id.page_error_image);
        tvErrorMessage = (TextView) mRootView.findViewById(R.id.page_error_msg);
        mErrorView.setOnClickListener(onErrorViewClickListener);
    }

    public void addView(View child) {
        mRootView.addView(child);
    }

    public void addView(View child, ViewGroup.LayoutParams layoutParams) {
        mRootView.addView(child, layoutParams);
    }

    public ViewGroup getRootView() {
        return mRootView;
    }

    public View findViewById(int viewId) {
        return mRootView.findViewById(viewId);
    }

    /**
     * 设置出错的图标和文字
     *
     * @param resId
     * @param message
     */
    public void showErrorMessage(int resId, String message) {
        ivErrorIcon.setImageResource(resId);
        tvErrorMessage.setText(message);
    }

    public void showErrorMessage(String errorMessage) {
        tvErrorMessage.setText(errorMessage);
    }

    /**
     * 设置空白页显示的图标和文字
     *
     * @param resId   图片资源Id
     * @param message 没有数据时的提示信息
     */
    public void showEmptyMessage(int resId, String message) {
        if(mEmptyView != null) {
            ivEmptyIcon.setImageResource(resId);
            tvEmptyMessage.setText(message);
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    public void showEmptyMessage(Bitmap bitmap, String message) {
        if(mEmptyView != null) {
            ivEmptyIcon.setImageBitmap(bitmap);
            tvEmptyMessage.setText(message);
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    public ViewGroup getContentView() {
        return (ViewGroup) mRootView.findViewById(R.id.page_content);
    }

    /**
     * 添加空白页面，将空白页添加到子ViewGroup中，使用该方法
     */
    public void addEmptyView(ViewGroup container, int index, View.OnClickListener onEmptyViewClickListener) {
        if(mEmptyView == null) {
            mEmptyView = mLayoutInflater.inflate(R.layout.page_empty_indicator, container, false);
            container.addView(mEmptyView, index, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            ivEmptyIcon = (ImageView) mEmptyView.findViewById(R.id.page_empty_image);
            tvEmptyMessage = (TextView) mEmptyView.findViewById(R.id.page_empty_msg);
            mEmptyView.setOnClickListener(onEmptyViewClickListener);
        }
    }

    public void addEmptyView(View.OnClickListener onEmptyViewClickListener) {
        addEmptyView(getContentView(), 0, onEmptyViewClickListener);
    }

    /**
     * 隐藏空数据提示view
     */
    public void hideEmptyView() {
        if (mEmptyView != null) {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    public void showLoading() {
        mContentView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public void showErrorView() {
        mContentView.setVisibility(View.GONE);

        AnimatorSet set = new AnimatorSet();
        ObjectAnimator in = ObjectAnimator.ofFloat(mErrorView, "alpha", 1f);
        ObjectAnimator loadingOut = ObjectAnimator.ofFloat(mLoadingView, "alpha", 0f);

        set.playTogether(in, loadingOut);
        set.setDuration(200);
        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mErrorView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mLoadingView.setVisibility(View.GONE);
                mLoadingView.setAlpha(1f); // For future showLoading calls
            }
        });
        set.start();
    }

    public void showContent() {
        if (mContentView.getVisibility() == View.VISIBLE) {
            mErrorView.setVisibility(View.GONE);
            mLoadingView.setVisibility(View.GONE);
        } else {
            mErrorView.setVisibility(View.GONE);

            int translateDp = 40;
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator contentFadeIn = ObjectAnimator.ofFloat(mContentView, "alpha", 0f, 1f);
            ObjectAnimator contentTranslateIn = ObjectAnimator.ofFloat(mContentView, "translationY",
                    dpToPx(mLoadingView.getContext(), translateDp), 0);

            ObjectAnimator loadingFadeOut = ObjectAnimator.ofFloat(mLoadingView, "alpha", 1f, 0f);
            ObjectAnimator loadingTranslateOut = ObjectAnimator.ofFloat(mLoadingView, "translationY", 0,
                    -dpToPx(mLoadingView.getContext(), translateDp));

            set.playTogether(contentFadeIn, contentTranslateIn, loadingFadeOut, loadingTranslateOut);
            set.setDuration(500);
            set.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationStart(Animator animation) {
                    mContentView.setTranslationY(0);
                    mLoadingView.setTranslationY(0);
                    mContentView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoadingView.setVisibility(View.GONE);
                    mLoadingView.setAlpha(1f); // For future showLoading calls
                    mContentView.setTranslationY(0);
                    mLoadingView.setTranslationY(0);
                }
            });

            set.start();
        }
    }

    public int dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

}
