package com.trident.beyond.dialog;


import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.trident.beyond.R;


/**
 *  使用下面的方式显示一个加载进度圆圈
 *  LoadingDialog loadingDialog = new LoadingDialog.Builder(Context context).create();
 *  loadingDialog.show();
 *
 *  Created by android_ls on 16/1/3.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * 定义Dialog构造器
     */
    public static class Builder {

        /**
         * Context上下文
         */
        private Context mContext;
        /**
         * 对话框布局
         */
        private View mContentView;
        /**
         * 对话框显示提示内容
         */
        private TextView mMessage;

        /**
         * 用户点击手机的返回键，是否可以cancel加载进度条，默认是可以cancel
         */
        private boolean mBackCancelable = false;

        /**
         * 用户点击除加载进度之外的区域，是否可以cancel加载进度条，默认是cancel不掉
         */
        private boolean mCanceledOnTouchOutside = false;

        /**
         * 构造函数
         *
         * @param context 上下文
         */
        public Builder(Context context) {
            mContext = context;
            LayoutInflater inflater = LayoutInflater.from(mContext);
            mContentView = inflater.inflate(R.layout.bolo_loading_dialog, null);
            mMessage = (TextView) mContentView.findViewById(R.id.loading_message_txt);
        }

        /**
         * 设置提示信息
         *
         * @param message 提示信息
         * @return 当前对象
         */
        public Builder setMessage(String message) {
            if(!TextUtils.isEmpty(message)) {
                mMessage.setText(message);
                mMessage.setVisibility(View.VISIBLE);
            } else {
                mMessage.setVisibility(View.GONE);
            }
            return this;
        }

        public Builder setCancelable(boolean flag) {
            mBackCancelable = flag;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean cancel) {
            mCanceledOnTouchOutside = cancel;
            return this;
        }

        /**
         * 创建对话框
         *
         * @return 自定义对话框实例
         */
        public LoadingDialog create() {
            LoadingDialog dialog = new LoadingDialog(mContext, R.style.bolo_loading_dialog);
            dialog.setContentView(mContentView);
            dialog.setCancelable(mBackCancelable);
            dialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
            return dialog;
        }
    }

}
