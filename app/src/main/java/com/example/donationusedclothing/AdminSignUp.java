package com.example.donationusedclothing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminSignUp extends AppCompatActivity {

    EditText adsignupEmail, adsignupName, adsignupUsername, adsignupPassword;
    TextView adloginRedirectText;
    Button adsignupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        adsignupName = findViewById(R.id.adsignup_name);
        adsignupEmail = findViewById(R.id.adsignup_email);
        adsignupUsername = findViewById(R.id.adsignup_username);
        adsignupPassword = findViewById(R.id.adsignup_password);
        adloginRedirectText = findViewById(R.id.adloginRedirectText);
        adsignupButton = findViewById(R.id.adsignup_button);
        adsignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("admin");
                String name = adsignupName.getText().toString();
                String email = adsignupEmail.getText().toString();
                String username = adsignupUsername.getText().toString();
                String password = adsignupPassword.getText().toString();
                AdminClass adminClass = new AdminClass(name, email, username, password);
                reference.child(username).setValue(adminClass);
                Toast.makeText(AdminSignUp.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminSignUp.this, AdminLogin.class);
                startActivity(intent);
            }
        });
        adloginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminSignUp.this, AdminLogin.class);
                startActivity(intent);
            }
        });
    }

}