package com.faat.thinkdebugtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
    Fragment homefragment= new HomeFragment();
    Fragment categoryfragment= new CategoryFragment();
    Fragment cartfragment= new CartFragment();
    Fragment profilefragment= new ProfileFragment();
    FragmentManager fm = getSupportFragmentManager();
    Fragment active = homefragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm.beginTransaction().add(R.id.fragment_container, profilefragment, "4").hide(profilefragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, cartfragment, "3").hide(cartfragment).commit();
        fm.beginTransaction().add(R.id.fragment_container, categoryfragment, "2").hide(categoryfragment).commit();
        fm.beginTransaction().add(R.id.fragment_container,homefragment, "1").commit();
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
                    fm.beginTransaction().hide(active).show(homefragment).commit();
                    active = homefragment;
                } else if (tab1.getId() == R.id.tabcategory) {
                    tittle.setText("Category");
                    fm.beginTransaction().hide(active).show(categoryfragment).commit();
                    active = categoryfragment;

                } else if (tab1.getId() == R.id.tabcart) {
                    tittle.setText("Cart");
                    fm.beginTransaction().hide(active).show(cartfragment).commit();
                    active = cartfragment;

                } else if (tab1.getId() == R.id.tabprofile) {
                    tittle.setText("Profile");
                    fm.beginTransaction().hide(active).show(profilefragment).commit();
                    active = profilefragment;

                }
            }

            @Override
            public void onTabReselected(int i, AnimatedBottomBar.Tab tab) {

            }
        });
    }
}