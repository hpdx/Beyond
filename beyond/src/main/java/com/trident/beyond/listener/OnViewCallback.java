package com.trident.beyond.listener;

import android.view.View;

/**
 * 在ViewHolder内部调用其所依赖View（Fragment）的方法
 * Created by android_ls on 2017/3/6.
 */

public interface OnViewCallback<T> {

    void onClick(View view, T data, int position, int code);

}
