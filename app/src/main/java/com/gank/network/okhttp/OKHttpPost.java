package com.gank.network.okhttp;

import com.trident.beyond.listener.ResponseListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 当发送Post请求时，传入的参数想使用Map<String, String>类型，那么请使用该类
 *
 * Created by android_ls on 2016/12/23.
 */

public class OKHttpPost<T> extends OKHttpRequest<T> {

    private Map<String, String> mParams;

    public OKHttpPost(String url, Map<String, String> params, ResponseListener<T> responseListener) {
        this(url, params, responseListener, null);
    }

    public OKHttpPost(String url, Map<String, String> params,
                      ResponseListener<T> responseListener, Class<T> classParameter) {
        super(OKHttpRequest.POST, url, null, responseListener, classParameter);
        this.mParams = params;
    }

    @Override
    protected RequestBody getRequestBody() {
        FormBody.Builder builder = new FormBody.Builder();

        try {
            for (String key : mParams.keySet()) {
                builder.addEncoded(key, URLEncoder.encode(mParams.get(key), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        mRequestBody = builder.build();
        return mRequestBody;
    }

}
