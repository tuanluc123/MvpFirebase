package com.maituanluc.basic1.ui.splash;

import android.os.Handler;

import com.maituanluc.basic1.data.AppDataManager;
import com.maituanluc.basic1.ui.base.BasePresenter;

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    public SplashPresenter(AppDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void decideNextActivity() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (getDataManager().getLoggedInMode()) {
                getMvpView().openMainActivity();
            } else {
                getMvpView().openLoginActivity();
            }
        }, 1000);

    }
}
