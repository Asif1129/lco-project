package com.lco.lcoproject.Fragments;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lco.lcoproject.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.widget.TextView;

public class Home_Bottom_navbar extends AppCompatActivity {
    private TextView mTextMessage;
    private boolean loadfrag(Fragment fragment){
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                        fragment=new home_fragment();
                   break;
                case R.id.navigation_dashboard:
                    fragment=new Search_fragments();
                    break;
                case R.id.navigation_notifications:
                    fragment=new Profile_fragments();
                    break;
            }
            return loadfrag(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__bottom_navbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadfrag(new home_fragment());

    }


}
