package com.maituanluc.basic1.ui.main;

import com.maituanluc.basic1.data.AppDataManager;
import com.maituanluc.basic1.ui.base.BasePresenter;


public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {
    public MainPresenter(AppDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void setUserLoggedOut() {
        getDataManager().clear();
        getMvpView().openSplashActivity();
    }

    @Override
    public void neckUserActivity() {
        getMvpView().openUserActivity();
    }

    @Override
    public void showListUser() {
        try {
            getMvpView().openRecycleView();
            getMvpView().setUpRecycleview();
        }catch (Exception e){

        }

    }

    @Override
    public void showDialogAddUser() {
        getMvpView().openDialogAddUser();
    }

    @Override
    public void handlerListenerRegistration() {
        getMvpView().setupListenerRegistration();
    }
}
