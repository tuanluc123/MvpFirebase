package com.maituanluc.basic1.ui.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.maituanluc.basic1.MvpApp;
import com.maituanluc.basic1.R;
import com.maituanluc.basic1.data.AppDataManager;
import com.maituanluc.basic1.ui.main.MainActivity;
import com.maituanluc.basic1.ui.register.RegisterUserActivity;

public class LoginActivity extends AppCompatActivity implements LoginMvpView {
    LoginPresenter loginPresenter;
    EditText etEmail, etPass;
    Button btnLogin, btnRegisterUser;
    String emaiLogin, passLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initMain();
        initPresenter();
        mAuth = FirebaseAuth.getInstance();
        loginPresenter.onAttach(this);
        etEmail.setText(((MvpApp) getApplicationContext()).getDataManager().getCurrentUser1().getEmail());
        btnLogin.setOnClickListener(v -> {
            onLoginButtonclick();
        });

        btnRegisterUser.setOnClickListener(V -> {
            onRigisterButtonClick();
        });
    }

    private void initPresenter() {
        AppDataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        loginPresenter = new LoginPresenter(dataManager);
    }

    private void initMain() {
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterUser = findViewById(R.id.btnRegisterUser);
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginButtonclick() {
        if (!getValidateForm()) {
            return;
        }
        emaiLogin = etEmail.getText().toString();
        passLogin = etPass.getText().toString();
        mAuth.signInWithEmailAndPassword(emaiLogin, passLogin)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("BBB", "signInWithEmail:success");
                        loginPresenter.startLogin(emaiLogin);/////////////
                    } else {
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });

//        if (!CommonUltil.isEmailValid(emaiLogin)) {
//            Toast.makeText(this, "Email not in the right format", Toast.LENGTH_SHORT).show();
//            return;
//        } else if (passLogin.isEmpty() || passLogin == null) {
//            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
//            return;
//        } else {
//            loginPresenter.startLogin(emaiLogin);///////////
//        }
    }

    @Override
    public void openRegisterUserActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
        startActivityForResult(intent, 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12) {
            etEmail.setText(data.getExtras().getString("Email"));
        }
    }

    @Override
    public void onRigisterButtonClick() {
        loginPresenter.neckRegisterUserActivity();
    }

    public boolean getValidateForm() {
        boolean registered = true;
        emaiLogin = etEmail.getText().toString();
        passLogin = etPass.getText().toString();

        if (TextUtils.isEmpty(emaiLogin) || TextUtils.isEmpty(passLogin)) {
            registered = false;
            Toast.makeText(this, "Enter email or password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Register successfully", Toast.LENGTH_SHORT).show();

        }
        return registered;
    }
}
