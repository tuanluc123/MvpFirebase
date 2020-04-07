package com.maituanluc.basic1.ui.register;

import com.maituanluc.basic1.ui.base.MvpPresenter;

public interface RegisterMvpPresenter<V extends RegisterMvpView> extends MvpPresenter<V> {
    void startRegister(String email, String password);
    void onRegisterButtonClick();
}
