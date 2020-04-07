package com.maituanluc.basic1.data.authen;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AppFirebaseLogin implements FirebaseLogin {
    @Override
    public FirebaseUser getCurrentUser1() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }
}
