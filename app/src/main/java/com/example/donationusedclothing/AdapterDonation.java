package com.example.donationusedclothing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterDonation extends RecyclerView.Adapter<AdapterDonation.MyViewHolder> {
    private List<ModelDonation> mList;
    private Activity activity;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterDonation(List<ModelDonation> mList, Activity activity){
        this.mList = mList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //to show recyclerview in layout_item
        View viewItem = inflater.inflate(R.layout.layout_item,parent,false);

        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ModelDonation data = mList.get(position);
       // holder.tv_imagedonation.setImageURI(mList.get(position));
      //  Glide.with(activity).load(mList.get(position).getUpimages()).into(holder.tv_imagedonation);

        holder.tv_imagedonation.setImageResource(data.getUpimages());

        holder.tv_itemtype.setText("Clothes type : " + data.getClothestype());
        holder.tv_username.setText("Donor name : " + data.getName());
        holder.tv_donationcontactnum.setText("Contact Number :" + data.getDonationContact());
        holder.tv_itemsize.setText("Item size :" + data.getItemSize());
        holder.tv_itemcolor.setText("Item color :" + data.getItemColor());
        holder.tv_meetuppoint.setText("Meetup point for collection : " + data.getMeetupPoint());
        holder.vDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //activity declared at the top
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        database.child("Donation").child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                //activity declared at the top
                                Toast.makeText(activity,"Data has been deleted",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity,"Unable to delete",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).setMessage("Do you want to delete the "+data.getName());
                builder.show();
            }

        });
        //update
        holder.card_hasil.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogForm dialog = new DialogForm(
                        data.getUpimages(),
                        data.getClothestype(),
                        data.getName(),
                        data.getDonationContact(),
                        data.getItemSize(),
                        data.getItemColor(),
                        data.getMeetupPoint(),
                        data.getKey(),
                        "Change"
                );
                dialog.show(manager, "form");
                return true;
            }
        });

        //details
        holder.card_hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailDonation.class);
                intent.putExtra("Image", mList.get(holder.getAdapterPosition()).getUpimages());
                intent.putExtra("Clothes type", mList.get(holder.getAdapterPosition()).getClothestype());
                intent.putExtra("Donor name", mList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("Contact number", mList.get(holder.getAdapterPosition()).getDonationContact());
                intent.putExtra("Item size", mList.get(holder.getAdapterPosition()).getItemSize());
                intent.putExtra("Item color", mList.get(holder.getAdapterPosition()).getItemColor());
                intent.putExtra("Meetup point for collection", mList.get(holder.getAdapterPosition()).getMeetupPoint());

                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    //delete
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_itemtype, tv_username, tv_donationcontactnum, tv_itemsize, tv_itemcolor,
                tv_meetuppoint;
        ImageView tv_imagedonation, vDelete;
        CardView card_hasil;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_imagedonation = itemView.findViewById(R.id.tv_imagedonation);
            tv_itemtype = itemView.findViewById(R.id.tv_itemtype);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_donationcontactnum = itemView.findViewById(R.id.tv_donationcontactnum);
            tv_itemsize = itemView.findViewById(R.id.tv_itemsize);
            tv_itemcolor = itemView.findViewById(R.id.tv_itemcolor);
            tv_meetuppoint = itemView.findViewById(R.id.tv_meetuppoint);
            vDelete = itemView.findViewById(R.id.vDelete);
            card_hasil = itemView.findViewById(R.id.card_hasil);
        }
    }
}
