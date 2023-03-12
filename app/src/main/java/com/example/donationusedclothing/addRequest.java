package com.example.donationusedclothing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addRequest extends AppCompatActivity {

    EditText etItemRequested, etRequestName, etRequestQuantity, etRequestPhone;
    Button btnReqAdd;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ImageView btn_backRequestDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        btn_backRequestDetails = findViewById(R.id.btn_backRequestDetails);
        btn_backRequestDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        etItemRequested = findViewById(R.id.etItemRequested);
        etRequestName = findViewById(R.id.etRequestName);
        etRequestQuantity = findViewById(R.id.etRequestQuantity);
        etRequestPhone = findViewById(R.id.etRequestPhone);
        btnReqAdd = findViewById(R.id.btnReqAdd);
        btnReqAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getItemRequested = etItemRequested.getText().toString();
                String getRequestName = etRequestName.getText().toString();
                String getRequestQuantity = etRequestQuantity.getText().toString();
                String getRequestPhone = etRequestPhone.getText().toString();

                if (getItemRequested.isEmpty()){
                    etItemRequested.setError("Please enter requested item");
                }else if(getRequestName.isEmpty()) {
                    etRequestName.setError("Please enter request username");
                }else if(getRequestQuantity.isEmpty()) {
                    etRequestQuantity.setError("Please enter quantity");
                }else if(getRequestPhone.isEmpty()) {
                    etRequestName.setError("Please enter contact number");
                }else{
                    database.child("Request").push().setValue(new ModelRequest(getItemRequested,getRequestName, getRequestQuantity, getRequestPhone)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(addRequest.this,"Successfully added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(addRequest.this,Request.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(addRequest.this,"Unable to insert", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }
}