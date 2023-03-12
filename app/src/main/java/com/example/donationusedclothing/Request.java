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

public class Request extends AppCompatActivity {

    FloatingActionButton addRequest;
    AdapterRequest adapterRequest;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelRequest> listRequest;
    RecyclerView tv_request;
    ImageView btn_backRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        btn_backRequest = findViewById(R.id.btn_backRequest);
        btn_backRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    finish();
            }
        });

        addRequest = findViewById(R.id.btn_addRequest);
        addRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Request.this,addRequest.class));
            }
        });

        tv_request = findViewById(R.id.tv_request);
        RecyclerView.LayoutManager RLayout = new LinearLayoutManager(this);
        tv_request.setLayoutManager(RLayout);
        tv_request.setItemAnimator(new DefaultItemAnimator());

        requestData();

    }

    private void requestData() {
        database.child("Request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listRequest = new ArrayList<>();
                for(DataSnapshot item : snapshot.getChildren()){
                    ModelRequest mrs = item.getValue(ModelRequest.class);
                    mrs.setKey(item.getKey());
                    listRequest.add(mrs);
                }
                adapterRequest = new AdapterRequest(listRequest, Request.this);
                tv_request.setAdapter(adapterRequest);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}