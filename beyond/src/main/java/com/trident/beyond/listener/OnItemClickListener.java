package com.trident.beyond.listener;

import android.view.View;

/**
 * Created by android_ls on 16/11/7.
 */

public interface OnItemClickListener<T> {

    void onItemClick(View view, T data, int position);

}
