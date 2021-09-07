package com.faat.thinkdebugtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.faat.thinkdebugtask.fragment.CartFragment;
import com.faat.thinkdebugtask.fragment.CategoryFragment;
import com.faat.thinkdebugtask.fragment.HomeFragment;
import com.faat.thinkdebugtask.fragment.ProfileFragment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {
    public static AnimatedBottomBar bottomBar;
    public static TextView tittle;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new HomeFragment();
        loadFragment(fragment);
        initView();
    }

    private void initView() {
        bottomBar = findViewById(R.id.bottom_bar);
        tittle=findViewById(R.id.tittle);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        bottomBar.selectTabAt(0, true);
        bottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, AnimatedBottomBar.Tab tab, int i1, AnimatedBottomBar.Tab tab1) {
                if (tab1.getId() == R.id.tabhome) {
                    tittle.setText("Home");
                    Fragment fragment = new HomeFragment();
                    loadFragment(fragment);
                } else if (tab1.getId() == R.id.tabcategory) {
                    tittle.setText("Category");
                    Fragment fragment = new CategoryFragment();
                    loadFragment(fragment);

                } else if (tab1.getId() == R.id.tabcart) {
                    tittle.setText("Cart");
                    Fragment fragment = new CartFragment();
                    loadFragment(fragment);

                } else if (tab1.getId() == R.id.tabprofile) {
                    tittle.setText("Profile");
                    Fragment fragment = new ProfileFragment();
                    loadFragment(fragment);

                }
            }

            @Override
            public void onTabReselected(int i, AnimatedBottomBar.Tab tab) {

            }
        });
    }


    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack("");
        transaction.commit();
    }
}