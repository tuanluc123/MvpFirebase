package com.maituanluc.basic1.data;


import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.maituanluc.basic1.data.authen.AppFirebaseLogin;
import com.maituanluc.basic1.data.authen.FirebaseLogin;
import com.maituanluc.basic1.data.preft.AppSharedPreftHelper;

public class AppDataManager implements DataManager, FirebaseLogin {
    Context context;
    AppSharedPreftHelper appSharedPreftHelper;
    AppFirebaseLogin appFirebaseLogin;

    public AppDataManager(Context context) {
        this.context = context;
        appSharedPreftHelper = new AppSharedPreftHelper(context);
        appFirebaseLogin = new AppFirebaseLogin();
    }

    @Override
    public void clear() {
        appSharedPreftHelper.clear();
    }

    @Override
    public void putEmail(String email) {
        appSharedPreftHelper.putEmail(email);
    }

    @Override
    public String getEmail() {
        return appSharedPreftHelper.getEmail();
    }

    @Override
    public boolean getLoggedInMode() {
        return appSharedPreftHelper.getLoggedInMode();
    }

    @Override
    public void setLoggedInMode(boolean loggedIn) {
        appSharedPreftHelper.setLoggedInMode(loggedIn);
    }

    @Override
    public FirebaseUser getCurrentUser1() {
        return appFirebaseLogin.getCurrentUser1();
    }
}
