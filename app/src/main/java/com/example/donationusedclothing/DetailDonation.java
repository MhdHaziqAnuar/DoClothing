package com.example.donationusedclothing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailDonation extends AppCompatActivity {

    ImageView detailImage;
    TextView detailClothestype, detailDonorname, detailContactnum, detailItemsize, detailItemcolor,
            detailMeetuppoint;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_donation);

        detailClothestype = findViewById(R.id.detailClothestype);
        detailDonorname = findViewById(R.id.detailDonorname);
        detailContactnum = findViewById(R.id.detailContactnum);
        detailItemsize = findViewById(R.id.detailItemsize);
        detailItemcolor = findViewById(R.id.detailItemcolor);
        detailMeetuppoint = findViewById(R.id.detailMeetuppoint);

        detailImage = findViewById(R.id.detailImage);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //detailImage.setImageResource(bundle.getInt("Image"));
            detailClothestype.setText(bundle.getString("Clothes type"));
            detailDonorname.setText(bundle.getString("Donor name"));
            detailContactnum.setText(bundle.getString("Contact num"));
            detailItemsize.setText(bundle.getString("Item size"));
            detailItemcolor.setText(bundle.getString("Item color"));
            detailMeetuppoint.setText(bundle.getInt("Meet Up Point"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
    }

}