package com.trident.beyond.host;

import android.view.MenuItem;

/**
 *
 * Fragment对外开放的接口
 */
public interface BinderFragment {

    /**
     * 系统返回键事件，Fragment是否要自己处理
     *
     * @return true 表示我要自己拦截处理
     */
    boolean onBackPressed();

    /**
     * @return true 标识拦截音量上下键
     */
    boolean onKeyDownEvent(int code);

    /**
     * 导航栏（ActionBar）上的返回键事件，Fragment是否要自己处理
     *
     * @return true 表示我要自己拦截处理
     */
    boolean onMenuBackClick(MenuItem item);

    /**
     * 该方法本来打算放到Fragment的基类里，
     * 但是当发生不可预知的异常时，我目前采取的是保存当前Fragment的栈结构，
     * 恢复时Actionbar的状态也是需要恢复的，故该方法放在了这里。
     *
     * 发生的异常：比如三星手机调用系统拍照
     */
    void rebindActionBar();

}
