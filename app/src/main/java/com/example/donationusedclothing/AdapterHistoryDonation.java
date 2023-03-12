package com.example.donationusedclothing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.List;

public class AdapterHistoryDonation extends RecyclerView.Adapter<MyHViewHolder> {
    private Context context3;
    private List<ModelClothRecord> dataList3;
    public AdapterHistoryDonation(Context context, List<ModelClothRecord> dataList) {
        this.context3 = context;
        this.dataList3 = dataList;
    }
    @NonNull
    @Override
    public MyHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_historydonation, parent, false);
        return new MyHViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyHViewHolder holder, int position) {
        Glide.with(context3).load(dataList3.get(position).getDataDonatedImage()).into(holder.recImage3);
        holder.recDonorname3.setText(dataList3.get(position).getDataDonorname());
        holder.recDonorphonenum3.setText(dataList3.get(position).getDataDonorPhonenum());
        holder.recDonordesc3.setText(dataList3.get(position).getDataDonatedDesc());
        holder.recDonorquantity3.setText(dataList3.get(position).getDataDonatedQuantity());
        /*holder.recCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context3, DetailActivity.class);
                intent.putExtra("Image", dataList3.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Description", dataList3.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Title", dataList3.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Key",dataList3.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Language", dataList3.get(holder.getAdapterPosition()).getDataLang());
                intent.putExtra("Size", dataList3.get(holder.getAdapterPosition()).getDataSize());
                intent.putExtra("Color", dataList3.get(holder.getAdapterPosition()).getDataColor());
                intent.putExtra("Meetup", dataList3.get(holder.getAdapterPosition()).getDataMeetup());
                context3.startActivity(intent);
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return dataList3.size();
    }
    public void searchDataList(ArrayList<ModelClothRecord> searchList){
        dataList3 = searchList;
        notifyDataSetChanged();
    }
}
class MyHViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage3;
    TextView recDonorname3, recDonorphonenum3, recDonordesc3, recDonorquantity3;
    CardView recCard3;
    public MyHViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage3 = itemView.findViewById(R.id.recImage3);
        recCard3 = itemView.findViewById(R.id.recCard3);
        recDonorname3 = itemView.findViewById(R.id.recDonorname3);
        recDonorphonenum3 = itemView.findViewById(R.id.recDonorphonenum3);
        recDonordesc3 = itemView.findViewById(R.id.recDonordesc3);
        recDonorquantity3 = itemView.findViewById(R.id.recDonorquantity3);
    }
}
