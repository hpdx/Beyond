package com.trident.beyond.listener;

import android.view.View;

import java.util.List;

/**
 *
 * Created by android_ls on 16/11/7.
 */

public abstract class OnItemClickListener<T> {

    public void onClick(View view, T data, int position) {
    }

    public void onClick(View view, T data, int position, int code) {
    }

    public void onClick(View view, List<T> data, int position) {
    }

}
