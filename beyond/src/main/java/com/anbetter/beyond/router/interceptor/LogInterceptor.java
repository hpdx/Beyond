package com.anbetter.beyond.router.interceptor;

import android.support.annotation.NonNull;

import com.anbetter.log.MLog;
import com.anbetter.beyond.router.interfaces.INavigation;
import com.anbetter.beyond.router.interfaces.Interceptor;
import com.anbetter.beyond.router.model.Chain;

/**
 *
 * Created by android_ls on 16/12/26.
 */

public class LogInterceptor implements Interceptor {
    private static final String SUFFIX = ".java";

    private static final int STACK_TRACE_INDEX = 5;

    @Override
    public void intercept(INavigation navigation, Chain chain) {
//        if (!BuildConfig.DEBUG) {
//            return;
//        }

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[STACK_TRACE_INDEX];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }

        if (className.contains("$")) {
            className = className.split("\\$")[0] + SUFFIX;
        }

        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();

        if (lineNumber < 0) {
            lineNumber = 0;
        }

        String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        String headString = "[ (" + className + ":" + lineNumber + ")#" + methodNameShort + " ] ";

        MLog.e(headString + buildMessage(chain));
    }

    @NonNull
    private String buildMessage(Chain chain) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("<----------Uri---------->");
        builder.append("\n");
        builder.append(chain.uri.toString());


//        try {
//            builder.append(URLDecoder.decode(chain.uri.toString(), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        builder.append("\n");
        if (chain.bundle != null) {
            builder.append("<----------Bundle---------->");
            builder.append("\n");
            for (String key : chain.bundle.keySet()) {
                Object obj = chain.bundle.get(key);
                if (obj != null) {
                    builder.append("\t");
                    builder.append(key);
                    builder.append(" : ");
                    builder.append(obj.toString());

//                    try {
//                        builder.append(URLDecoder.decode(chain.bundle.get(key).toString(), "UTF-8"));
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }

                    builder.append("\n");
                }
            }
        }

        if (chain.isIntercepted()) {
            builder.append("intercepted-->");
            builder.append(chain.isIntercepted());
        }
        return builder.toString();
    }

}