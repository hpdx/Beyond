package com.gank.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.gank.App;


/**
 * Created by android_ls on 2016/12/26.
 */

public final class ToastUtils {

    public static void showToast(String message) {
        Toast toast = Toast.makeText(App.get(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(int resId) {
        Toast toast = Toast.makeText(App.get(), App.get().getString(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(String message, int py) {
        Toast toast = Toast.makeText(App.get(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, py);
        toast.show();
    }

}
