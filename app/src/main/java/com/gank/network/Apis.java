package com.gank.network;

import android.net.Uri;

import com.gank.account.model.UserInfo;
import com.gank.common.model.GanKBlock;
import com.gank.day.model.GanKDayBlock;
import com.trident.dating.libcommon.IRequest;
import com.trident.dating.libcommon.listener.ResponseListener;
import com.trident.dating.libnetwork.okhttp.OKHttpRequest;

import java.util.HashMap;

/**
 * 服务端提供的API
 *
 * Created by android_ls on 2016/12/27.
 */

public class Apis {

    public static IRequest getGankDay(String url, final ResponseListener<GanKDayBlock> listener) {
        return new OKHttpRequest<>(url, listener, GanKDayBlock.class).enqueue();
    }

    public static IRequest getGank(String url, final ResponseListener<GanKBlock> listener) {
        return new OKHttpRequest<>(url, listener, GanKBlock.class).enqueue();
    }

    public static void login(String phone, String pwd, ResponseListener<UserInfo> listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", pwd);

        final String url = Uri.withAppendedPath(Uri.parse("http://gank.io"),
                ApiUrls.LOGIN).buildUpon().toString();
        new OKHttpRequest<>(url, params, listener, UserInfo.class).enqueue();
    }

}
