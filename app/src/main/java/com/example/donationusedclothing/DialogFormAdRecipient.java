package com.example.donationusedclothing;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogFormAdRecipient extends DialogFragment {
    String adrecipientname, adrecipientphonenum, adrecipientcategory, rekey, select;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogFormAdRecipient(String adrecipientname, String adrecipientphonenum, String adrecipientcategory, String rekey, String select) {
        this.adrecipientname = adrecipientname;
        this.adrecipientphonenum = adrecipientphonenum;
        this.adrecipientcategory= adrecipientcategory;
        this.rekey = rekey;
        this.select = select;
    }

    TextView adminrecipientname, adminrecipientphonenum, adminrecipientcategory;
    Button btnaddRecipientinfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_manage_add_recipient, container,false);
        adminrecipientname = view.findViewById(R.id.adRecipientname);
        adminrecipientphonenum = view.findViewById(R.id.adRecipientphonenum);
        adminrecipientcategory = view.findViewById(R.id.adRecipientcategory);
        btnaddRecipientinfo = view.findViewById(R.id.btn_addRecipientinfo);

        adminrecipientname.setText(adrecipientname);
        adminrecipientphonenum.setText(adrecipientphonenum);
        adminrecipientcategory.setText(adrecipientcategory);
        btnaddRecipientinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipientNm = adminrecipientname.getText().toString();
                String recipientPNum = adminrecipientphonenum.getText().toString();
                String recipientCat = adminrecipientcategory.getText().toString();

                if(select.equals("Change")){
                    database.child("Recipientlist").child(rekey).setValue(new ModelAdminRecipient(recipientNm, recipientPNum, recipientCat)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(view.getContext(),"Successfully updated", Toast.LENGTH_SHORT).show();

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
        Dialog dialogR = getDialog();
        if(dialogR != null){
            dialogR.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
