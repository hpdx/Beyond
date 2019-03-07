package com.anbetter.beyond.utils;

import android.content.Context;
import android.text.TextUtils;

import com.anbetter.beyond.dialog.LoadingDialog;

/**
 * <p>
 * Created by android_ls on 2019/2/20.
 *
 * @author 李松
 * @version 1.0
 */
public class LoadingUtils {

    private static LoadingDialog mProgressDialog;

    public static void show(Context context) {
        show(context,null, false);
    }

    public static void show(Context context, boolean cancel) {
        show(context, null, cancel);
    }

    public static void show(Context context, String title) {
        show(context, title, false);
    }

    public static void show(Context context, String title, boolean cancel) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            return;
        }

        LoadingDialog.Builder builder = new LoadingDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setMessage(title);
        }

        builder.setCancelable(cancel);
        mProgressDialog = builder.create();
        mProgressDialog.show();
    }

    public static void cancel() {
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            mProgressDialog = null;
        }
    }

}
