package com.maituanluc.basic1.ui.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.maituanluc.basic1.MvpApp;
import com.maituanluc.basic1.R;
import com.maituanluc.basic1.data.AppDataManager;
import com.maituanluc.basic1.ui.main.MainActivity;

import java.util.List;

public class UserActivity extends AppCompatActivity implements UserMvpView {
    RecyclerView rvUser;
    Button btnNeckMain;
    UserPresenter userPresenter;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;

    EditText etEmail1, etPass1;
    Button btnLogin1;

    TextView mStatusTextView, mDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
        AppDataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        userPresenter = new UserPresenter(dataManager);
        userPresenter.onAttach(this);
//        btnNeckMain.setOnClickListener(view -> userPresenter.backMainActivity());

        mAuth = FirebaseAuth.getInstance();
        btnLogin1.setOnClickListener(v -> {
            String email = etEmail1.getText().toString().trim();
            String pass = etPass1.getText().toString().trim();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Log.d("AAA", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w("AAA", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(UserActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            mStatusTextView.setText("chua biết");
            mDetailTextView.setText("deo biết");
            if (user.isEmailVerified()) {
                Toast.makeText(this, "Con cac", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ngu", Toast.LENGTH_SHORT).show();
            }
        } else {
            mStatusTextView.setText(null);
            mDetailTextView.setText(null);
        }

    }

    private void init() {
//        rvUser = findViewById(R.id.rvUser);
//        btnNeckMain = findViewById(R.id.btnNeckMain);

        etEmail1 = findViewById(R.id.etEmail1);
        etPass1 = findViewById(R.id.etPass1);
        btnLogin1 = findViewById(R.id.btnLogin1);
        mStatusTextView = findViewById(R.id.mStatusTextView);
        mDetailTextView = findViewById(R.id.mDetailTextView);
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(UserActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void listUser(List list) {

    }
}
