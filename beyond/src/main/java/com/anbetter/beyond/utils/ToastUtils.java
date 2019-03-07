package com.anbetter.beyond.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by android_ls on 2016/12/26.
 */

public final class ToastUtils {

    public static void show(Context context, String message) {
        if(TextUtils.isEmpty(message)) {
            return;
        }
        Toast toast = Toast.makeText(context,
                message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void show(Context context, int resId) {
        Toast toast = Toast.makeText(context,
                context.getString(resId), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void show(Context context, String message, int py) {
        Toast toast = Toast.makeText(context,
                message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, py);
        toast.show();
    }

    public static void showToast(Context context, String message) {
        if(TextUtils.isEmpty(message)) {
            return;
        }
        Toast toast = Toast.makeText(context,
                message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
