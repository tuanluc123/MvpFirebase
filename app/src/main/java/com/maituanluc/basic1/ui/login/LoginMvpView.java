package com.maituanluc.basic1.ui.login;

import com.maituanluc.basic1.ui.base.MvpView;

public interface LoginMvpView extends MvpView {
    void openMainActivity();

    void onLoginButtonclick();

    void openRegisterUserActivity();

    void onRigisterButtonClick();
}
