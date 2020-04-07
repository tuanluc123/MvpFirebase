package com.maituanluc.basic1.ui.user;

import com.maituanluc.basic1.data.AppDataManager;
import com.maituanluc.basic1.ui.base.BasePresenter;

public class UserPresenter<V extends UserMvpView> extends BasePresenter<V> implements UserMvpPresenter<V> {
    public UserPresenter(AppDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void backMainActivity() {
        getMvpView().openMainActivity();
    }
}
