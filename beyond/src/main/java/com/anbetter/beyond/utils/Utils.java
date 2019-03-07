package com.anbetter.beyond.utils;

public class Utils {

    /**
     * 将毫秒数格式化为如：00:01 01:22
     *
     * @param time
     * @return
     */
    public static String formatLongToString(long time) {
        StringBuffer duration = new StringBuffer();
        long mints = time / 1000 / 60 % 60;
        if (mints < 10) {
            duration.append("0");
        }
        duration.append(mints).append(":");
        long seconds = time / 1000 % 60;
        if (seconds < 10) {
            duration.append("0");
            if (seconds <= 0) {
                duration.append("0");
            } else {
                duration.append(seconds);
            }
        } else {
            duration.append(seconds);
        }

        return duration.toString();
    }
}
