package com.maituanluc.basic1.ui.main;

import com.maituanluc.basic1.data.model.User;
import com.maituanluc.basic1.ui.base.MvpPresenter;

import java.util.List;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void setUserLoggedOut();
    void neckUserActivity();
    void showListUser();
    void showDialogAddUser();
    void handlerListenerRegistration();
}
