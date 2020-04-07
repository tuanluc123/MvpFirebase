package com.maituanluc.basic1.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.maituanluc.basic1.MvpApp;
import com.maituanluc.basic1.R;
import com.maituanluc.basic1.adapter.UserAdapter;
import com.maituanluc.basic1.data.AppDataManager;
import com.maituanluc.basic1.data.model.User;
import com.maituanluc.basic1.ui.splash.SpalshActivity;
import com.maituanluc.basic1.ui.user.UserActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

public class MainActivity extends AppCompatActivity implements MainMvpView {
    Button btnLogOut, btnAddUser;
    RecyclerView rvShowUser;
    MainPresenter mainPresenter;
    List<User> ds = new ArrayList<>();
    FirebaseFirestore db;
    UserAdapter userAdapter;
    private CollectionReference collectionReference;
    private ListenerRegistration listenerRegistration;

    TextView txtTitleAdd;
    EditText etIdUser, etNameUser, etPhoneUser, etGenderUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMain();
        initPresenter();
        mainPresenter.onAttach(this);
        collectionReference = db.collection("User");
        mainPresenter.handlerListenerRegistration();
        btnLogOut.setOnClickListener(v -> {
            mainPresenter.setUserLoggedOut();
        });
        mainPresenter.showListUser();
        btnAddUser.setOnClickListener(v -> {
            mainPresenter.showDialogAddUser();
        });
    }

    @Override
    public void setUpRecycleview() {
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        rvShowUser.setAdapter(userAdapter);
        rvShowUser.setLayoutManager(manager);
    }

    @Override
    public void setupListenerRegistration() {
        listenerRegistration = collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w("TAG", "listen:error", e);
                    return;
                }

                User mUser;
                User nUser;
                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                    String document = dc.getDocument().getId();
                    switch (dc.getType()) {
                        case ADDED:
                            nUser = dc.getDocument().toObject(User.class);
                            mUser = new User(nUser.getId(), nUser.getName(), nUser.getPhone(), nUser.getGender(), dc.getDocument().getId());
                            ds.add(mUser);
                            userAdapter.notifyDataSetChanged();
                            break;
                        case MODIFIED:
                            nUser = dc.getDocument().toObject(User.class);
                            int index;
                            for (int i = 0; i < ds.size(); i++) {
                                if (ds.get(i).getDocument().equals(document)) {
                                    index = i;
                                    ds.get(index).setName(nUser.getName());
                                    ds.get(index).setId(nUser.getId());
                                    ds.get(index).setGender(nUser.getGender());
                                    ds.get(index).setPhone(nUser.getPhone());
                                    userAdapter.notifyItemChanged(index);
                                    return;
                                }
                            }
                            break;
                        case REMOVED:
                            for (int i = 0; i < ds.size(); i++) {
                                if (ds.get(i).getDocument().equals(document)) {
                                    ds.remove(i);
                                    userAdapter.notifyItemRemoved(i);
                                }
                            }
                            break;
                    }
                }
            }
        });
    }

    private void initPresenter() {
        AppDataManager dataManager = ((MvpApp) getApplication()).getDataManager();
        mainPresenter = new MainPresenter(dataManager);
        db = FirebaseFirestore.getInstance();
    }

    private void initMain() {
        btnLogOut = findViewById(R.id.btnLogOut);
        rvShowUser = findViewById(R.id.rvShowUser);
        btnAddUser = findViewById(R.id.btnAddUser);
    }

    @Override
    public void openSplashActivity() {
        Intent intent = new Intent(MainActivity.this, SpalshActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void openUserActivity() {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        startActivity(intent);
    }

    @Override
    public void openRecycleView() {
        userAdapter = new UserAdapter(ds, MainActivity.this, new UserAdapter.HandlerData() {
            @Override
            public void updateItem(int position) {
                AlertDialog.Builder abUppdate = new AlertDialog.Builder(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_add_user, null);
                abUppdate.setView(view);
                txtTitleAdd = view.findViewById(R.id.txtTitleAdd);
                etIdUser = view.findViewById(R.id.etIdUser);
                etNameUser = view.findViewById(R.id.etNameUser);
                etPhoneUser = view.findViewById(R.id.etPhoneUser);
                etGenderUser = view.findViewById(R.id.etGenderUser);
                txtTitleAdd.setText("Update user");
                User user = ds.get(position);
                etIdUser.setText(user.getId());
                etNameUser.setText(user.getName());
                etPhoneUser.setText(user.getPhone());
                etGenderUser.setText(user.getGender());
                abUppdate.setPositiveButton("Add", (dialogInterface, i) -> {
                    String id1 = etIdUser.getText().toString().trim();
                    String name = etNameUser.getText().toString().trim();
                    String phone = etPhoneUser.getText().toString().trim();
                    String gender = etGenderUser.getText().toString().trim();
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("Id", id1);
                    userMap.put("Name", name);
                    userMap.put("Phone", phone);
                    userMap.put("Gender", gender);

                    collectionReference.document(user.getDocument())
                            .update(userMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    userAdapter.notifyDataSetChanged();
                                    Log.d("AAA", ds.toString());
                                    Toast.makeText(MainActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "Update error", Toast.LENGTH_SHORT).show();
                                }
                            });
                });
                abUppdate.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        abUppdate.setCancelable(true);
                    }
                });
                abUppdate.show();
                Log.d("BBB", ds.toString());
//                listenerRegistration.remove();
            }

            @Override
            public void removeItem(int position) {
                collectionReference.document(ds.get(position).getDocument())
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully deleted!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error deleting document", e);
                            }
                        });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listenerRegistration.remove();
    }

    @Override
    public void openDialogAddUser() {
        AlertDialog.Builder abUser = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_user, null);
        abUser.setView(view);
        txtTitleAdd = view.findViewById(R.id.txtTitleAdd);
        etIdUser = view.findViewById(R.id.etIdUser);
        etNameUser = view.findViewById(R.id.etNameUser);
        etPhoneUser = view.findViewById(R.id.etPhoneUser);
        etGenderUser = view.findViewById(R.id.etGenderUser);
        txtTitleAdd.setText("Add user");
        abUser.setPositiveButton("Add", (dialogInterface, i) -> {
            String id = etIdUser.getText().toString().trim();
            String name = etNameUser.getText().toString().trim();
            String phone = etPhoneUser.getText().toString().trim();
            String gender = etGenderUser.getText().toString().trim();
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("Id", id);
            userMap.put("Name", name);
            userMap.put("Phone", phone);
            userMap.put("Gender", gender);
            db.collection("User")
                    .add(userMap)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(MainActivity.this, "Add successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Error adding", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
        abUser.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                abUser.setCancelable(true);
            }
        });
        abUser.show();
    }

}
