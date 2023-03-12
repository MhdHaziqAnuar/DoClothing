package com.example.donationusedclothing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectUser extends AppCompatActivity {

    CardView btnuser, btnstaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        //selection
        btnuser = findViewById(R.id.btnUserSelect);
        btnstaff = findViewById(R.id.btnStaff);

        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectUser.this, ActivityLogin.class);
                startActivity(intent);
            }
        });

        btnstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectUser.this, AdminLogin.class);
                startActivity(intent);
            }
        });

    }
}