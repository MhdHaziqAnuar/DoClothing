package com.example.donationusedclothing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class HistoryDonation extends AppCompatActivity {

    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView3;
    List<ModelClothRecord> dataList;
    AdapterHistoryDonation adapter;
    SearchView search3;
    ImageView btn_backHistoryDonation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_donation);

        btn_backHistoryDonation = findViewById(R.id.btn_backHistoryDonation);
        btn_backHistoryDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView3 = findViewById(R.id.recyclerView3);
        search3 = findViewById(R.id.search3);
        search3.clearFocus();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(HistoryDonation.this, 1);
        recyclerView3.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryDonation.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        dataList = new ArrayList<>();
        adapter = new AdapterHistoryDonation(HistoryDonation.this, dataList);
        recyclerView3.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Cloth Records");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    ModelClothRecord modelClothRecord = itemSnapshot.getValue(ModelClothRecord.class);
                    modelClothRecord.setKey(itemSnapshot.getKey());
                    dataList.add(modelClothRecord);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
        search3.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

    }
    public void searchList(String text){
        ArrayList<ModelClothRecord> searchList = new ArrayList<>();
        for (ModelClothRecord modelClothRecord : dataList){
            if (modelClothRecord.getDataDonorname().toLowerCase().contains(text.toLowerCase())){
                searchList.add(modelClothRecord);
            }
        }
        adapter.searchDataList(searchList);
    }

}