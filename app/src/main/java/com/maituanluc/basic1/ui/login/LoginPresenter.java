package com.maituanluc.basic1.ui.login;

import com.maituanluc.basic1.data.AppDataManager;
import com.maituanluc.basic1.ui.base.BasePresenter;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    public LoginPresenter(AppDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void startLogin(String email) {
        getDataManager().putEmail(email);
        getDataManager().setLoggedInMode(true);
        getMvpView().openMainActivity();
    }

    @Override
    public void neckRegisterUserActivity() {
        getMvpView().openRegisterUserActivity();
    }
}
