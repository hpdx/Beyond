package com.an.better.netease.cloud.music.utils;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.an.better.netease.cloud.music.App;

/**
 * Created by android_ls on 2016/12/26.
 */

public final class ToastUtils {

    public static void showToast(String message) {
        if(TextUtils.isEmpty(message)) {
            return;
        }
        Toast toast = Toast.makeText(App.get().getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(int resId) {
        Toast toast = Toast.makeText(App.get().getApplicationContext(),
                App.get().getString(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(String message, int py) {
        Toast toast = Toast.makeText(App.get().getApplicationContext(),
                message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, py);
        toast.show();
    }

}
