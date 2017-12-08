package com.gank.network.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by android_ls on 2016/12/22.
 */

public class OKHttpConfig {

    /**
     * 默认的超时时间，10秒
     * 连接超时
     * 读取数据超时
     * 写数据超时
     */
    public static final int DEFAULT_MILLISECONDS = 10 * 1000;

    /**
     * 用于在主线程执行的调度器
     */
    private Handler mHandler;

    private static OKHttpConfig mOK;
    private OkHttpClient.Builder mOkHttpClientBuilder;
    private OkHttpClient mOkHttpClient;

    private OKHttpConfig() {
        mOkHttpClientBuilder = new OkHttpClient.Builder();
        mOkHttpClientBuilder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        mOkHttpClientBuilder.readTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        mOkHttpClientBuilder.writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        mOkHttpClientBuilder.retryOnConnectionFailure(false);
        // 请求支持重定向
        mOkHttpClientBuilder.followRedirects(true);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClientBuilder.addInterceptor(loggingInterceptor);

        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OKHttpConfig get() {
        if(mOK == null) {
            synchronized (OKHttpConfig.class) {
                if (mOK == null) {
                    mOK = new OKHttpConfig();
                }
            }
        }
        return mOK;
    }

    public OkHttpClient.Builder getOkHttpClientBuilder() {
       return mOkHttpClientBuilder;
    }

    public OkHttpClient getOkHttpClient() {
        if(mOkHttpClient == null) {
            mOkHttpClient = mOkHttpClientBuilder.build();
        }
        return mOkHttpClient;
    }

    public Handler getDelivery() {
        return mHandler;
    }

    /**
     * 全局的headers配置
     * @return
     */
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        return headers;
    }

    /**
     * 取消指定url的网络请求
     *
     * @param url
     */
    public void cancel(String url) {
        if (!TextUtils.isEmpty(url)) {
            OkHttpClient okHttpClient = OKHttpConfig.get().getOkHttpClient();
            for (Call call : okHttpClient.dispatcher().queuedCalls()) {
                if (url.equals(call.request().tag())) {
                    call.cancel();
                    break;
                }
            }

            for (Call call : okHttpClient.dispatcher().runningCalls()) {
                if (url.equals(call.request().tag())) {
                    call.cancel();
                    break;
                }
            }
        }
    }

    /**
     * 取消所有网络请求
     **/
    public void cancelAll() {
        OKHttpConfig.get().getOkHttpClient().dispatcher().cancelAll();
    }

}
