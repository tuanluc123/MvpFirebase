package com.maituanluc.basic1.ui.user;

import com.maituanluc.basic1.ui.base.MvpPresenter;

public interface UserMvpPresenter<V extends UserMvpView> extends MvpPresenter<V> {
    void backMainActivity();
}
