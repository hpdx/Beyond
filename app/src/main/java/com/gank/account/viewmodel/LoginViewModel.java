package com.gank.account.viewmodel;

import com.anbetter.beyond.listener.ResponseListener;
import com.anbetter.beyond.mvvm.MvvmBaseViewModel;
import com.gank.account.model.UserInfo;
import com.gank.account.view.LoginView;
import com.gank.Apis;

public class LoginViewModel extends MvvmBaseViewModel<UserInfo, LoginView> {

    public void login(final String phone, String password) {
        Apis.login(phone, password, new ResponseListener<UserInfo>() {

            @Override
            public void onErrorResponse(Exception error) {
                if (isViewAttached()) {
                    getView().loginFail(error);
                }
            }

            @Override
            public void onResponse(UserInfo response) {
                if (isViewAttached()) {
                    getView().loginSuccess(null);
                }
            }
        });
    }

}