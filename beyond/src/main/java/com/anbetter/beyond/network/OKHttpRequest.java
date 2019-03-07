package com.anbetter.beyond.network;

import com.anbetter.beyond.listener.ResponseListener;
import com.anbetter.beyond.model.IRequest;
import com.anbetter.log.MLog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by android_ls on 2016/12/22.
 */
public class OKHttpRequest<T> implements Callback, IRequest {

    public static final int GET = 0;
    public static final int POST = 1;

    protected String mUrl;
    protected Map<String, String> mParams;
    protected Class<T> mTargetClass;
    protected ResponseListener<T> mResponseListener;

    private int mMethod;
    private byte[] mPostBody;
    private boolean mOriginalResult;

    private Call mCall;
    private Map<String, String> mHeaders;
    private Gson mGson;

    public OKHttpRequest() {

    }

    public OKHttpRequest(String url, ResponseListener<T> responseListener, Class<T> classParameter) {
        this(GET, url, null, responseListener, classParameter);
    }

    public OKHttpRequest(String url, String jsonStr,
                         ResponseListener<T> responseListener, Class<T> classParameter) {
        this(POST, url, jsonStr.getBytes(), responseListener, classParameter);
    }

    public OKHttpRequest(String url, Map<String, String> params,
                         ResponseListener<T> responseListener, Class<T> classParameter) {
        mMethod = POST;
        mUrl = url;
        if (params != null) {
            String jsonStr = new Gson().toJson(params);
            mPostBody = jsonStr.getBytes();
        }
//        mParams = params;
        mResponseListener = responseListener;
        mTargetClass = classParameter;
    }

    public OKHttpRequest(String url, Map<String, Object> params,
                         ResponseListener<T> responseListener, Class<T> classParameter, boolean withObject) {
        mMethod = POST;
        mUrl = url;
        if (params != null) {
            String jsonStr = new Gson().toJson(params);
            mPostBody = jsonStr.getBytes();
        }
//        mParams = params;
        mResponseListener = responseListener;
        mTargetClass = classParameter;
    }

    public OKHttpRequest(int method, String url, byte[] postBody,
                         ResponseListener<T> responseListener, Class<T> classParameter) {
        mMethod = method;
        mUrl = url;
        mPostBody = postBody;
        mResponseListener = responseListener;
        mTargetClass = classParameter;
    }

    public OKHttpRequest setMethod(int method) {
        mMethod = method;
        return this;
    }

    public OKHttpRequest setGson(Gson gson) {
        mGson = gson;
        return this;
    }

    public OKHttpRequest setOriginalResult(boolean originalResult) {
        mOriginalResult = originalResult;
        return this;
    }

    public OKHttpRequest setPostBody(byte[] postBody) {
        mPostBody = postBody;
        return this;
    }

    public OKHttpRequest setTargetClass(Class<T> classParameter) {
        mTargetClass = classParameter;
        return this;
    }

    public OKHttpRequest setResponseListener(ResponseListener<T> responseListener) {
        mResponseListener = responseListener;
        return this;
    }

    public OKHttpRequest setUrl(String url) {
        mUrl = url;
        return this;
    }

    @Override
    public String getUrl() {
        return mUrl;
    }

    @Override
    public void cancel() {
        if (mCall != null) {
            mCall.cancel();
        }
    }

