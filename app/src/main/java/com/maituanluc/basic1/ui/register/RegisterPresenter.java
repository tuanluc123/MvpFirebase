package com.maituanluc.basic1.ui.register;

import com.maituanluc.basic1.data.AppDataManager;
import com.maituanluc.basic1.ui.base.BasePresenter;

public class RegisterPresenter<V extends RegisterMvpView> extends BasePresenter<V> implements RegisterMvpPresenter<V> {
    public RegisterPresenter(AppDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void startRegister(String email, String password) {
        getMvpView().createAccount(email, password);
    }

    @Override
    public void onRegisterButtonClick() {
        getMvpView().openLoginActivity();
    }
}
