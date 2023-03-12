package com.example.donationusedclothing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Donation extends AppCompatActivity {

    FloatingActionButton addDonation;
    AdapterDonation adapterDonation;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelDonation> listDonation;
    RecyclerView tv_donation;
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);


        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addDonation = findViewById(R.id.btn_addDonation);
        addDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Donation.this,addDonation.class));

            }
        });

        tv_donation = findViewById(R.id.tv_donation);
        RecyclerView.LayoutManager Layout = new LinearLayoutManager(this);
        tv_donation.setLayoutManager(Layout);
        tv_donation.setItemAnimator(new DefaultItemAnimator());

        donationData();

    }

    private void donationData() {
        database.child("Donation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDonation = new ArrayList<>();
                for(DataSnapshot item : snapshot.getChildren()){
                    ModelDonation mds = item.getValue(ModelDonation.class);
                    mds.setKey(item.getKey());
                    listDonation.add(mds);
                }
                adapterDonation = new AdapterDonation(listDonation,Donation.this);
                tv_donation.setAdapter(adapterDonation);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}