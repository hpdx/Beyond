package com.anbetter.beyond.listener;

import android.view.View;

import java.util.List;

/**
 *
 * Created by android_ls on 16/11/7.
 */

public class OnItemClickAdapter<T> implements OnItemClickListener<T> {

    @Override
    public void onItemClick(View view, T data, int position) {

    }

    @Override
    public void onItemClick(View view, T data, int position, int code) {

    }

    public void onItemClick(View view, List<T> data, int position) {

    }

    public void onItemLongClickListener(View view, T data, int position) {

    }

}
