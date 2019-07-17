package com.lco.lcoproject.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lco.lcoproject.R;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class home_fragment extends Fragment {
    private List<String> lastSearches;
    private MaterialSearchBar searchBar;


    public home_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragment, container, false);
        
    }

}
