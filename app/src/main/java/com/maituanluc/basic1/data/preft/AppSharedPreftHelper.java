package com.maituanluc.basic1.data.preft;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class AppSharedPreftHelper implements SharedPreftHelper {
    SharedPreferences sharedPreferences;
    public static final String MY_PREFS="MY_PREFS";
    public AppSharedPreftHelper(Context context) {
        sharedPreferences=context.getSharedPreferences(MY_PREFS,MODE_PRIVATE);
    }

    public static final String EMAIL = "EMAIL";

    @Override
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    @Override
    public void putEmail(String email) {
        sharedPreferences.edit().putString(EMAIL,email).apply();
    }

    @Override
    public String getEmail() {
        return sharedPreferences.getString(EMAIL,null);
    }

    @Override
    public boolean getLoggedInMode() {
        return sharedPreferences.getBoolean("IS_LOGGED_IN",false);
    }

    @Override
    public void setLoggedInMode(boolean loggedIn) {
        sharedPreferences.edit().putBoolean("IS_LOGGED_IN",loggedIn).apply();
    }
}
