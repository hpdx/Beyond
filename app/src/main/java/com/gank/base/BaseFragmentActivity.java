package com.gank.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;

import com.anbetter.beyond.BaseActivity;
import com.anbetter.beyond.host.BinderFragment;
import com.anbetter.beyond.host.PageFragmentHost;
import com.anbetter.beyond.router.Router;
import com.anbetter.beyond.router.interceptor.LogInterceptor;
import com.anbetter.log.MLog;
import com.gank.BuildConfig;
import com.gank.actionbar.ActionBarHelper;
import com.gank.nav.navigation.NavigationManager;

/**
 * 应用程序主Activity
 * <p/>
 * 1、提供ActionBar的开关（显示/隐藏）
 * 2、提供用户点击返回键事件的处理方法
 * 3、提供用户点击ActionBar上返回按钮事件的处理方法
 * 4、提供设置ActionBar上标题的方法
 * 5、提供设置ActionBar上返回按钮是否显示的方法
 */
public abstract class BaseFragmentActivity extends BaseActivity implements PageFragmentHost,
        FragmentManager.OnBackStackChangedListener {

    private static final String TAG = "BaseFragmentActivity";

    protected ActionBarHelper mActionBarHelper;
    protected NavigationManager mNavigationManager;

    protected boolean mStateSaved;
    protected Handler mHandler;
    protected Bundle mSavedInstanceState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResId());
        this.mStateSaved = false;
        mSavedInstanceState = savedInstanceState;
        mNavigationManager = new NavigationManager(this);
        mNavigationManager.addOnBackStackChangedListener(this);
        mHandler = new Handler(Looper.getMainLooper());

        if (BuildConfig.DEBUG) {
            Router.install(mNavigationManager)
                    .addInterceptor(new LogInterceptor());
        } else {
            Router.install(mNavigationManager);
        }

        setupViews();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSavedInstanceState != null && mNavigationManager != null) {
                    mNavigationManager.restoreInstanceState(mSavedInstanceState);
                }
            }
        }, 1000);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mStateSaved = true;
        MLog.i(TAG, "onSaveInstanceState");
        if (mNavigationManager != null) {
            mNavigationManager.saveInstanceState(outState);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (mNavigationManager != null && savedInstanceState != null) {
            mNavigationManager.restoreInstanceState(savedInstanceState);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MLog.i(TAG, "onStart");
        mStateSaved = false;
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        MLog.i(TAG, "onResumeFragments");
        mStateSaved = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        MLog.i(TAG, "onStop");
        mStateSaved = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Router.unInstall();
        if (mNavigationManager != null) {
            mNavigationManager.removeOnBackStackChangedListener(this);
        }
    }

    public boolean isStateSaved() {
        return mStateSaved;
    }

    public abstract int getLayoutResId();

    public abstract void setupViews();

    /**
     * 提供用户点击ActionBar上返回按钮事件的处理方法
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mNavigationManager != null) {
            BinderFragment currentPage = mNavigationManager.getActivePage();
            switch (item.getItemId()) {
                case android.R.id.home:
                    if (currentPage != null) {
                        if (!currentPage.onMenuBackClick(item)) {
                            mNavigationManager.goBack();
                        }
                    }
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 提供用户点击返回键事件的处理方法
     */
    @Override
    public void onBackPressed() {
        if (mNavigationManager != null) {
            BinderFragment currPage = mNavigationManager.getActivePage();
            if (currPage != null && currPage.onBackPressed()) {
                return;
            }

            if (!mNavigationManager.goBack()) {
                super.onBackPressed();
            }
        }
    }

    /**
     * 提供ActionBar的开关（显示/隐藏）
     *
     * @param visible
     */
    @Override
    public void toggleActionBar(boolean visible) {
        mActionBarHelper.toggleActionBar(visible);
    }

    /**
     * 提供设置ActionBar上标题的方法
     *
     * @param title
     */
    @Override
    public void setActionBarTitle(CharSequence title) {
        mActionBarHelper.setActionBarTitle(title);
    }

    @Override
    public void setActionBarImageTitle(int resId) {
        mActionBarHelper.setActionBarImageTitle(resId);
    }

    /**
     * 提供设置ActionBar上返回按钮是否显示的方法
     *
     * @param displayBack
     */
    @Override
    public void displayActionBarBack(boolean displayBack) {
        mActionBarHelper.displayActionBarBack(displayBack);
    }

    @Override
    public void setHomeAsUpIndicator(int resId) {
        mActionBarHelper.setHomeAsUpIndicator(resId);
    }

    @Override
    public void displayActionBarRightText(CharSequence title, View.OnClickListener listener) {
        mActionBarHelper.displayActionBarRightText(title, listener);
    }

    @Override
    public void displayActionBarRightIcon(int resId, View.OnClickListener listener) {
        mActionBarHelper.displayActionBarRightIcon(resId, listener);
    }

    @Override
    public void displayActionBarLeftText(CharSequence title, View.OnClickListener listener) {
        mActionBarHelper.displayActionBarLeftText(title, listener);
    }

    @Override
    public void displayActionBarLeftText(CharSequence title, int resId, View.OnClickListener listener) {
        mActionBarHelper.displayActionBarLeftText(title, resId, listener);
    }

    @Override
    public void displayActionBarRightText(CharSequence title, int resId, View.OnClickListener listener) {
        mActionBarHelper.displayActionBarRightText(title, resId, listener);
    }

    @Override
    public void displayActionBarRightIconText(CharSequence title, int resId, View.OnClickListener listener) {
        mActionBarHelper.displayActionBarRightIconText(title, resId, listener);
    }

    @Override
    public void setActionBarLeftTextColor(int resId) {
        mActionBarHelper.setActionBarLeftTextColor(resId);
    }

    @Override
    public void setActionBarRightTextColor(int resId) {
        mActionBarHelper.setActionBarRightTextColor(resId);
    }

    @Override
    public void setTitleOnClickListener(View.OnClickListener onClickListener) {
        mActionBarHelper.setTitleOnClickListener(onClickListener);
    }

    @Override
    public String getCurrentPage() {
        if (mNavigationManager != null) {
            return mNavigationManager.getCurrentPage();
        }
        return null;
    }

    @Override
    public Fragment getCurrentHomePage() {
        if (mNavigationManager != null) {
            return mNavigationManager.getCurrentHomePage();
        }
        return null;
    }

    @Override
    public int getBackStackCount() {
        if (mNavigationManager != null) {
            return mNavigationManager.getBackStackCount();
        }
        return 0;
    }

}
