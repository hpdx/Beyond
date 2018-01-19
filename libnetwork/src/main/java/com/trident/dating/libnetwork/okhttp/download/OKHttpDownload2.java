package com.trident.dating.libnetwork.okhttp.download;

import com.trident.dating.libnetwork.okhttp.interceptor.ResponseInterceptor;
import com.trident.dating.libnetwork.okhttp.interceptor.RetryInterceptor;
import com.trident.dating.libnetwork.okhttp.listener.DownloadListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载文件
 * <p>
 * Created by android_ls on 2017/9/15.
 */

public class OKHttpDownload2 implements Callback {

    private DownloadListener mDownloadListener;
    private String mUrl;
    private String mFilePath;

    public OKHttpDownload2(String url, String filePath, DownloadListener downloadFileListener) {
        this.mUrl = url;
        this.mFilePath = filePath;
        this.mDownloadListener = downloadFileListener;
    }

    public void execute() {
        Request request = new Request.Builder()
                .get()
                .url(mUrl)
                .tag(mUrl)
                .build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10000, TimeUnit.MILLISECONDS);
        builder.readTimeout(10000, TimeUnit.MILLISECONDS);
        if (mDownloadListener != null) {
            builder.networkInterceptors().add(new ResponseInterceptor(mDownloadListener));
        }
        builder.addInterceptor(new RetryInterceptor(2));

        builder.build().newCall(request).enqueue(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        if (mDownloadListener != null) {
            mDownloadListener.onFailure(e);
        }
    }

    @Override
    public void onResponse(Call call, Response response) {
        try {
            if (response.isSuccessful() && mFilePath != null && mDownloadListener != null) {
                FileOutputStream fileOutputStream = new FileOutputStream(mFilePath);
                fileOutputStream.write(response.body().bytes());
                fileOutputStream.flush();
                fileOutputStream.close();
                mDownloadListener.onSuccess(mFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
