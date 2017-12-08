package com.trident.beyond.listener;

import android.view.View;

import java.util.List;

/**
 * Created by android_ls on 16/11/7.
 */

public interface OnItemClickListener2<T> {

    void onItemClick(View view, List<T> data, int position);

}
