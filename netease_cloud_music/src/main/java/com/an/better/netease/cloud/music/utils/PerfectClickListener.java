package com.an.better.netease.cloud.music.utils;

import android.view.View;
import android.view.View.OnClickListener;

import java.util.Calendar;

/**
 * 避免在1秒内出发多次点击
 * Created by yangcai on 2016/1/15.
 */
public abstract class PerfectClickListener implements OnClickListener {

    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    private int viewId = -1;

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (viewId != v.getId()) {
            viewId = v.getId();
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        } else {
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                onNoDoubleClick(v);
            }
        }
    }

    protected abstract void onNoDoubleClick(View v);

}
