package com.example.donationusedclothing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

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
import android.os.Bundle;

public class ManageAddDonor extends AppCompatActivity {
    EditText adDonorname, adDonorphonenum, adDonorcategory;
    Button btn_addDonorinfo;
    ImageView btn_backmanageDonor;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_add_donor);

        btn_backmanageDonor = findViewById(R.id.btn_backmanageDonor);
        btn_backmanageDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adDonorname = findViewById(R.id.adDonorname);
        adDonorphonenum = findViewById(R.id.adDonorphonenum);
        adDonorcategory = findViewById(R.id.adDonorcategory);

        btn_addDonorinfo = findViewById(R.id.btn_addDonorinfo);
        btn_addDonorinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getDonorname = adDonorname.getText().toString();
                String getDonorphonenum = adDonorphonenum.getText().toString();
                String getDonorcategory = adDonorcategory.getText().toString();

                if (getDonorname.isEmpty()){
                    adDonorname.setError("Please enter donor name");
                }else if(getDonorphonenum.isEmpty()){
                    adDonorphonenum.setError("Please enter donor's phone number");
                }else if(getDonorcategory.isEmpty()){
                    adDonorcategory.setError("Please enter donor's category");
                }
                else{
                    db.child("Donorlist").push().setValue(new ModelAdminDonor(getDonorname,getDonorphonenum, getDonorcategory)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(ManageAddDonor.this,"Successfully added!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ManageAddDonor.this, ManageDonorActivity.class));
                            finish(); //after submit, it will back to page
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ManageAddDonor.this,"Unable to insert", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }
}