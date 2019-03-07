package com.anbetter.beyond.dialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.lang.reflect.Field;

/**
 * 为了解决如下异常：
 * java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
 *
 * Created by android_ls on 2017/6/1.
 */

public class SafeDialogFragment extends DialogFragment {

    /**
     * 显示Dialog
     *
     * @param manager
     * @param tag
     */
    public void show(FragmentManager manager, String tag) {
        // mDismissed = false;
        try {
            Field dismissed = DialogFragment.class.getDeclaredField("mDismissed");
            dismissed.setAccessible(true);
            dismissed.set(this, false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // mShownByMe = true;
        try {
            Field shown = DialogFragment.class.getDeclaredField("mShownByMe");
            shown.setAccessible(true);
            shown.set(this, true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

}
