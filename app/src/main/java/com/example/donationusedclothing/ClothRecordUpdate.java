package com.example.donationusedclothing;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ClothRecordUpdate extends AppCompatActivity {

    ImageView updateDonatedImage, btn_backClothUpdate;
    Button updateButton;
    TextView updateDonatedby, updateDonatedphonenum, updateDonatedDesc, updateDonatedQuantity;
    //TextView UpdonatedName, UpdonatedPhonenum, UpdonatedDesc, UpdonatedQuantity;
    String imageUrl;
    String key, oldImageURL;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_record_update);

        btn_backClothUpdate = findViewById(R.id.btn_backClothUpdate);
        btn_backClothUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        updateButton = findViewById(R.id.updateButton);
        updateDonatedby = findViewById(R.id.updateDonatedby);
        updateDonatedphonenum = findViewById(R.id.updateDonatedphonenum);
        updateDonatedDesc = findViewById(R.id.updateDonatedDesc);
        updateDonatedQuantity = findViewById(R.id.updateDonatedQuantity);
        updateDonatedImage = findViewById(R.id.updateDonatedImage);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            updateDonatedImage.setImageURI(uri);
                        } else {
                            Toast.makeText(ClothRecordUpdate.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Glide.with(ClothRecordUpdate.this).load(bundle.getString("Image")).into(updateDonatedImage);
            updateDonatedby.setText(bundle.getString("Donor's name"));
            updateDonatedphonenum.setText(bundle.getString("Donor's Phone Number"));
            updateDonatedDesc.setText(bundle.getString("Description"));
            updateDonatedQuantity.setText(bundle.getString("Quantity"));
            key = bundle.getString("Key");
            oldImageURL = bundle.getString("Image");
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Cloth Records").child(key);
        updateDonatedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                Intent intent = new Intent(ClothRecordUpdate.this, ManageClothRecord.class);
                startActivity(intent);
            }
        });
    }
    public void saveData(){
        storageReference = FirebaseStorage.getInstance().getReference().child("Cloth Records").child(uri.getLastPathSegment());
        AlertDialog.Builder builder = new AlertDialog.Builder(ClothRecordUpdate.this);
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
                imageUrl = urlImage.toString();
                updateData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }
    public void updateData(){
        String UpdonatedName = updateDonatedby.getText().toString().trim();
        String UpdonatedPhonenum = updateDonatedphonenum.getText().toString().trim();
        String UpdonatedDesc = updateDonatedDesc.getText().toString();
        String UpdonatedQuantity = updateDonatedQuantity.getText().toString();
        ModelClothRecord modelClothRecord = new ModelClothRecord(UpdonatedName, UpdonatedPhonenum, UpdonatedDesc, UpdonatedQuantity, imageUrl);
        databaseReference.setValue(modelClothRecord).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(ClothRecordUpdate.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ClothRecordUpdate.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}