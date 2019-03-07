package com.anbetter.beyond.network.listener;


import com.anbetter.beyond.listener.ResponseListener;

/**
 * Created by android_ls on 2017/10/31.
 */

public interface UploadProgressListener<T> extends ResponseListener<T> {

    void onProgress(int fileCount, String filename, long current, long total);

}
