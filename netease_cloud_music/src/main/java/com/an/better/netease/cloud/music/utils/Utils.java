package com.an.better.netease.cloud.music.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.an.better.netease.cloud.music.douban.model.SubjectsBean;

import java.util.List;
import java.util.Random;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 *
 * Created by android_ls on 2018/1/22.
 */

public class Utils {

    // 过渡图的图片链接
    private static final String TRANSITION_URL_01 = "http://ojyz0c8un.bkt.clouddn.com/b_1.jpg";
    private static final String TRANSITION_URL_02 = "http://ojyz0c8un.bkt.clouddn.com/b_2.jpg";
    private static final String TRANSITION_URL_03 = "http://ojyz0c8un.bkt.clouddn.com/b_3.jpg";
    private static final String TRANSITION_URL_04 = "http://ojyz0c8un.bkt.clouddn.com/b_4.jpg";
    private static final String TRANSITION_URL_05 = "http://ojyz0c8un.bkt.clouddn.com/b_5.jpg";
    private static final String TRANSITION_URL_06 = "http://ojyz0c8un.bkt.clouddn.com/b_6.jpg";
    private static final String TRANSITION_URL_07 = "http://ojyz0c8un.bkt.clouddn.com/b_7.jpg";
    private static final String TRANSITION_URL_08 = "http://ojyz0c8un.bkt.clouddn.com/b_8.jpg";
    private static final String TRANSITION_URL_09 = "http://ojyz0c8un.bkt.clouddn.com/b_9.jpg";
    private static final String TRANSITION_URL_10 = "http://ojyz0c8un.bkt.clouddn.com/b_10.jpg";

    public static final String[] TRANSITION_URLS = new String[]{
            TRANSITION_URL_01, TRANSITION_URL_02, TRANSITION_URL_03
            , TRANSITION_URL_04, TRANSITION_URL_05, TRANSITION_URL_06
            , TRANSITION_URL_07, TRANSITION_URL_08, TRANSITION_URL_09
            , TRANSITION_URL_10
    };

    // 2张图的随机图
    private static final String HOME_TWO_01 = "http://ojyz0c8un.bkt.clouddn.com/home_two_01.png";
    private static final String HOME_TWO_02 = "http://ojyz0c8un.bkt.clouddn.com/home_two_02.png";
    private static final String HOME_TWO_03 = "http://ojyz0c8un.bkt.clouddn.com/home_two_03.png";
    private static final String HOME_TWO_04 = "http://ojyz0c8un.bkt.clouddn.com/home_two_04.png";
    private static final String HOME_TWO_05 = "http://ojyz0c8un.bkt.clouddn.com/home_two_05.png";
    private static final String HOME_TWO_06 = "http://ojyz0c8un.bkt.clouddn.com/home_two_06.png";
    private static final String HOME_TWO_07 = "http://ojyz0c8un.bkt.clouddn.com/home_two_07.png";
    private static final String HOME_TWO_08 = "http://ojyz0c8un.bkt.clouddn.com/home_two_08.png";
    private static final String HOME_TWO_09 = "http://ojyz0c8un.bkt.clouddn.com/home_two_09.png";
    public static final String[] HOME_TWO_URLS = new String[]{
            HOME_TWO_01, HOME_TWO_02, HOME_TWO_03, HOME_TWO_04
            , HOME_TWO_05, HOME_TWO_06, HOME_TWO_07, HOME_TWO_08
            , HOME_TWO_09
    };

    public static String getTwoImageUrl() {
        int index = new Random().nextInt(Utils.HOME_TWO_URLS.length);
        return Utils.HOME_TWO_URLS[index];
    }

    // 六图的随机图
    public static final String[] HOME_SIX_URLS = new String[] {
            "http://ojyz0c8un.bkt.clouddn.com/home_six_0.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_1.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_2.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_3.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_4.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_5.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_6.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_7.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_8.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_9.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_10.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_11.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_12.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_13.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_14.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_15.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_16.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_17.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_18.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_19.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_20.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_21.png",
            "http://ojyz0c8un.bkt.clouddn.com/home_six_22.png"
    };

    public static String getThreeImageUrl() {
        int index = new Random().nextInt(HOME_SIX_URLS.length);
        return Utils.HOME_SIX_URLS[index];
    }

    /**
     * 格式化导演、主演名字
     */
    public static String formatName(List<SubjectsBean.CastsBean> casts) {
        if (casts != null && casts.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < casts.size(); i++) {
                if (i < casts.size() - 1) {
                    stringBuilder.append(casts.get(i).name).append(" / ");
                } else {
                    stringBuilder.append(casts.get(i).name);
                }
            }
            return stringBuilder.toString();

        } else {
            return "佚名";
        }
    }

    /**
     * 格式化电影类型
     */
    public static String formatGenres(List<String> genres) {
        if (genres != null && genres.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < genres.size(); i++) {
                if (i < genres.size() - 1) {
                    stringBuilder.append(genres.get(i)).append(" / ");
                } else {
                    stringBuilder.append(genres.get(i));
                }
            }
            return stringBuilder.toString();

        } else {
            return "不知名类型";
        }
    }

    /**
     * 复制内容到剪切板
     * @param context
     * @param text
     */
    public static void copy(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager)context.getSystemService(CLIPBOARD_SERVICE);
        if(clipboard != null) {
            clipboard.setPrimaryClip(ClipData.newPlainText(text, text));
        }
    }

    /**
     * 使用浏览器打开链接
     */
    public static void openLink(Context context, String content) {
        Uri issuesUrl = Uri.parse(content);
        Intent intent = new Intent(Intent.ACTION_VIEW, issuesUrl);
        context.startActivity(intent);
    }

    /**
     * 判断网络是否连通
     */
    public static boolean isNetworkConnected(Context context) {
        try {
            if (context != null) {
                @SuppressWarnings("static-access")
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                NetworkInfo info = cm.getActiveNetworkInfo();
                return info != null && info.isConnected();
            } else {
                /**如果context为空，就返回false，表示网络未连接*/
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断手机是否安装某个应用
     *
     * @param context
     * @param appPackageName 应用包名
     * @return true：安装，false：未安装
     */
    public static boolean isApplicationAvilible(Context context, String appPackageName) {
        try {
            // 获取packagemanager
            PackageManager packageManager = context.getPackageManager();
            // 获取所有已安装程序的包信息
            List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
            if (pinfo != null) {
                for (int i = 0; i < pinfo.size(); i++) {
                    String pn = pinfo.get(i).packageName;
                    if (appPackageName.equals(pn)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception ignored) {
            return false;
        }
    }

}
