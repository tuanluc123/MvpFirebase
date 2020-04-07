package com.maituanluc.basic1.ui.splash;

import com.maituanluc.basic1.ui.base.MvpPresenter;

public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
    void decideNextActivity();
}
