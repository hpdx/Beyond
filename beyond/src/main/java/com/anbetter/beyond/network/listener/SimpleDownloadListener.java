package com.anbetter.beyond.network.listener;

import java.io.IOException;

/**
 * Created by android_ls on 2017/2/17.
 */

public abstract class SimpleDownloadListener implements DownloadListener {

    @Override
    public void onProgress(long totalLength, long currentLength) {

    }

    @Override
    public void onFailure(IOException e) {

    }
}
