package com.trident.dating.libnetwork.okhttp.interceptor;

import com.trident.dating.libnetwork.okhttp.filebody.FileResponseBody;
import com.trident.dating.libnetwork.okhttp.listener.DownloadListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * Created by android_ls on 2017/9/15.
 */

public class ResponseInterceptor implements Interceptor {

    private DownloadListener mDownloadListener;

    public ResponseInterceptor(DownloadListener listener) {
        this.mDownloadListener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.isSuccessful() && mDownloadListener != null) {
            return response
                    .newBuilder()
                    .body(new FileResponseBody(response, mDownloadListener))
                    .build();
        }
        return response;
    }

}
