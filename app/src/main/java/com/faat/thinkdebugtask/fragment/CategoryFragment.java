package com.faat.thinkdebugtask.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faat.thinkdebugtask.MainActivity;
import com.faat.thinkdebugtask.R;

public class CategoryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category, container, false);
        MainActivity.tittle.setText("Category");
        MainActivity.bottomBar.selectTabAt(1, true);
        return view;
    }
}