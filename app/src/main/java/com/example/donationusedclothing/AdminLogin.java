package com.example.donationusedclothing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {

    EditText adloginUsername, adloginPassword;
    Button adloginButton;
    TextView adsignupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adloginUsername = findViewById(R.id.admin_username);
        adloginPassword = findViewById(R.id.admin_password);
        adloginButton = findViewById(R.id.loginadmin_button);
        adsignupRedirectText = findViewById(R.id.signupadminRedirectText);
        adloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {
                } else {
                    checkUser();
                }
            }
        });
        adsignupRedirectText.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLogin.this, AdminSignUp.class);
                startActivity(intent);
            }
        });
    }
    public Boolean validateUsername() {
        String val = adloginUsername.getText().toString();
        if (val.isEmpty()) {
            adloginUsername.setError("Username cannot be empty");
            return false;
        } else {
            adloginUsername.setError(null);
            return true;
        }
    }
    public Boolean validatePassword(){
        String val = adloginPassword.getText().toString();
        if (val.isEmpty()) {
            adloginPassword.setError("Password cannot be empty");
            return false;
        } else {
            adloginPassword.setError(null);
            return true;
        }
    }
    public void checkUser(){
        String userUsername = adloginUsername.getText().toString().trim();
        String userPassword = adloginPassword.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("admin");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    adloginUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userPassword)) {
                        adloginUsername.setError(null);
                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                        Intent intent = new Intent(AdminLogin.this, Admin.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else {
                        adloginPassword.setError("Invalid Credentials");
                        adloginPassword.requestFocus();
                    }
                } else {
                    adloginUsername.setError("User does not exist");
                    adloginUsername.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
