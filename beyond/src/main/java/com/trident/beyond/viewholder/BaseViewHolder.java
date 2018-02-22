package com.trident.beyond.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by android_ls on 16/8/24.
 */
public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {

    protected M mCellModel;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(M cellModel, final int position) {
        mCellModel = cellModel;
    }

    @Override
    public String toString() {
        return buildMessage(new Gson().toJson(mCellModel));
    }

    String buildMessage(String msg) {
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
