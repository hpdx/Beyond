package com.trident.dating.libcommon.listener;

/**
 *
 * Created by android_ls on 16/8/27.
 */
public interface ResponseListener<T> extends ResponseErrorListener {

    void onResponse(T response);

}
