package com.trident.beyond.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.trident.beyond.listener.OnItemClickListener;

import java.util.List;

/**
 *
 * Created by android_ls on 16/7/21.
 */
public abstract class BaseAdapter<M> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<M> mData;
    protected LayoutInflater mLayoutInflater;
    protected OnItemClickListener mOnItemClickListener;

    public BaseAdapter(Context context, List<M> data) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public BaseAdapter(Context context, List<M> data, OnItemClickListener listener) {
        this(context, data);
        mOnItemClickListener = listener;
    }

    public void updateAdapterData(List<M> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
