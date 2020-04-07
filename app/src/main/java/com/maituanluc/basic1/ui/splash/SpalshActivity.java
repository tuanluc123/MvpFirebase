package com.maituanluc.basic1.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.maituanluc.basic1.MvpApp;
import com.maituanluc.basic1.R;
import com.maituanluc.basic1.data.AppDataManager;
import com.maituanluc.basic1.ui.login.LoginActivity;
import com.maituanluc.basic1.ui.main.MainActivity;


public class SpalshActivity extends AppCompatActivity implements SplashMvpView {
    SplashPresenter splashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppDataManager dataManager=((MvpApp)getApplication()).getDataManager();
        splashPresenter=new SplashPresenter(dataManager);
        splashPresenter.onAttach(this);
        splashPresenter.decideNextActivity();
    }

    @Override
    public void openMainActivity() {
        Intent intent=new Intent(SpalshActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void openLoginActivity() {
        Intent intent=new Intent(SpalshActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
