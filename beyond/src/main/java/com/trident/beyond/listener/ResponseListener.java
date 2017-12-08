package com.trident.beyond.listener;

/**
 * Created by android_ls on 16/8/27.
 */
public interface ResponseListener<T> extends ResponseErrorListener {

    /**
     * 网络请求完回调
     *
     * @param response
     */
    void onResponse(T response);

}
