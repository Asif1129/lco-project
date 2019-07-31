package com.lco.lcoproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Choice_new extends AppCompatActivity {
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10;
    TextView otheruser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_new);
        imageView1=findViewById(R.id.doctor1);
        imageView2=findViewById(R.id.computer);
        imageView3=findViewById(R.id.teacher);
        imageView4=findViewById(R.id.mechanic);
        imageView5=findViewById(R.id.photographer);
        imageView6=findViewById(R.id.carpenter);
        imageView7=findViewById(R.id.construct);
        imageView8=findViewById(R.id.driver);
        imageView9=findViewById(R.id.electric);
        imageView10=findViewById(R.id.plumber);
        otheruser=findViewById(R.id.other);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctor="I m a doctor";
                Intent intent = new Intent(Choice_new.this,Resigter.class);
                intent.putExtra("key",doctor);
                startActivity(intent);

            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String computer="computer";
                Intent intent = new Intent(Choice_new.this, Resigter.class);
                intent.putExtra("key",computer);
                startActivity(intent);

            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String teacher="teacher";
                Intent intent=new Intent(Choice_new.this,Resigter.class);
                intent.putExtra("key",teacher);
                startActivity(intent);

            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mechanic="mechanic";
                Intent intent=new Intent(Choice_new.this,Resigter.class);
                intent.putExtra("key",mechanic);
                startActivity(intent);

            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String photographer="photographer";
                Intent intent=new Intent(Choice_new.this,Resigter.class);
                intent.putExtra("key",photographer);
                startActivity(intent);
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Carpenter="Carpenter";
                Intent intent=new Intent(Choice_new.this,Resigter.class);
                intent.putExtra("key",Carpenter);
                startActivity(intent);
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String construct="construct";
                Intent intent=new Intent(Choice_new.this,Resigter.class);
                intent.putExtra("key",construct);
                startActivity(intent);
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String driver="driver";
                Intent intent=new Intent(Choice_new.this,Resigter.class);
                intent.putExtra("key",driver);
                startActivity(intent);
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Doctor="Doctor";
                Intent intent=new Intent(Choice_new.this,Resigter.class);
                intent.putExtra("key",Doctor);
                startActivity(intent);
            }
        });
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Carpenter="Carpenter";
                Intent intent=new Intent(Choice_new.this,Resigter.class);
                intent.putExtra("key",Carpenter);
                startActivity(intent);
            }
        });
        otheruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String other="Other";
                Intent intent=new Intent(Choice_new.this,Resigter.class);
                intent.putExtra("key",other);
                startActivity(intent);
            }
        });

    }
}
