package com.maituanluc.basic1.ui.main;

import com.maituanluc.basic1.ui.base.MvpView;


public interface MainMvpView extends MvpView {
    void openSplashActivity();
    void openUserActivity();
    void openRecycleView();
    void openDialogAddUser();
    void setUpRecycleview();
    void setupListenerRegistration();
}
