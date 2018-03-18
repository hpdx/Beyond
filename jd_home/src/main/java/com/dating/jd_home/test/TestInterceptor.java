package com.dating.jd_home.test;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.anbetter.log.MLog;

/**
 * Created by android_ls on 2018/3/13.
 */

//@Interceptor(priority = 4)
public class TestInterceptor implements IInterceptor {

    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        MLog.i("path:" + postcard.getPath());
        MLog.i("group:" + postcard.getGroup());

        //这里进行逻辑处理
        //callback.onContinue(postcard);
        //callback.onInterrupt(postcard)
    }

    @Override
    public void init(Context context) {
        MLog.i(TestInterceptor.class.getSimpleName() + " has been init");

    }

}
