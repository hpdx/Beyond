package com.dating.jd_home.test;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by android_ls on 2018/3/14.
 */

@Route(path = "/user/*")
public class LoginDegradeService implements DegradeService {

    Context mContext;

    @Override
    public void onLost(Context context, Postcard postcard) {
        //
        Log.d("", "onLost:" + postcard);
        ARouter.getInstance().build("/router/login").navigation();
    }

    @Override
    public void init(Context context) {
        this.mContext = context ;
    }

}
