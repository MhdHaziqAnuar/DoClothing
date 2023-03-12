package com.example.donationusedclothing;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ClothRecordDetail extends AppCompatActivity {
    TextView detailDonorname, detailDonatedPhonenum, detailDonatedDesc, detailDonatedQuantity;
    ImageView detailDonorimage, btn_backClothDetail;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_record_detail);

        btn_backClothDetail = findViewById(R.id.btn_backClothDetail);
        btn_backClothDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        detailDonorname = findViewById(R.id.detailDonorname);
        detailDonatedPhonenum = findViewById(R.id.detailDonatedPhonenum);
        detailDonatedDesc = findViewById(R.id.detailDonatedDesc);
        detailDonatedQuantity = findViewById(R.id.detailDonatedQuantity);
        detailDonorimage = findViewById(R.id.detailDonorimage);

        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDonorname.setText(bundle.getString("Donor's name"));
            detailDonatedPhonenum.setText(bundle.getString("Donor's Phone Number"));
            detailDonatedDesc.setText(bundle.getString("Description"));
            detailDonatedQuantity.setText(bundle.getString("Quantity"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailDonorimage);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Cloth Records");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(ClothRecordDetail.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ManageClothRecord.class));
                        finish();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothRecordDetail.this, ClothRecordUpdate.class)
                        .putExtra("Donor's name", detailDonorname.getText().toString())
                        .putExtra("Donor's Phone Number", detailDonatedPhonenum.getText().toString())
                        .putExtra("Description", detailDonatedDesc.getText().toString())
                        .putExtra("Quantity", detailDonatedQuantity.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}