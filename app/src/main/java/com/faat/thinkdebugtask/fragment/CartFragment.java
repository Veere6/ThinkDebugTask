package com.faat.thinkdebugtask.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faat.thinkdebugtask.MainActivity;
import com.faat.thinkdebugtask.R;

public class CartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_cart, container, false);
        MainActivity.tittle.setText("Cart");
        MainActivity.bottomBar.selectTabAt(2, true);
        return view;
    }
}