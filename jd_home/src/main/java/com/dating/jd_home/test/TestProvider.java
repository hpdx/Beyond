package com.dating.jd_home.test;

import com.alibaba.android.arouter.facade.template.IProvider;
import com.dating.jd_common.TestParcelable;

/**
 * Created by android_ls on 2018/3/14.
 */

public interface TestProvider extends IProvider {

    void gotoTest2Page(String name, int age, TestParcelable testParcelable);

}
