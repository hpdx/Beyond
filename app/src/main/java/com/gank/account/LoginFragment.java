package com.gank.account;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.anbetter.beyond.fragment.BaseVdbMvvmFragment;
import com.gank.R;
import com.gank.account.model.UserInfo;
import com.gank.account.view.LoginView;
import com.gank.account.viewmodel.LoginViewModel;

/**
 * 登录界面
 *
 * Created by android_ls on 2016/12/30.
 */

public class LoginFragment extends BaseVdbMvvmFragment<UserInfo, LoginView, LoginViewModel> implements LoginView {

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void rebindActionBar() {
        // 是否要显示顶部的导航栏（ActionBar）
        mPageFragmentHost.toggleActionBar(true);
        // 导航栏上要显示的Title
        mPageFragmentHost.setActionBarTitle("登录");
        // 导航栏上是否要显示返回图标
        mPageFragmentHost.displayActionBarBack(true);
    }

    @Override
    protected int getLayoutRes() {
        // 布局文件资源的Id
        return R.layout.vdb_fragment_login;
    }

    @Override
    protected LoginViewModel createViewModel() {
        return new LoginViewModel();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化组件

        // 调用登录方法
//        String phone = "";
//        String passwd = "";
//        viewModel.login(phone, passwd);
    }

    // LoginView
    @Override
    public void loginSuccess(UserInfo userInfo) {

    }

    // LoginView
    @Override
    public void loginFail(Exception error) {

    }

}
