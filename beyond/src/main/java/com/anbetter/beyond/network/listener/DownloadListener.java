package com.anbetter.beyond.network.listener;

import java.io.IOException;

/**
 *
 * Created by android_ls on 2017/2/17.
 */

public interface DownloadListener {

    void onProgress(long totalLength, long currentLength);

    void onSuccess(String filePath);

    void onFailure(IOException e);
}
