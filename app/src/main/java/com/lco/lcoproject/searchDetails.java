package com.lco.lcoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class searchDetails extends AppCompatActivity {
    TextView search_name,search_email,search_phone,search_city,search_addr;
    ImageView search_call,search_message,search_gmail,search_google;
    String ph = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);
        search_name=findViewById(R.id.search_name);
        search_email=findViewById(R.id.search_email);
        search_phone=findViewById(R.id.search_phone);
        search_city=findViewById(R.id.search_city);
        search_addr=findViewById(R.id.search_addr);
        search_call=findViewById(R.id.search_call);
        search_gmail=findViewById(R.id.search_gmail);
        search_message=findViewById(R.id.search_message);
        search_google=findViewById(R.id.search_googlemap);
        final Intent i = getIntent();
        String name = i.getStringExtra("name");
        String email = i.getStringExtra("email");
        final String phone = i.getStringExtra("phoneno");
        String city = i.getStringExtra("city");
        String addr = i.getStringExtra("address");
        search_email.setText(email);
        search_name.setText(name);
        search_phone.setText(phone);
        search_city.setText(city);
        search_addr.setText(addr);
        search_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search_phone.getText().length() > 9) {
                    ph = search_phone.getText().toString().trim();
                    opencall(ph);
                }
            }
        });
        search_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (search_phone.getText().length() > 9) {
                    String num = search_phone.getText().toString().trim();
                    openchat(num);
                }
            }
        });
        search_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendemail();
            }
        });

    }

    private void opencall(String tel) {
        Intent call = new Intent(Intent.ACTION_CALL);
        call.setData(Uri.parse("tel:" + tel));
        if (ActivityCompat.checkSelfPermission(searchDetails.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
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

            String Messagegmail = search_email.getText().toString();
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{EmailId});
//            if (URI != null) {
//                emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
//            }
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Messagegmail);
            this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
            Toast.makeText(searchDetails.this, "Sending email...", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {

        }

    }
}
