package com.anbetter.beyond.helper;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.anbetter.beyond.listener.OnItemClickListener;
import com.anbetter.beyond.model.IModel;
import com.anbetter.beyond.viewholder.BaseViewHolder;

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

    public V onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, OnItemClickListener<M> onItemClickListener) {
        return null;
    }

    public V onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return null;
    }

    public void onBindViewHolder(@NonNull V viewHolder, @NonNull M cellModel, int position) {
        viewHolder.bind(cellModel, position);
    }

}
