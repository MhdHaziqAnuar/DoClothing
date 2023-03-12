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

public class DialogFormRequest extends DialogFragment {
    String rItemRequested, rRequestedName, rRequestedQuantity, rRequestPhone, rkey, select;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public DialogFormRequest(String rItemRequested, String rRequestedName, String rRequestedQuantity,
                             String rRequestPhone, String rkey, String select) {
        this.rItemRequested = rItemRequested;
        this.rRequestedName = rRequestedName;
        this.rRequestedQuantity = rRequestedQuantity;
        this.rRequestPhone = rRequestPhone;
        this.rkey = rkey;
        this.select = select;
    }

    TextView etItemrequest, etRequestname, etRequestquantity, etRequestphone;
    Button btnRequestadd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_add_request, container,false);
        etItemrequest = view.findViewById(R.id.etItemRequested);
        etRequestname = view.findViewById(R.id.etRequestName);
        etRequestquantity = view.findViewById(R.id.etRequestQuantity);
        etRequestphone = view.findViewById(R.id.etRequestPhone);
        btnRequestadd = view.findViewById(R.id.btnReqAdd);

        etItemrequest.setText(rItemRequested);
        etRequestname.setText(rRequestedName);
        etRequestquantity.setText(rRequestedQuantity);
        etRequestphone.setText(rRequestPhone);
        btnRequestadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String requestItem = etItemrequest.getText().toString();
                String nameRequest = etRequestname.getText().toString();
                String requestQuantity = etRequestquantity.getText().toString();
                String requestPhone = etRequestphone.getText().toString();
                if(select.equals("Change")){
                    database.child("Request").child(rkey).setValue(new ModelRequest(requestItem, nameRequest, requestQuantity, requestPhone)).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

}
