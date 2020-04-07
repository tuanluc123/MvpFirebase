package com.maituanluc.basic1.ui.base;

import com.maituanluc.basic1.data.AppDataManager;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    AppDataManager dataManager;
    private V mMvpView;

    public BasePresenter(AppDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView= mvpView;
    }

    public V getMvpView(){
        return mMvpView;
    }

    public AppDataManager getDataManager(){
        return dataManager;
    }
}
