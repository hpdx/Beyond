package com.trident.beyond.viewholder;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.trident.beyond.listener.OnItemClickListener;

/**
 * Created by android_ls on 16/7/21.
 */
public abstract class BaseVdbViewHolder<M, VDB extends ViewDataBinding>
        extends BaseViewHolder<M> {

    protected VDB binding;
    protected Context mContext;

    public BaseVdbViewHolder(VDB viewDataBinding) {
        super(viewDataBinding.getRoot());
        mContext = viewDataBinding.getRoot().getContext();
        this.binding = viewDataBinding;
    }

    public BaseVdbViewHolder(VDB viewDataBinding, OnItemClickListener<M> listener) {
       this(viewDataBinding);
       this.mOnItemClickListener = listener;
    }

}
