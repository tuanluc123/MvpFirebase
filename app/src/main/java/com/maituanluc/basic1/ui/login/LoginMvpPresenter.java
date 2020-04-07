package com.maituanluc.basic1.ui.login;

import com.maituanluc.basic1.ui.base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {
    void startLogin(String email);

    void neckRegisterUserActivity();
}
