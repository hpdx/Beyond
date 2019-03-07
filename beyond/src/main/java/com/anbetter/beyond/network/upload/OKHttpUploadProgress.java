package com.anbetter.beyond.network.upload;


import com.anbetter.beyond.network.OKHttpHelper;
import com.anbetter.beyond.network.OKHttpRequest;
import com.anbetter.beyond.network.filebody.FileRequestBody;
import com.anbetter.beyond.network.listener.UploadProgressListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 上传文件
 * <p>
 * Created by android_ls on 2017/1/18.
 */

public class OKHttpUploadProgress<T> extends OKHttpRequest<T> {

    private Map<String, String> mFiles;
    private Map<String, String> mParams;

    public OKHttpUploadProgress(String url, String fileKey, String filePath, Map<String, String> params) {
        super(url, params, null, null);
        mFiles = new HashMap<>(1);
        mFiles.put(fileKey, filePath);
        this.mParams = params;
    }

    public OKHttpUploadProgress(String url, String fileKey, String filePath, Map<String, String> params,
                                UploadProgressListener<T> responseListener, Class<T> classParameter) {
        super(url, params, responseListener, classParameter);
        mFiles = new HashMap<>(1);
        mFiles.put(fileKey, filePath);
        this.mParams = params;
    }

    public OKHttpUploadProgress(String url, Map<String, String> files, Map<String, String> params,
                                UploadProgressListener<T> responseListener, Class<T> classParameter) {
        super(url, params, responseListener, classParameter);
        this.mFiles = files;
        this.mParams = params;
    }

    protected RequestBody getRequestBody() {
        if(mParams == null) {
            mParams = new HashMap<>();
        }

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (mParams != null && !mParams.isEmpty()) {
            for (HashMap.Entry<String, String> entry : mParams.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
            }
        }

        if (mFiles != null && !mFiles.isEmpty()) {
            for (HashMap.Entry<String, String> entry : mFiles.entrySet()) {
                String fileKey = entry.getKey();
                String filePath = entry.getValue();
                final File file = new File(filePath);
                if (file.exists()) {
                    builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + fileKey + "\"; filename=\"" + file.getName() + "\""),
                            new FileRequestBody(file) {
                                @Override
                                public void onProgress(final String filename, final long current, final long total) {
                                    OKHttpHelper.get().getDelivery().post(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(mResponseListener instanceof UploadProgressListener) {
                                                ((UploadProgressListener)mResponseListener).onProgress(mFiles.size(), filename, current, total);
                                            }
                                        }
                                    });
                                }
                            });
                }
            }
        }

        return builder.build();
    }

}
