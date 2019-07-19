package com.lco.lcoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewDetailsUser extends AppCompatActivity {
    TextView view_email, view_name, view_phone, view_addr, view_city, view_profession;
    ImageView call, message, google, gmail;
    String ph = "";

    //String phone,email,addr,city,other,name;
    // DatabaseReference databaseReference;
    // FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details_user);
        view_email = findViewById(R.id.view_email);
        view_addr = findViewById(R.id.view_addr);
        view_city = findViewById(R.id.view_city);
        view_phone = findViewById(R.id.view_phone);
        view_name = findViewById(R.id.view_name);
        view_profession = findViewById(R.id.view_choice);
        call = findViewById(R.id.call);
        message = findViewById(R.id.message);
        gmail = findViewById(R.id.gmail);
        google = findViewById(R.id.googlemap);
        final Intent i = getIntent();
        String name = i.getStringExtra("name");
        String email = i.getStringExtra("email");
        final String phone = i.getStringExtra("phoneno");
        String city = i.getStringExtra("city");
        String addr = i.getStringExtra("address");
        view_email.setText(email);
        view_name.setText(name);
        view_phone.setText(phone);
        view_city.setText(city);
        view_addr.setText(addr);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view_phone.getText().length() > 9) {
                    ph = view_phone.getText().toString().trim();
                    opencall(ph);
                }
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view_phone.getText().length() > 9) {
                    String num = view_phone.getText().toString().trim();
                    openchat(num);
                }
            }
        });
        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendemail();
            }
        });

    }

    private void opencall(String tel) {
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:" + tel));
        if (ActivityCompat.checkSelfPermission(ViewDetailsUser.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 11);
        }
        startActivity(call);
    }

    private void openchat(String s) {
        Uri uri = Uri.parse("smsto:" + s);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.putExtra(Intent.EXTRA_TEXT, "My name is Khan...");
        startActivity(i);
    }

    public void sendemail() {
        try {
            Intent it = getIntent();
            String EmailId = it.getStringExtra("Email");

            String Messagegmail = view_email.getText().toString();
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{EmailId});
//            if (URI != null) {
//                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
//            }
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Messagegmail);
            this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
            Toast.makeText(ViewDetailsUser.this, "Sending email...", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {

        }

    }
}
