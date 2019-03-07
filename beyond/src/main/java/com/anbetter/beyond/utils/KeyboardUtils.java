package com.anbetter.beyond.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * https://www.cnblogs.com/plokmju/p/7978500.html
 * <p>
 * Created by android_ls on 2019/2/28.
 *
 * @author 李松
 * @version 1.0
 */
public class KeyboardUtils {

    /**
     * 弹出软键盘
     */
    public static void show(EditText editText) {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.showSoftInput(editText, 0);
        }
    }

    /**
     * 弹出软键盘
     */
    public static void show(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void close(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 隐藏软键盘
     * @param activity
     */
    public static void close(Activity activity) {
        if (activity == null || activity.getCurrentFocus() == null) {
            return;
        }

        InputMethodManager im = ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE));
        if (im.isActive()) {
            im.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void  toggleSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0,0);
        }
    }

}
