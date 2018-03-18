package com.dating.jd_home.test;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dating.jd_common.TestParcelable;

/**
 * Created by android_ls on 2018/3/14.
 */

public class ServiceManager {

    @Autowired(name = "/service/hello")
    TestProvider service;

    public ServiceManager() {
        ARouter.getInstance().inject(this);
    }

    public void gotoTest2Page(String name, int age, TestParcelable testParcelable) {
        service.gotoTest2Page(name, age, testParcelable);
    }

}
