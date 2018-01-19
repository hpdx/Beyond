package com.trident.dating.libnetwork.okhttp.filebody;


import com.trident.dating.libnetwork.okhttp.listener.DownloadListener;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/**
 *
 * Created by android_ls on 2017/10/31.
 */

public class FileResponseBody extends ResponseBody {

    private Response mResponse;
    private DownloadListener mDownloadListener;

    public FileResponseBody(Response originalResponse, DownloadListener listener) {
        this.mResponse = originalResponse;
        this.mDownloadListener = listener;
    }

    @Override
    public MediaType contentType() {
        return mResponse.body().contentType();
    }

    @Override
    public long contentLength() {
        return mResponse.body().contentLength();
    }

    @Override
    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(mResponse.body().source()) {
            long bytesReadCount = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReadCount += bytesRead == -1 ? 0 : bytesRead;

                if (mDownloadListener != null) {
                    mDownloadListener.onProgress(contentLength(), bytesReadCount);
                }
                return bytesRead;
            }
        });
    }

}
