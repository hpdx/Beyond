package com.trident.better.router;

import android.net.Uri;
import android.text.TextUtils;

import com.anbetter.log.MLog;
import com.trident.better.router.interfaces.ISwitcher;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by android_ls on 16/12/26.
 */

public class SwitchersProvider {

    private static Map<String, ISwitcher> pageMap = new HashMap<>();

    public static final String SCHEME_DATING = "hd";
    public static final String HOST = "dating.app";

    public static final String SCHEMA_HTTP = "http";
    public static final String SCHEMA_HTTPS = "https";
    public static final String PAGE_NAME_WEB = "web";

    public static void addSwitcher(String pageName, ISwitcher switcher) {
        if (!pageMap.containsKey(pageName)) {
            pageMap.put(pageName, switcher);
        }
    }

    public static ISwitcher getSwitcher(Uri uri) {
        String pageName = "";
        if (TextUtils.equals(uri.getScheme(), SwitchersProvider.SCHEME_DATING)) {
            if(!TextUtils.isEmpty(uri.getPath())) {
                pageName = uri.getPath().substring(1);
            }
        } else if (TextUtils.equals(uri.getScheme(), SwitchersProvider.SCHEMA_HTTPS)
                ||TextUtils.equals(uri.getScheme(), SwitchersProvider.SCHEMA_HTTP)) {
            pageName = PAGE_NAME_WEB;
        }
        MLog.i("pageName = " + pageName);

        ISwitcher switcher = pageMap.get(pageName);
        if (switcher == null) {
            throw new PageNotFoundException(pageName);
        }
        return switcher;
    }

    public static class PageNotFoundException extends RuntimeException {
        PageNotFoundException(String key) {
            super("你可能没有注册路由（"+ (TextUtils.isEmpty(key)?"key == null":key) +"）到SwitchersProvider（路由注册表）中，请检查路由注册表");
        }
    }
}
