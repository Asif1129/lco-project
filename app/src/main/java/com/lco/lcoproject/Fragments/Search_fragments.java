package com.lco.lcoproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lco.lcoproject.Adapter.SearchAdapter;
import com.lco.lcoproject.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search_fragments extends Fragment {
    EditText search_edit_text;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ArrayList<String> fullNameList;
    ArrayList<String> userNameList;
    ArrayList<String> profilePicList;
    SearchAdapter searchAdapter;


    public Search_fragments() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_fragments, container, false);

        search_edit_text = view.findViewById(R.id.search_edit_text);
        recyclerView = view.findViewById(R.id.recyclerView);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        /*
         * Create a array list for each node you want to use
         * */
        fullNameList = new ArrayList<>();
        userNameList = new ArrayList<>();
        profilePicList = new ArrayList<>();

        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    setAdapter(s.toString());
                } else {
                    /*
                     * Clear the list when editText is empty
                     * */
                    fullNameList.clear();
                    userNameList.clear();
                    profilePicList.clear();
                    recyclerView.removeAllViews();
                }
            }
        });
        return view;

    }

    private void setAdapter(final String searchedString) {
        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                 * Clear the list for every new search
                 * */
                fullNameList.clear();
                userNameList.clear();
                profilePicList.clear();
                recyclerView.removeAllViews();

                int counter = 0;

                /*
                 * Search all users for matching searched string
                 * */
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String uid = snapshot.getKey();
                    String full_name = snapshot.child("name").getValue(String.class);
                    String user_phone= snapshot.child("phone").getValue(String.class);
                    String profile_pic=snapshot.child("Image").getValue(String.class);


                    if (full_name.toLowerCase().contains(searchedString.toLowerCase())) {
                        fullNameList.add(full_name);
                        userNameList.add(user_phone);
                       profilePicList.add(profile_pic);
                        counter++;
                    } else if (user_phone.toLowerCase().contains(searchedString.toLowerCase())) {
                        fullNameList.add(full_name);
                        userNameList.add(user_phone);
                       profilePicList.add(profile_pic);
                        counter++;
                    }

                    /*
                     * Get maximum of 15 searched results only
                     * */
                    if (counter == 15)
                        break;
                }

                searchAdapter = new SearchAdapter(getContext(), fullNameList, userNameList,profilePicList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
