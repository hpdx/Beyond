package com.anbetter.beyond.network.upload;


import com.anbetter.beyond.listener.ResponseListener;
import com.anbetter.beyond.network.OKHttpRequest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 上传文件
 * <p>
 * Created by android_ls on 2017/1/18.
 */

public class OKHttpUpload<T> extends OKHttpRequest<T> {

    private Map<String, String> mFiles;

    public OKHttpUpload(String url, String fileKey, String filePath, Map<String, String> params) {
        super(url, params, null, null);
        mFiles = new HashMap<>(1);
        mFiles.put(fileKey, filePath);
        this.mParams = params;
    }

    public OKHttpUpload(String url, String fileKey, String filePath, Map<String, String> params,
                        ResponseListener<T> responseListener, Class<T> classParameter) {
        super(url, params, responseListener, classParameter);
        mFiles = new HashMap<>(1);
        mFiles.put(fileKey, filePath);
        this.mParams = params;
    }

    public OKHttpUpload(String url, Map<String, String> files, Map<String, String> params,
                        ResponseListener<T> responseListener, Class<T> classParameter) {
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
                File file = new File(filePath);
                if (file.exists()) {
                    String fileName = file.getName();
                    try {
                        fileName = URLEncoder.encode(file.getName(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + fileKey + "\"; filename=\"" + fileName + "\""),
                            RequestBody.create(MediaType.parse("application/octet-stream"), file));
                }
            }
        }

        return builder.build();
    }

}
