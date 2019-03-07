package com.gank.account.view;


import com.anbetter.beyond.mvvm.MvvmBaseView;
import com.gank.account.model.UserInfo;

public interface LoginView extends MvvmBaseView<UserInfo> {

    void loginSuccess(UserInfo userInfo);

    void loginFail(Exception error);

}