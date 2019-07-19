package com.lco.lcoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lco.lcoproject.Fragments.Home_Bottom_navbar;

public class LoginSignup extends AppCompatActivity {
    Button login,signup;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);
       // setupFirebaseAuth();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSignup.this,MainActivity.class));

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginSignup.this,choice.class));
            }
        });
    }

//    private void setupFirebaseAuth(){
//        Log.d(TAG, "setupFirebaseAuth: started");
//
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//
//                    //check if email is verified
//                    if(user.isEmailVerified()){
//                        Toast.makeText(LoginSignup.this, "Authenticated with: " + user.getEmail(), Toast.LENGTH_SHORT).show();
//
//                        Intent intent = new Intent(LoginSignup.this, Home_Bottom_navbar.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                        finish();
//
//                    }else{
//                        Toast.makeText(LoginSignup.this, "Email is not Verified\nCheck your Inbox", Toast.LENGTH_SHORT).show();
//                        FirebaseAuth.getInstance().signOut();
//                    }
//
//                } else {
//                    // User is signed out
//                    Log.d(TAG, "onAuthStateChanged: signed_out");
//                }
//                // ...
//            }
//        };
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        System.exit(0);
//        finish();
//    }
    }

