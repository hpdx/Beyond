package com.trident.dating.libnetwork.okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.trident.dating.libnetwork.okhttp.interceptor.HttpLoggingInterceptor;
import com.trident.dating.libnetwork.okhttp.interceptor.RetryInterceptor;
import com.trident.dating.libnetwork.okhttp.utils.SSLSocketConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 *
 * Created by android_ls on 2016/12/22.
 */

public class OKHttpHelper {

    /**
     * 默认的超时时间，10秒
     * 连接超时
     * 读取数据超时
     * 写数据超时
     */
    private static final int DEFAULT_MILLISECONDS = 10 * 1000;
    private static OKHttpHelper mOK;

    private Handler mHandler;
    private OkHttpClient.Builder mBuilder;
    private OkHttpClient mOkHttpClient;

    private OKHttpHelper() {
        init();
    }

    public static OKHttpHelper get() {
        if (mOK == null) {
            synchronized (OKHttpHelper.class) {
                if (mOK == null) {
                    mOK = new OKHttpHelper();
                }
            }
        }
        return mOK;
    }

    public OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            build();
        }
        return mOkHttpClient;
    }

    public Handler getDelivery() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    private void init() {
        if (mBuilder == null) {
            mBuilder = new OkHttpClient.Builder();
            mBuilder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            mBuilder.readTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            mBuilder.writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
            mBuilder.retryOnConnectionFailure(true);
            mBuilder.followRedirects(true); // 请求支持重定向
            mBuilder.addInterceptor(new RetryInterceptor(2)); // 弱网环境，网络请求失败了，重试2次
        }
    }

    public OKHttpHelper setSSLSocketFactory(Context context) {
        // 设置证书
        try {
            X509TrustManager x509TrustManager = SSLSocketConfig.getX509TrustManager(context);
            if (x509TrustManager != null) {
//                SSLContext sslContext = SSLContext.getInstance("TLS");
                SSLContext sslContext = SSLContext.getInstance("TLSv1", "AndroidOpenSSL");
                sslContext.init(null, new TrustManager[]{x509TrustManager}, null);
                mBuilder.sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public OKHttpHelper setCookieJar(Context context) {
        // cookie持久化存储，如果cookie不过期，则一直有效
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(context.getApplicationContext()));
        mBuilder.cookieJar(cookieJar);
        return this;
    }

    public OKHttpHelper log() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mBuilder.addInterceptor(loggingInterceptor);
        return this;
    }

    public void build() {
        if (mBuilder == null) {
            init();
        }
        mOkHttpClient = mBuilder.build();
    }

    /**
     * 获取全局的CookieStore
     *
     * @return
     */
    public List<Cookie> getCookies(HttpUrl httpUrl) {
        if (mOkHttpClient != null) {
            return mOkHttpClient.cookieJar().loadForRequest(httpUrl);
        }
        return null;
    }

    /**
     * 清空cookies
     */
    public void clearCookies() {
        ((ClearableCookieJar) mOkHttpClient.cookieJar()).clear();
    }

    /**
     * 全局的headers配置
     *
     * @return
     */
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
//        headers.put("User-Agent", getUserAgent());
        return headers;
    }

    /**
     * 取消指定url的网络请求
     *
     * @param url
     */
    public void cancel(String url) {
        if (!TextUtils.isEmpty(url)) {
            OkHttpClient okHttpClient = getOkHttpClient();
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
        getOkHttpClient().dispatcher().cancelAll();
    }

    /**
     * 当前将要执行的请求是否已经在执行
     *
     * @param url
     * @return
     */
    public boolean isExecuted(String url) {
        if (!TextUtils.isEmpty(url)) {
            OkHttpClient okHttpClient = getOkHttpClient();
            Dispatcher dispatcher = okHttpClient.dispatcher();
            for (Call call : dispatcher.queuedCalls()) {
                if (url.equals(call.request().tag())) {
                    return true;
                }
            }

            for (Call call : dispatcher.runningCalls()) {
                if (url.equals(call.request().tag())) {
                    return true;
                }
            }
        }
        return false;
    }

}
