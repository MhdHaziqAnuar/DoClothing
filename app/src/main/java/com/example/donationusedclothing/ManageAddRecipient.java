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

public class ManageAddRecipient extends AppCompatActivity {
    EditText adRecipientname, adRecipientphonenum, adRecipientcategory;
    Button btn_addRecipientinfo;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    ImageView btn_backAddRecipient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_add_recipient);

        btn_backAddRecipient = findViewById(R.id.btn_backAddRecipient);
        btn_backAddRecipient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adRecipientname = findViewById(R.id.adRecipientname);
        adRecipientphonenum = findViewById(R.id.adRecipientphonenum);
        adRecipientcategory = findViewById(R.id.adRecipientcategory);

        btn_addRecipientinfo = findViewById(R.id.btn_addRecipientinfo);
        btn_addRecipientinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getRecipientname = adRecipientname.getText().toString();
                String getRecipientphonenum = adRecipientphonenum.getText().toString();
                String getRecipientcategory = adRecipientcategory.getText().toString();

                if (getRecipientname.isEmpty()){
                    adRecipientname.setError("Please enter recipient name");
                }else if(getRecipientphonenum.isEmpty()){
                    adRecipientphonenum.setError("Please enter recipient contact number");
                }else if(getRecipientcategory.isEmpty()){
                    adRecipientcategory.setError("Please enter recipient's category");
                }
                else{
                    db.child("Recipientlist").push().setValue(new ModelAdminRecipient(getRecipientname,getRecipientphonenum, getRecipientcategory)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(ManageAddRecipient.this,"Successfully added!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ManageAddRecipient.this, ManageRecipientActivity.class));
                            finish(); //after submit, it will back to page
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ManageAddRecipient.this,"Unable to insert", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }
}