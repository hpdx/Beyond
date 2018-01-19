package com.gank.account.viewmodel;

import com.gank.account.model.UserInfo;
import com.gank.account.view.LoginView;
import com.gank.network.Apis;
import com.trident.beyond.core.MvvmBaseViewModel;
import com.trident.dating.libcommon.listener.ResponseListener;

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