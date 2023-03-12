package com.example.donationusedclothing;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DialogForm extends DialogFragment {
    int dupimages;
    String dclothestype;
    String dname;
    String dcontact;
    String ditemsize;
    String ditemcolor;
    String dmeetup;
    String key;
    String select;
    String imageURL;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    //string imageURL already declared
    public DialogForm(int dupimages, String dclothestype, String dname, String dcontact, String ditemsize, String ditemcolor,
                      String dmeetup, String key, String select) {
        this.dupimages = dupimages;
        this.dclothestype = dclothestype;
        this.dname = dname;
        this.dcontact = dcontact;
        this.ditemsize = ditemsize;
        this.ditemcolor = ditemcolor;
        this.dmeetup = dmeetup;
        this.key = key;
        this.select = select;
    }

    TextView etclothestype, etname, etphonenum, etitemsize, etitemcolor, etmeetuppoint;
    Button btnDonateadd;
    ImageView etupimages;
    Uri uri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_add_donation, container,false);
        //for update
        etupimages = view.findViewById(R.id.etDuploadImage);

        etclothestype = view.findViewById(R.id.etDclothestype);
        etname = view.findViewById(R.id.etDname);
        etphonenum = view.findViewById(R.id.etDphonenum);
        etitemsize = view.findViewById(R.id.etDitemsize);
        etitemcolor = view.findViewById(R.id.etDitemcolor);
        etmeetuppoint = view.findViewById(R.id.etDmeetuppoint);
        btnDonateadd = view.findViewById(R.id.btnDadd);

        etupimages.setImageURI(uri);
        etclothestype.setText(dclothestype);
        etname.setText(dname);
        etphonenum.setText(dcontact);
        etitemsize.setText(ditemsize);
        etitemcolor.setText(ditemcolor);
        etmeetuppoint.setText(dmeetup);
        btnDonateadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //diubah
                int images = Integer.parseInt(String.valueOf(etupimages));
                String clothestype = etclothestype.getText().toString();
                String name = etname.getText().toString();
                String donationcontact = etphonenum.getText().toString();
                String itemsize = etitemsize.getText().toString();
                String itemcolor = etitemcolor.getText().toString();
                String meetuppoint = etmeetuppoint.getText().toString();
                if(select.equals("Change")){
                    database.child("Donation").child(key).setValue(new ModelDonation(images,clothestype, name, donationcontact,
                            itemsize, itemcolor, meetuppoint)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(view.getContext(),"Successfully updated", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(),"Unable to update", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    //image
    /*ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        uri = data.getData();
                        etupimages.setImageURI(uri);
                    } else {
                        Toast.makeText(DialogForm.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
    //onclick for upload
        etupimages.setOnClickListener(new View.OnClickListener() {
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
    }*/

}