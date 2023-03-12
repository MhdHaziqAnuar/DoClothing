package com.example.donationusedclothing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Admin extends AppCompatActivity {

    CardView mngdonors, mngrecipient, mngcrecord, adminlogout;

    TextView adprofileName, adprofileEmail, adprofileUsername, adprofilePassword;
    TextView adtitleName, adtitleUsername;
    Button adeditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mngdonors = findViewById(R.id.mngdonation);
        mngrecipient = findViewById(R.id.mngrecipient);
        mngcrecord = findViewById(R.id.mngcrecord);
        adminlogout = findViewById(R.id.adminlogout);

        //intent
        adminlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, SelectUser.class);
                startActivity(intent);
            }
        });

        mngdonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, ManageDonorActivity.class);
                startActivity(intent);
            }
        });

        mngrecipient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, ManageRecipientActivity.class);
                startActivity(intent);
            }
        });

        mngcrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin.this, ManageClothRecord.class);
                startActivity(intent);
            }
        });



        adprofileName = findViewById(R.id.adprofileName);
        adprofileEmail = findViewById(R.id.adprofileEmail);
        adprofileUsername = findViewById(R.id.adprofileUsername);
        adprofilePassword = findViewById(R.id.adprofilePassword);
        adtitleName = findViewById(R.id.adtitleName);
        adtitleUsername = findViewById(R.id.adtitleUsername);
        adeditProfile = findViewById(R.id.adeditButton);
        showAllUserData();
        adeditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });
    }
    public void showAllUserData(){
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("name");
        String emailUser = intent.getStringExtra("email");
        String usernameUser = intent.getStringExtra("username");
        String passwordUser = intent.getStringExtra("password");
        adtitleName.setText(nameUser);
        adtitleUsername.setText(usernameUser);
        adprofileName.setText(nameUser);
        adprofileEmail.setText(emailUser);
        adprofileUsername.setText(usernameUser);
        //profilePassword.setText(passwordUser);
    }
    public void passUserData(){
        String userUsername = adprofileUsername.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("admin");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    Intent intent = new Intent(Admin.this, EditProfileActivity.class);
                    intent.putExtra("name", nameFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("username", usernameFromDB);
                    intent.putExtra("password", passwordFromDB);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}
