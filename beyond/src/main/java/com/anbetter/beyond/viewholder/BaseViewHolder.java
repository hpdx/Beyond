package com.anbetter.beyond.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.anbetter.beyond.listener.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by android_ls on 16/8/24.
 */
public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {

    protected Context mContext;
    protected M mCellModel;
    protected OnItemClickListener<M> mOnItemClickListener;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }

    public BaseViewHolder(View itemView, OnItemClickListener<M> onItemClickListener) {
        this(itemView);
        mOnItemClickListener = onItemClickListener;
    }

    public BaseViewHolder(LayoutInflater layoutInflater, int resId, ViewGroup parent) {
        super(layoutInflater.inflate(resId, parent, false));
        mContext = itemView.getContext();
    }

    public BaseViewHolder(LayoutInflater layoutInflater, int resId, ViewGroup parent,
                          OnItemClickListener<M> onItemClickListener) {
        this(layoutInflater, resId, parent);
        mOnItemClickListener = onItemClickListener;
    }

    public void bind(M cellModel, final int position) {
        mCellModel = cellModel;
    }

    public void setOnItemClickListener(OnItemClickListener<M> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public String toString() {
        return buildMessage(new Gson().toJson(mCellModel));
    }

    protected String buildMessage(String msg) {
        String message;
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }
        return message;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int viewId) {
        return (T) itemView.findViewById(viewId);
    }

}
