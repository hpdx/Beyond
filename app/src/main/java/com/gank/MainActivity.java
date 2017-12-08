package com.gank;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.Toolbar;

import com.gank.actionbar.ActionBarHelper;
import com.gank.base.BaseFragmentActivity;
import com.gank.utils.ToastUtils;
import com.trident.beyond.host.BinderFragment;

public class MainActivity extends BaseFragmentActivity {

    private Handler mHandler;
    private boolean mConfirmingExit;
    private Runnable mExitConfirmsRunnable;

    private BlankFragment mBlankFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler(Looper.getMainLooper());
        mExitConfirmsRunnable = new Runnable() {
            @Override
            public void run() {
                mConfirmingExit = false;
            }
        };

        mBlankFragment = BlankFragment.newInstance();
        mNavigationManager.showHomePage(mBlankFragment);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBarHelper = new ActionBarHelper(this, getSupportActionBar(), toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExitConfirmsRunnable = null;
    }

    @Override
    public void onBackPressed() {
        BinderFragment currentPage = mNavigationManager.getActivePage();
        if (currentPage != null) {
            if (currentPage.onBackPressed()) {
                return;
            }
        }

        if (!mNavigationManager.goBack()) {
            doubleConfirmExitApp();
        }
    }

    private void doubleConfirmExitApp() {
        if (!mConfirmingExit) {
            mConfirmingExit = true;
            ToastUtils.showToast(R.string.confirm_exit);
            mHandler.postDelayed(mExitConfirmsRunnable, 2000);
        } else {
            finish();
        }
    }

    @Override
    public void onBackStackChanged() {

    }

    @Override
    public void showGlobalBannerTips(String message) {

    }

    @Override
    public void showFullscreen() {

    }

    @Override
    public void clearFullscreen() {

    }

    @Override
    public void setSoftInputMode(int mode) {

    }

}
