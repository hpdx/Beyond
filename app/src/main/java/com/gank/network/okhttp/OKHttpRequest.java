package com.gank.network.okhttp;

import com.anbetter.log.MLog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.trident.beyond.core.IRequest;
import com.trident.beyond.listener.ResponseListener;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OKHttp3的使用帮助类
 * <p>
 * 支持全局连接超时、读取数据超时、写数据超时的配置
 * 支持全局headers的配置
 * 支出全局参数的配置
 * <p>
 * 支持局部配置覆盖全局配置的需求（当局部和全局都配置，起作用的是局部配置）
 * <p>
 * Created by android_ls on 2016/12/22.
 */

public class OKHttpRequest<T> implements Callback, IRequest {

    public static final int GET = 0;
    public static final int POST = 1;

    protected long mReadTimeOut;
    protected long mWriteTimeOut;
    protected long mConnectTimeout;

    protected String mUrl;
    protected int mMethod;
    protected byte[] mPostBody;
    protected Class<T> mTargetClass;
    protected ResponseListener<T> mResponseListener;

    protected Call mCall;
    protected RequestBody mRequestBody;
    protected Map<String, String> mHeaders;

    public OKHttpRequest() {

    }

    public OKHttpRequest(String url, ResponseListener<T> responseListener, Class<T> classParameter) {
        this(OKHttpRequest.GET, url, null, responseListener, classParameter);
    }

    public OKHttpRequest(String url, String jsonStr,
                         ResponseListener<T> responseListener, Class<T> classParameter) {
        this(OKHttpRequest.POST, url, jsonStr.getBytes(), responseListener, classParameter);
    }

    public OKHttpRequest(int method, String url, byte[] postBody,
                         ResponseListener<T> responseListener, Class<T> classParameter) {
        mMethod = method;
        mUrl = url;
        mPostBody = postBody;
        mResponseListener = responseListener;
        mTargetClass = classParameter;
    }

    public void setMethod(int method) {
        mMethod = method;
    }

    public String getUrl() {
        return mUrl;
    }

    @Override
    public void cancel() {
        if(mCall != null) {
            mCall.cancel();
        }
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setPostBody(byte[] postBody) {
        mPostBody = postBody;
    }

    public void setTargetClass(Class<T> classParameter) {
        mTargetClass = classParameter;
    }

    public void setResponseListener(ResponseListener<T> responseListener) {
        mResponseListener = responseListener;
    }

    public void setReadTimeOut(long readTimeOut) {
        this.mReadTimeOut = readTimeOut;
    }

    public void setWriteTimeOut(long writeTimeOut) {
        this.mWriteTimeOut = writeTimeOut;
    }

    public void setConnectTimeout(long connectTimeout) {
        this.mConnectTimeout = connectTimeout;
    }

    protected RequestBody getRequestBody() {
        if (mRequestBody == null && mPostBody != null) {
            mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), mPostBody);
        }
        return mRequestBody;
    }

    protected Map<String, String> getHeaders() {
        if (mHeaders == null) {
            mHeaders = OKHttpConfig.get().getHeaders();
        }
        return mHeaders;
    }

    protected void setHeaders(Map<String, String> headers) {
        mHeaders = headers;
    }

    public IRequest execute() {
        Request.Builder requestBuilder = new Request.Builder();
        if (OKHttpRequest.GET == mMethod) {
            requestBuilder.get();
        } else if (OKHttpRequest.POST == mMethod) {
            requestBuilder.post(getRequestBody());
        }

        requestBuilder.url(mUrl);
        requestBuilder.tag(mUrl);

        // 添加headers
        Map<String, String> headers = getHeaders();
        if (headers != null && headers.size() > 0) {
            requestBuilder.headers(Headers.of(headers));
        }

        Request request = requestBuilder.build();

        if (mConnectTimeout > 0) {
            OKHttpConfig.get().getOkHttpClientBuilder().connectTimeout(mConnectTimeout, TimeUnit.MILLISECONDS);
        }

        if (mConnectTimeout > 0) {
            OKHttpConfig.get().getOkHttpClientBuilder().readTimeout(mReadTimeOut, TimeUnit.MILLISECONDS);
        }

        if (mConnectTimeout > 0) {
            OKHttpConfig.get().getOkHttpClientBuilder().writeTimeout(mWriteTimeOut, TimeUnit.MILLISECONDS);
        }

        OkHttpClient okHttpClient = OKHttpConfig.get().getOkHttpClient();
        mCall = okHttpClient.newCall(request);
        mCall.enqueue(this);

        return this;
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        if (mResponseListener == null) {
            return;
        }

        OKHttpConfig.get().getDelivery().post(new Runnable() {
            @Override
            public void run() {
                mResponseListener.onErrorResponse(e);
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (mTargetClass == null || mResponseListener == null) {
            return;
        }

        try {
            if (response.isSuccessful()) {
                final String jsonString = response.body().string();
                MLog.i("--->jsonString = " + jsonString);

                if(mTargetClass != null) {
                    JsonParser parser = new JsonParser();
                    JsonObject object = parser.parse(jsonString).getAsJsonObject();
//                    Gson gson = new GsonBuilder()
//                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                            .create();

                    Gson gson = new Gson();
                    final T resultData = gson.fromJson(object, mTargetClass);
                    OKHttpConfig.get().getDelivery().post(new Runnable() {
                        @Override
                        public void run() {
                            mResponseListener.onResponse(resultData);
                        }
                    });

//                    int result = gson.fromJson(object.get("result"), int.class);
//                    if (result == 0) {
//                        JsonObject data = object.getAsJsonObject("data");
//                        final T resultData = gson.fromJson(data, mTargetClass);
//
//                        OKHttpConfig.get().getDelivery().post(new Runnable() {
//                            @Override
//                            public void run() {
//                                mResponseListener.onResponse(resultData);
//                            }
//                        });
//                    }
                } else {
                    OKHttpConfig.get().getDelivery().post(new Runnable() {
                        @Override
                        public void run() {
                            mResponseListener.onResponse((T) jsonString);
                        }
                    });
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

}
