package com.trident.beyond.list.helper;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trident.beyond.core.IModel;
import com.trident.beyond.listener.OnViewCallback;
import com.trident.beyond.viewholder.BaseViewHolder;

/**
 * Created by android_ls on 16/8/23.
 */
public abstract class ViewHolderProvider<M extends IModel, V extends BaseViewHolder<M>> {

    protected boolean childViewCallback;

    public ViewHolderProvider() {

    }

    public ViewHolderProvider(boolean childViewCallback) {
        this.childViewCallback = childViewCallback;
    }

    public boolean isChildViewCallback() {
        return childViewCallback;
    }

    public V onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, OnViewCallback<M> callback) {
        return null;
    }

    public V onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return null;
    }

    public void onBindViewHolder(@NonNull V viewHolder, @NonNull M cellModel, int position) {
        viewHolder.bind(cellModel, position);
    }

}
