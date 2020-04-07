package com.maituanluc.basic1.ui.register;

import com.maituanluc.basic1.ui.base.MvpView;

public interface RegisterMvpView extends MvpView {
    void createAccount(String email, String password);
    void openLoginActivity();
    boolean getValidateForm();
}
