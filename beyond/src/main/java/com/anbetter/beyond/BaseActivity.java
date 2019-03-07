package com.anbetter.beyond;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.anbetter.beyond.rxbus.RxBus;
import com.anbetter.beyond.statusbar.StatusBarFontColorUtils;

/**
 * <p>
 * Created by android_ls on 2018/11/5.
 *
 * @author 李松
 * @version 1.0
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 任总要求去掉状态栏，全屏显示
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarFontColorUtils.statusBarLightMode(this, true);
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        super.onDestroy();
    }

}
