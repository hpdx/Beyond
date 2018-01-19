package com.trident.dating.libnetwork.okhttp.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *  模拟服务端响应的结果
 *
 * Created by android_ls on 2017/9/13.
 */

public class TestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String responseString = "{\"default_login_type\":1,\"user_agreements_url\":\"https:\\/\\/ztest2-mapi.jikeyue.com\\/init\\/user_agreements\",\"ok\":1,\"end_memory\":1.299,\"peak_memory\":1.391}";//模拟的错误的返回值
        return new Response.Builder()
                .code(307)
                .request(request)
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .message("我是测试数据")
                .build();
    }

}
