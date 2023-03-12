package com.example.donationusedclothing;

import androidx.fragment.app.DialogFragment;
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

public class DialogFormAdDonor extends DialogFragment {
    String addonorname, addonorphonenum, addonorcategory, adkey, select;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogFormAdDonor(String addonorname, String addonorphonenum, String addonorcategory, String adkey, String select) {
        this.addonorname = addonorname;
        this.addonorphonenum = addonorphonenum;
        this.addonorcategory= addonorcategory;
        this.adkey = adkey;
        this.select = select;
    }

    TextView admindonorname, admindonorphonenum, admindonorcategory;
    Button btnaddDonorinfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_manage_add_donor, container,false);
        admindonorname = view.findViewById(R.id.adDonorname);
        admindonorphonenum = view.findViewById(R.id.adDonorphonenum);
        admindonorcategory = view.findViewById(R.id.adDonorcategory);
        btnaddDonorinfo = view.findViewById(R.id.btn_addDonorinfo);

        admindonorname.setText(addonorname);
        admindonorphonenum.setText(addonorphonenum);
        admindonorcategory.setText(addonorcategory);
        btnaddDonorinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String donorNm = admindonorname.getText().toString();
                String donorPNum = admindonorphonenum.getText().toString();
                String donorCat = admindonorcategory.getText().toString();

                if(select.equals("Change")){
                    database.child("Donorlist").child(adkey).setValue(new ModelAdminDonor(donorNm, donorPNum, donorCat)).addOnSuccessListener(new OnSuccessListener<Void>() {
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
