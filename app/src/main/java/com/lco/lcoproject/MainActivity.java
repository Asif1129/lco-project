package com.lco.lcoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lco.lcoproject.Fragments.Home_Bottom_navbar;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";


    private FirebaseAuth.AuthStateListener mAuthListener;

    private TextView mRegister,forgotpassword;
    private EditText mEmail, mPassword;
    private Button mLogin;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegister =  findViewById(R.id.link_register);
        mEmail =  findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        mLogin =  findViewById(R.id.btn_login);
        forgotpassword=findViewById(R.id.link_forgotpassword);
         forgotpassword.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, resetPassword.class);
                 startActivity(intent);
             }
         });



        //initImageLoader();
        initProgressBar();
        setupFirebaseAuth();
        init();


    }
    private void init(){
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the fields are filled out
                if(!isEmpty(mEmail.getText().toString())
                        && !isEmpty(mPassword.getText().toString())){
                  //  Log.d(TAG, "onClick: attempting to authenticate.");

                    showProgressBar();

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(mEmail.getText().toString(),
                            mPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    hideProgressBar();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toasty.error(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT, true).show();
                           // Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            hideProgressBar();
                        }
                    });
                }else{
                    Toasty.warning(MainActivity.this, "You didn't fill in all the fields.", Toast.LENGTH_SHORT, true).show();
                  //  Toast.makeText(MainActivity.this, "You didn't fill in all the fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this,Choice_new.class);
                startActivity(intent);
            }
        });



    }



    private boolean isEmpty(String string){
        return string.equals("");
    }


    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);

    }

    private void hideProgressBar(){
        if(mProgressBar.getVisibility() == View.VISIBLE){
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }



    private void initProgressBar(){
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
    }


    ////firebase

    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: started");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    //check if email is verified
                    if(user.isEmailVerified()){
                        Toasty.success(MainActivity.this, "Authenticated with: " + user.getEmail(), Toast.LENGTH_SHORT, true).show();
                       // Toast.makeText(MainActivity.this, "Authenticated with: " + user.getEmail(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MainActivity.this, Home_Bottom_navbar.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();

                    }else{
                        Toasty.info(MainActivity.this, "Email is not Verified\nCheck your Inbox", Toast.LENGTH_SHORT, true).show();
                        //Toast.makeText(MainActivity.this, "Email is not Verified\nCheck your Inbox", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged: signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}

