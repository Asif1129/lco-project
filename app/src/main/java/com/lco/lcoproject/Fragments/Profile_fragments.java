package com.lco.lcoproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lco.lcoproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_fragments extends Fragment {


    public Profile_fragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_fragments, container, false);
    }

}