    protected RequestBody getRequestBody() {
        if (mParams != null) {
            FormBody.Builder builder = new FormBody.Builder();
            try {
                for (String key : mParams.keySet()) {
                    String value = mParams.get(key);
                    if (value != null) {
                        builder.addEncoded(key, URLEncoder.encode(value, "UTF-8"));
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return builder.build();
        } else if (mPostBody != null) {
            return RequestBody.create(MediaType.parse("application/json;charset=utf-8"), mPostBody);
        }
        return null;
    }

    protected Map<String, String> getHeaders() {
        if (mHeaders == null) {
            mHeaders = OKHttpHelper.get().getHeaders();
        }
        return mHeaders;
    }

    protected void setHeaders(Map<String, String> headers) {
        mHeaders = headers;
    }

    public IRequest enqueue() {
        mCall = OKHttpHelper.get().getOkHttpClient().newCall(getRequest());
        mCall.enqueue(this);
        return this;
    }

    public Response execute() throws IOException {
        mCall = OKHttpHelper.get().getOkHttpClient().newCall(getRequest());
        return mCall.execute();
    }

    private Request getRequest() {
        Request.Builder requestBuilder = new Request.Builder();
        if (GET == mMethod) {
            requestBuilder.get();
        } else if (POST == mMethod) {
            requestBuilder.post(getRequestBody());
        }

        requestBuilder.url(mUrl);
        requestBuilder.tag(mUrl);

        Map<String, String> headers = getHeaders();
        if (headers != null && headers.size() > 0) {
            requestBuilder.headers(Headers.of(headers));
        }

        return requestBuilder.build();
    }

    @Override
    public void onResponse(Call call, final Response response) {
        if (mResponseListener == null) {
            return;
        }

        try {
            if (response.isSuccessful()) {
                final String jsonString = response.body().string();
                MLog.i(response.request().url());
                MLog.i(jsonString);

                if (mOriginalResult) {
                    OKHttpHelper.get().getDelivery().post(new Runnable() {
                        @Override
                        public void run() {
                            mResponseListener.onResponse((T) jsonString);
                        }
                    });
                } else {
                    if (mGson == null) {
                        // mGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
                        mGson = new Gson();
                    }

                    JSONObject jsonObject = new JSONObject(jsonString);
                    int code = jsonObject.optInt("code");
                    if (code == 200) {
                        JSONObject jsonData = jsonObject.optJSONObject("data");
                        if (jsonData != null) {
                            JsonParser parser = new JsonParser();
                            JsonObject object = parser.parse(jsonData.toString()).getAsJsonObject();
                            JsonObject data = object.getAsJsonObject();
                            final T resultData = mGson.fromJson(data, mTargetClass);

                            OKHttpHelper.get().getDelivery().post(new Runnable() {
                                @Override
                                public void run() {
                                    mResponseListener.onResponse(resultData);
                                }
                            });
                        } else {
                            JsonParser parser = new JsonParser();
                            JsonObject object = parser.parse(jsonString).getAsJsonObject();
                            JsonObject data = object.getAsJsonObject();
                            final T resultData = mGson.fromJson(data, mTargetClass);

                            OKHttpHelper.get().getDelivery().post(new Runnable() {
                                @Override
                                public void run() {
                                    mResponseListener.onResponse(resultData);
                                }
                            });
                        }
                    } else {
                        JsonParser parser = new JsonParser();
                        JsonObject object = parser.parse(jsonString).getAsJsonObject();
                        JsonObject data = object.getAsJsonObject();
                        final T resultData = mGson.fromJson(data, mTargetClass);

                        OKHttpHelper.get().getDelivery().post(new Runnable() {
                            @Override
                            public void run() {
                                mResponseListener.onResponse(resultData);
                            }
                        });
                    }
                }
            } else {
                OKHttpHelper.get().getDelivery().post(new Runnable() {
                    @Override
                    public void run() {
                        // TODO 开发阶段，调试使用
                        mResponseListener.onErrorResponse(new Exception(response.toString()));
//                        mResponseListener.onErrorResponse(new Exception("连接服务器失败，请稍后再试"));
                    }
                });
            }
        } catch (final Exception e) {
            e.printStackTrace();
            OKHttpHelper.get().getDelivery().post(new Runnable() {
                @Override
                public void run() {
                    mResponseListener.onErrorResponse(e);
                }
            });
        }
    }

    @Override
    public void onFailure(Call call, final IOException error) {
        if (mResponseListener == null) {
            return;
        }

        OKHttpHelper.get().getDelivery().post(new Runnable() {
            @Override
            public void run() {
                if (error instanceof SocketTimeoutException
                        || error instanceof ConnectException) {
                    mResponseListener.onErrorResponse(new IOException("网络连接超时，请重试"));
                } else {
                    mResponseListener.onErrorResponse(error);
                }
            }
        });
    }

}
