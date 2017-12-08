package com.trident.beyond.widgets;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.trident.beyond.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by android_ls on 2017/2/8.
 */

public class PtrHeaderView extends FrameLayout implements PtrUIHandler {

    private ImageView ivLoading;
    private AnimationDrawable mAnimationDrawable;

    public PtrHeaderView(Context context) {
        super(context);
        setupViews();
    }

    public PtrHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupViews();
    }

    private void setupViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.dating_header_loading, this);
        ivLoading = (ImageView) findViewById(R.id.iv_loading);
        ivLoading.setBackgroundResource(R.drawable.animation_refresh_00_);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        ivLoading.setBackgroundResource(R.drawable.animation_refresh_00_);
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
            mAnimationDrawable = null;
        }
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        ivLoading.setBackgroundResource(R.drawable.pull_refresh_anim);
        mAnimationDrawable = (AnimationDrawable) ivLoading.getBackground();
        mAnimationDrawable.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        if (mAnimationDrawable != null && mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}
