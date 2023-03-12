package com.example.donationusedclothing;

import androidx.appcompat.app.AppCompatActivity;
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
import java.util.List;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

public class ManageDonorActivity extends AppCompatActivity {

    FloatingActionButton addDonor;
    AdapterAdDonor adapterAdDonor;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    ArrayList<ModelAdminDonor> listDonor;
    RecyclerView recyclerview_adDonor;

    SearchView searchViewDonor;
    ImageView btn_backDonor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_donor);

        //search
        /*searchViewDonor = findViewById(R.id.searchdonor);
        searchViewDonor.clearFocus();
        searchViewDonor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchListDonor(newText);
                return false;
            }
        });*/

        btn_backDonor = findViewById(R.id.btn_backDonor);
        btn_backDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addDonor = findViewById(R.id.btn_addDonor);
        addDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageDonorActivity.this, ManageAddDonor.class));

            }
        });
        recyclerview_adDonor = findViewById(R.id.recyclerview_adDonor);
        RecyclerView.LayoutManager rLayout = new LinearLayoutManager(this);
        recyclerview_adDonor.setLayoutManager(rLayout);
        recyclerview_adDonor.setItemAnimator(new DefaultItemAnimator());

        recordData();

    }
    //search
    /*private void SearchListDonor(String text) {
        ArrayList <ModelAdminDonor> searchListDonor = new ArrayList<>();
        for (ModelAdminDonor modelAdminDonor: listDonor){
            if (modelAdminDonor.getDonorname().toLowerCase().contains(text.toLowerCase())){
                searchListDonor.add(modelAdminDonor);
            }
        }
        if (searchListDonor.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapterAdDonor.searchDataDonor(searchListDonor);
        }

    }*/

    private void recordData() {
        db.child("Donorlist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotD) {
                listDonor = new ArrayList<>();
                for(DataSnapshot itemD : snapshotD.getChildren()){
                    ModelAdminDonor mad = itemD.getValue(ModelAdminDonor.class);
                    mad.setDkey(itemD.getKey());
                    listDonor.add(mad);
                }
                adapterAdDonor = new AdapterAdDonor(listDonor, ManageDonorActivity.this);
                recyclerview_adDonor.setAdapter(adapterAdDonor);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

