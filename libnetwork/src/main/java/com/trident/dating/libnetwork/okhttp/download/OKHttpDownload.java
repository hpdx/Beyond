package com.trident.dating.libnetwork.okhttp.download;

import com.anbetter.log.MLog;
import com.trident.dating.libnetwork.okhttp.OKHttpHelper;
import com.trident.dating.libnetwork.okhttp.listener.DownloadListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载文件
 *
 * Created by android_ls on 2017/2/17.
 */

public class OKHttpDownload implements Callback {

    private DownloadListener mDownloadFileListener;
    private String mUrl;
    private String mFilePath;

    public OKHttpDownload(String url, String filePath, DownloadListener downloadFileListener) {
        this.mUrl = url;
        this.mFilePath = filePath;
        this.mDownloadFileListener = downloadFileListener;
    }

    public void execute() {
        Request.Builder requestBuilder = new Request.Builder()
                .get()
                .url(mUrl)
                .tag(mUrl);
        Request request = requestBuilder.build();

        OkHttpClient okHttpClient = OKHttpHelper.get().getOkHttpClient();
        okHttpClient.newCall(request).enqueue(this);
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        OKHttpHelper.get().getDelivery().post(new Runnable() {
            @Override
            public void run() {
                if(mDownloadFileListener != null) {
                    mDownloadFileListener.onFailure(e);
                }
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) {
        File file = new File(mFilePath);
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            final long totalLength = response.body().contentLength();
//            MLog.i("totalLength------>" + totalLength);

            long current = 0;
            int len;
            byte[] buffer = new byte[1024];
            inputStream = response.body().byteStream();
            outputStream = new FileOutputStream(file);
            while ((len = inputStream.read(buffer)) != -1) {
                current += len;
                outputStream.write(buffer, 0, len);
//                MLog.i("currentLength------>" + current);

                final long currentLength = current;
                OKHttpHelper.get().getDelivery().post(new Runnable() {
                    @Override
                    public void run() {
                        if(mDownloadFileListener != null) {
                            mDownloadFileListener.onProgress(totalLength, currentLength);
                        }
                    }
                });
            }
            outputStream.flush();

            if(mDownloadFileListener != null) {
                mDownloadFileListener.onSuccess(mFilePath);
            }
        } catch (final IOException e) {
            MLog.i(e.toString());
            OKHttpHelper.get().getDelivery().post(new Runnable() {
                @Override
                public void run() {
                    if(mDownloadFileListener != null) {
                        mDownloadFileListener.onFailure(e);
                    }
                }
            });
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                MLog.i(e.toString());
            }
        }
    }

}
