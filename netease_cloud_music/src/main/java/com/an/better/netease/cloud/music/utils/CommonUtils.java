package com.an.better.netease.cloud.music.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.an.better.netease.cloud.music.App;

import java.util.Random;

/**
 * Created by jingbin on 2016/11/22.
 * 获取原生资源
 */
public class CommonUtils {

    /**
     * 随机颜色
     */
    public static int randomColor() {
        Random random = new Random();
        int red = random.nextInt(150) + 50;//50-199
        int green = random.nextInt(150) + 50;//50-199
        int blue = random.nextInt(150) + 50;//50-199
        return Color.rgb(red, green, blue);
    }

    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(App.get(), resId);
    }

    public static int getColor(int resId) {
        return ContextCompat.getColor(App.get(), resId);
    }

    public static Resources getResources() {
        return App.get().getResources();
    }

    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static float getDimension(int resId) {
        return getResources().getDimension(resId);
    }

}
