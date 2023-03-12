package com.example.donationusedclothing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditAdminActivity extends AppCompatActivity {

    EditText adeditName, adeditEmail, adeditUsername, adeditPassword;
    Button adsaveButton;
    String adnameUser, ademailUser, adusernameUser, adpasswordUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin);

        reference = FirebaseDatabase.getInstance().getReference("admin");
        adeditName = findViewById(R.id.adeditName);
        adeditEmail = findViewById(R.id.adeditEmail);
        adeditUsername = findViewById(R.id.adeditUsername);
        adeditPassword = findViewById(R.id.adeditPassword);
        adsaveButton = findViewById(R.id.adsaveButton);
        showData();
        adsaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isPasswordChanged() || isEmailChanged()){
                    Toast.makeText(EditAdminActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditAdminActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isNameChanged() {
        if (!adnameUser.equals(adeditName.getText().toString())){
            reference.child(adusernameUser).child("name").setValue(adeditName.getText().toString());
            adnameUser = adeditName.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isEmailChanged() {
        if (!ademailUser.equals(adeditEmail.getText().toString())){
            reference.child(adusernameUser).child("email").setValue(adeditEmail.getText().toString());
            ademailUser = adeditEmail.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    private boolean isPasswordChanged() {
        if (!adpasswordUser.equals(adeditPassword.getText().toString())){
            reference.child(adusernameUser).child("password").setValue(adeditPassword.getText().toString());
            adpasswordUser = adeditPassword.getText().toString();
            return true;
        } else {
            return false;
        }
    }
    public void showData(){
        Intent intent = getIntent();
        adnameUser = intent.getStringExtra("name");
        ademailUser = intent.getStringExtra("email");
        adusernameUser = intent.getStringExtra("username");
        adpasswordUser = intent.getStringExtra("password");
        adeditName.setText(adnameUser);
        adeditEmail.setText(ademailUser);
        adeditUsername.setText(adusernameUser);
        adeditPassword.setText(adpasswordUser);
    }

}