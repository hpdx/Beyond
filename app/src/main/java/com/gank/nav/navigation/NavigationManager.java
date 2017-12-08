package com.gank.nav.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.anbetter.log.MLog;
import com.gank.R;
import com.gank.base.BaseFragmentActivity;
import com.gank.utils.MainThreadStack;
import com.trident.better.router.interfaces.INavigation;
import com.trident.beyond.host.BinderFragment;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by android_ls on 2016/12/26.
 * <p>
 * 禁止继承和重写，防止直接对栈的操作
 */
public final class NavigationManager implements INavigation<Fragment> {

    public static final String TAG = "NavigationManager";

    public final static String BACK_STACK_STATE_KEY = "back_stack_state";

    protected final Stack<NavigationState> mBackStack = new MainThreadStack<>();

    protected FragmentManager mFragmentManager;
    protected BaseFragmentActivity mActivity;
    private Fragment mHomeFragment;

    public NavigationManager(BaseFragmentActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public boolean canNavigate() {
        return mActivity != null && !mActivity.isStateSaved();
    }

    @Override
    public String getCurrentPage() {
        if (mBackStack.isEmpty()) {
            return null;
        }
        return mBackStack.peek().pageName;
    }

    public String getPageByIndex(int index) {
        if (mBackStack.isEmpty()) {
            return null;
        }
        return mBackStack.get(index).pageName;
    }

    public Fragment getCurrentHomePage() {
        return mHomeFragment;
    }

    public void showHomePage(Fragment fragment) {
        if (canNavigate()) {
            MLog.i(TAG, "mBackStack.isEmpty()：" + mBackStack.isEmpty());
            if (!mBackStack.isEmpty()) {
                mBackStack.clear();
            }
            MLog.i(TAG, "isBackStackEmpty()：" + isBackStackEmpty());

            if (!isBackStackEmpty()) {
                mFragmentManager.popBackStack(NavigationState.TOP_STATE, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            mHomeFragment = fragment;
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            if(!fragment.isAdded()) {
                ft.replace(R.id.content_frame, fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void goHome() {
        if (canNavigate()) {
            if (!mBackStack.isEmpty()) {
                mBackStack.clear();
            }

            if (!isBackStackEmpty()) {
                mFragmentManager.popBackStackImmediate(NavigationState.TOP_STATE, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }

    public void clearBackStack() {
        if (canNavigate()) {
            if (!mBackStack.isEmpty()) {
                mBackStack.clear();
            }

            if (!isBackStackEmpty()) {
                mFragmentManager.popBackStackImmediate();
            }
        }
    }

    public void showPage(String pageName, Fragment fragment) {
        if (canNavigate()) {
            NavigationState state = new NavigationState(pageName, mBackStack.isEmpty() ?
                    NavigationState.TOP_STATE : NavigationState.NON_TOP_STATE);

            FragmentTransaction ft = mFragmentManager.beginTransaction();

            //  可能某些页面需要单独的进场动画.
            //  ft.setCustomAnimations(R.anim.dialog_show,R.anim.dialog_hide);
            if(!fragment.isAdded()) {
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack(state.backStackName);
                ft.commitAllowingStateLoss();
                mBackStack.push(state);
            }
        }
    }

    @Override
    public boolean goBack() {
        if (!canNavigate() || mBackStack.isEmpty() || isBackStackEmpty()) {
            return false;
        }

        try {
            NavigationState state = mBackStack.pop();
            MLog.i("goBack() state.pageName = " + state.pageName);
            mFragmentManager.popBackStack();
        } catch (EmptyStackException ex) {
            return false;
        }
        return true;
    }

    public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener listener) {
        mFragmentManager.addOnBackStackChangedListener(listener);
    }

    public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener listener) {
        mFragmentManager.removeOnBackStackChangedListener(listener);
    }

    public boolean isBackStackEmpty() {
        return mFragmentManager.getBackStackEntryCount() == 0;
    }

    public int getBackStackCount() {
        return mFragmentManager.getBackStackEntryCount();
    }

    public BinderFragment getActivePage() {
        return (BinderFragment) mFragmentManager.findFragmentById(R.id.content_frame);
    }

    public void saveInstanceState(Bundle savedInstanceState) {
        if (mBackStack.isEmpty()) {
            return;
        }
        ArrayList<NavigationState> asList = new ArrayList<>(mBackStack);
        savedInstanceState.putParcelableArrayList(BACK_STACK_STATE_KEY, asList);
        MLog.i("Serialize backStack size = " + asList.size());
    }

    public boolean restoreInstanceState(Bundle savedInstanceState) {
        ArrayList<NavigationState> contents = savedInstanceState.getParcelableArrayList(BACK_STACK_STATE_KEY);
        if (contents == null || contents.size() == 0 || getActivePage() == null) {
            return false;
        }

        for (NavigationState st : contents) {
            mBackStack.push(st);
        }

        getActivePage().rebindActionBar();
        MLog.i("Deserialize backStack size = " + contents.size());
        return true;
    }

}
