package com.lco.lcoproject.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.lco.lcoproject.LoginSignup;
import com.lco.lcoproject.MainActivity;
import com.lco.lcoproject.R;
import com.lco.lcoproject.Team;
import com.lco.lcoproject.ViewDetailProfile;
import com.lco.lcoproject.ViewDetailsUser;
import com.lco.lcoproject.resetPassword;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_fragments extends Fragment {
    TextView textView,share,team;
    RatingBar ratingBar;
    Button signout;
    WebView wb;



    public Profile_fragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_profile_fragments, container, false);
        textView=view.findViewById(R.id.editdetail);
        //ratingBar=view.findViewById(R.id.ratingBar1);
        signout=view.findViewById(R.id.signOut);
        share=view.findViewById(R.id.shareit);
        team=view.findViewById(R.id.team);

       // final TextView mRatingScale =  view.findViewById(R.id.tvRatingScale);

//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//
//            }
//        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
               // FirebaseAuth.getInstance().signOut();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               FirebaseAuth.getInstance().signOut();
                            getActivity().finish();
                                startActivity(new Intent(getContext(), LoginSignup.class));

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                //startActivity(new Intent(getContext(), LoginSignup.class));
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIt();
            }
        });
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//                mRatingScale.setText(String.valueOf(v));
//                switch ((int) ratingBar.getRating()) {
//                    case 1:
//                        mRatingScale.setText("Very bad");
//                        break;
//                    case 2:
//                        mRatingScale.setText("Need some improvement");
//                        break;
//                    case 3:
//                        mRatingScale.setText("Good");
//                        break;
//                    case 4:
//                        mRatingScale.setText("Great");
//                        break;
//                    case 5:
//                        mRatingScale.setText("Awesome. I love it");
//                        break;
//                    default:
//                        mRatingScale.setText("");
//                }
//            }
//        });
        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Team.class));
            }
        });
//        view.findViewById(R.id.ripple).setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                // handle me
//                MaterialRippleLayout.on(view)
//                        .rippleColor(Color.BLACK)
//                        .create();
//            }
//        });


        return view;
    }

    @Override
    public void onStart() {

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ViewDetailProfile.class));


            }
        });
        super.onStart();
    }
    public void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

    }
}
