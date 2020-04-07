package com.maituanluc.basic1.ui.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.maituanluc.basic1.MvpApp;
import com.maituanluc.basic1.R;
import com.maituanluc.basic1.data.AppDataManager;
import com.maituanluc.basic1.ui.login.LoginActivity;
import com.maituanluc.basic1.ui.main.MainActivity;
import com.maituanluc.basic1.ui.user.UserActivity;

public class RegisterUserActivity extends AppCompatActivity implements RegisterMvpView {
    EditText etRigisterEmail, etRigisterPassword;
    Button btnRigisterUser;
    RegisterPresenter registerPresenter;
    FirebaseAuth mAth;
    String email;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        init();
        AppDataManager appDataManager = new AppDataManager(this);
        registerPresenter = new RegisterPresenter(appDataManager);
        registerPresenter.onAttach(this);
        btnRigisterUser.setOnClickListener(v -> {
            registerPresenter.startRegister(etRigisterEmail.getText().toString(), etRigisterPassword.getText().toString());
        });
    }

    private void init() {
        etRigisterEmail = findViewById(R.id.etRigisterEmail);
        etRigisterPassword = findViewById(R.id.etRigisterPassword);
        btnRigisterUser = findViewById(R.id.btnRigisterUser);
    }

    @Override
    public void createAccount(String email, String password) {
        mAth = FirebaseAuth.getInstance();
//        FirebaseAuth currentUser = ;
//        updateUI(currentUser);
        if (!getValidateForm()) {
            return;
        }
        mAth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("AAA", "createUserWithEmail:success");
                        FirebaseUser user = mAth.getCurrentUser();
                    } else {
                        Log.w("AAA", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(RegisterUserActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
//                        updateUI(null);
                    }
                });
    }

    @Override
    public void openLoginActivity() {
        Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
        intent.putExtra("Email", email);
        setResult(12, intent);
        finish();
    }

    @Override
    public boolean getValidateForm() {
        boolean registered = true;
        email = etRigisterEmail.getText().toString();
        pass = etRigisterPassword.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
            registered = false;
            Toast.makeText(this, "Enter email or password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Register successfully", Toast.LENGTH_SHORT).show();
            registerPresenter.onRegisterButtonClick();
        }
        return registered;
    }
}
