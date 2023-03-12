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

public class ManageRecipientActivity extends AppCompatActivity {

    FloatingActionButton addRecipient;
    AdapterAdRecipient adapterAdRecipient;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelAdminRecipient> listRecipient;
    RecyclerView recyclerview_adRecipient;
    ImageView btn_backRecipient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_recipient);


        btn_backRecipient = findViewById(R.id.btn_backRecipient);
        btn_backRecipient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addRecipient = findViewById(R.id.btn_addRecipient);
        addRecipient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageRecipientActivity.this, ManageAddRecipient.class));

            }
        });
        recyclerview_adRecipient = findViewById(R.id.recyclerview_adRecipient);
        RecyclerView.LayoutManager ReLayout = new LinearLayoutManager(this);
        recyclerview_adRecipient.setLayoutManager(ReLayout);
        recyclerview_adRecipient.setItemAnimator(new DefaultItemAnimator());

        recordData();

    }

    private void recordData() {
        db.child("Recipientlist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotR) {
                listRecipient = new ArrayList<>();
                for(DataSnapshot itemR : snapshotR.getChildren()){
                    ModelAdminRecipient mar = itemR.getValue(ModelAdminRecipient.class);
                    mar.setRkey(itemR.getKey());
                    listRecipient.add(mar);
                }
                adapterAdRecipient = new AdapterAdRecipient(listRecipient, ManageRecipientActivity.this);
                recyclerview_adRecipient.setAdapter(adapterAdRecipient);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}