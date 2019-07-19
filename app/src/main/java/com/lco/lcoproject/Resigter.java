package com.lco.lcoproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.lco.lcoproject.Model.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Resigter extends AppCompatActivity {
    StorageReference postrefrance;
    Uri uri;
    CircleImageView profile_image;
    private static final String TAG = "RegisterActivity";
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    private FirebaseAuth.AuthStateListener mAuthListener;


    private EditText mEmail, mName,mPassword, mConfirmPassword,mphone,maddress,mcity,motheruser;
    private Button mRegister;
    private ProgressBar mProgressBar;


    private Context mContext;
    private String email, name, password,phone,address,city,other;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resigter);
        profile_image=findViewById(R.id.imageView);
       // upload_image=findViewById(R.id.upload_image);
        mRegister = findViewById(R.id.btn_register);
        mEmail = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        mConfirmPassword = findViewById(R.id.input_confirm_password);
        mName = findViewById(R.id.input_name);
        maddress = findViewById(R.id.input_addr);
        mcity = findViewById(R.id.input_city);
        mphone = findViewById(R.id.input_phone);
        motheruser = findViewById(R.id.input_choice);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        ///////////////////image
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
            }
        });


        mContext = Resigter.this;
        mUser = new User();


        initProgressBar();
        setupFirebaseAuth();
        init();

        Intent intent = getIntent();
        String str = intent.getStringExtra("key");
        String str1 = intent.getStringExtra("key");
        String str2 = intent.getStringExtra("key");
        String str3 = intent.getStringExtra("key");
        String str4 = intent.getStringExtra("key");
        String str5 = intent.getStringExtra("key");
        String str6 = intent.getStringExtra("key");
        String str7 = intent.getStringExtra("key");
        String str8 = intent.getStringExtra("key");
        String str9 = intent.getStringExtra("key");
        motheruser.setText(str9);
        motheruser.setText(str8);
        motheruser.setText(str7);
        motheruser.setText(str6);
        motheruser.setText(str5);
        motheruser.setText(str4);
        motheruser.setText(str3);
        motheruser.setText(str2);
        motheruser.setText(str1);
        motheruser.setText(str);

    }

    void pickImage()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(Intent.createChooser(intent,"select image"),1002);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1002){
            try {
                uri=data.getData();
                Bitmap bm= MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                profile_image.setImageBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();
            }
        }
        //the end  onActivityResult
    }
    private void init(){


        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = mEmail.getText().toString();
                name = mName.getText().toString();
                password = mPassword.getText().toString();
                phone=mphone.getText().toString();
                address=maddress.getText().toString();
                city=mcity.getText().toString();
                other=motheruser.getText().toString();


                if (checkInputs(email, name, password, mConfirmPassword.getText().toString())) {
                    if(doStringsMatch(password, mConfirmPassword.getText().toString())){
                        registerNewEmail(email, password);
                    }else{
                        Toast.makeText(mContext, "passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(mContext, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private boolean doStringsMatch(String s1, String s2){
        return s1.equals(s2);
    }



    private boolean checkInputs(String email, String username, String password, String confirmPassword){
       // Log.d(TAG, "checkInputs: checking inputs for null values");
        if(email.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("")){
            Toast.makeText(mContext, "All fields must be filled out", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

    private void initProgressBar(){
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
    }


//    private void hideSoftKeyboard(){
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//    }

      /*
    ---------------------------Firebase-----------------------------------------
     */


    private void setupFirebaseAuth(){

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is authenticated
                    //Log.d(TAG, "onAuthStateChanged: signed_in: " + user.getUid());


                } else {
                    // User is signed out
                   // Log.d(TAG, "onAuthStateChanged: signed_out");

                }
                // ...
            }
        };

    }


    public void registerNewEmail(final String email, String password){

        showProgressBar();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       // Log.d(TAG, "registerNewEmail: onComplete: " + task.isSuccessful());

                        if (task.isSuccessful()){
                            //send email verificaiton
                            sendVerificationEmail();

                            //add user details to firebase database
                            addNewUser();
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(mContext, "Someone with that email already exists",
                                    Toast.LENGTH_SHORT).show();
                            hideProgressBar();

                        }
                        hideProgressBar();
                        // ...
                    }
                });
    }

    /**
     * Adds data to the node: "users"
     */
    public void addNewUser(){

        //add data to the "users" node
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

       // Log.d(TAG, "addNewUser: Adding new User: \n user_id:" + userid);
        mUser.setName(name);
        mUser.setUser_id(userid);
        mUser.setAddr(address);
        mUser.setCity(city);
        mUser.setEmail(email);
        mUser.setPhone(phone);
        mUser.setOther(other);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        //insert into users node
        reference.child(getString(R.string.node_users))
                .child(userid)
                .setValue(mUser);

        FirebaseAuth.getInstance().signOut();
        redirectLoginScreen();
    }

    /**
     * sends an email verification link to the user
     */
    public void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                            }
                            else{
                                Toast.makeText(mContext, "couldn't send email", Toast.LENGTH_SHORT).show();
                                hideProgressBar();
                            }
                        }
                    });
        }

    }

    /**
     * Redirects the user to the login screen
     */
    private void redirectLoginScreen(){
       // Log.d(TAG, "redirectLoginScreen: redirecting to login screen.");

        Intent intent = new Intent(Resigter.this, MainActivity.class);
        startActivity(intent);
        finish();
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


