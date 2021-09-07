package com.faat.thinkdebugtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactDetailsActivity extends AppCompatActivity {
    ImageView back;
    TextView name,email,address,gender,mobile,home,office,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        initView();
    }

    private void initView() {
        back=findViewById(R.id.back);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);
        gender=findViewById(R.id.gender);
        mobile=findViewById(R.id.mobile);
        home=findViewById(R.id.home);
        id=findViewById(R.id.id);
        office=findViewById(R.id.office);
        try {
            Intent intent=getIntent();
            id.setText("id- "+intent.getStringExtra("id"));
            name.setText(intent.getStringExtra("name"));
            email.setText(intent.getStringExtra("email"));
            address.setText(intent.getStringExtra("address"));
            gender.setText(intent.getStringExtra("gender"));
            mobile.setText(intent.getStringExtra("mobile"));
            home.setText(intent.getStringExtra("home"));
            office.setText(intent.getStringExtra("office"));

        }catch (Exception e){

        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}