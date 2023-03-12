package com.example.donationusedclothing;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addDonation extends AppCompatActivity {

    EditText etDclothestype, etDname, etDphonenum, etDitemsize, etDitemcolor, etDmeetuppoint;
    Button btnDadd;
    ImageView etDuploadImage;
    String imageURL; //dleted imageurl
    Uri uri;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        etDuploadImage = findViewById(R.id.etDuploadImage);

        etDclothestype = findViewById(R.id.etDclothestype);
        etDname = findViewById(R.id.etDname);
        etDphonenum = findViewById(R.id.etDphonenum);
        etDitemsize = findViewById(R.id.etDitemsize);
        etDitemcolor = findViewById(R.id.etDitemcolor);
        etDmeetuppoint = findViewById(R.id.etDmeetuppoint);

        btnDadd = findViewById(R.id.btnDadd);
        btnDadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();

                int getImages = Integer.parseInt(String.valueOf(etDuploadImage));
                String getClothestype = etDclothestype.getText().toString();
                String getName = etDname.getText().toString();
                String getDonationContact = etDphonenum.getText().toString();
                String getItemSize = etDitemsize.getText().toString();
                String getItemColor = etDitemcolor.getText().toString();
                String getMeetupPoint = etDmeetuppoint.getText().toString();


                if (getClothestype.isEmpty()){
                    etDclothestype.setError("Please enter clothes type");
                }else if(getName.isEmpty()){
                    etDname.setError("Please enter donor name");
                }else if(getDonationContact.isEmpty()) {
                    etDname.setError("Please enter contact number");
                }else if(getItemSize.isEmpty()) {
                    etDname.setError("Please enter item size");
                }else if(getItemColor.isEmpty()) {
                    etDname.setError("Please enter item color");
                }else if(getMeetupPoint.isEmpty()) {
                    etDname.setError("Please enter meetup location");
                }else{
                    database.child("Donation").push().setValue(new ModelDonation(getImages, getClothestype,getName,getDonationContact,
                            getItemSize, getItemColor,getMeetupPoint)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(addDonation.this,"Successfully added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(addDonation.this,Donation.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(addDonation.this,"Unable to insert", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        
        //image
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            etDuploadImage.setImageURI(uri);
                        } else {
                            Toast.makeText(addDonation.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        //onclick for upload
        etDuploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

    }
    //to save image
    public void saveData(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(addDonation.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                //uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

}