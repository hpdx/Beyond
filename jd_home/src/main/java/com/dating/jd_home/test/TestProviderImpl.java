package com.dating.jd_home.test;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dating.jd_common.TestParcelable;

/**
 * Created by android_ls on 2018/3/14.
 */

@Route(path = "/service/hello", name = "测试服务")
public class TestProviderImpl implements TestProvider {

    @Override
    public void gotoTest2Page(String name, int age, TestParcelable testParcelable) {
        ARouter.getInstance()
                .build("/test/Test2Activity")
                .withString("name", name)
                .withInt("age", age)
                .withParcelable("test", testParcelable)
                .navigation();
    }

    @Override
    public void init(Context context) {

    }

}
