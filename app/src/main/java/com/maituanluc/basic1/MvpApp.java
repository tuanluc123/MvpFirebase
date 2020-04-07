package com.maituanluc.basic1;

import android.app.Application;

import com.maituanluc.basic1.data.AppDataManager;

public class MvpApp extends Application {
    AppDataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        dataManager=new AppDataManager(getApplicationContext());

    }
    public AppDataManager getDataManager(){
        return dataManager;
    }
}
