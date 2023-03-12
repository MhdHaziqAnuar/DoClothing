package com.example.donationusedclothing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapterCloth extends RecyclerView.Adapter<MyAViewHolder> {
    private Context context;
    private List<ModelClothRecord> dataList;

    public MyAdapterCloth(Context context, List<ModelClothRecord> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_cloth, parent, false);
        return new MyAViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyAViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataDonatedImage()).into(holder.recImage);
        holder.recDonorname.setText(dataList.get(position).getDataDonorname());
        holder.recDonorphonenum.setText(dataList.get(position).getDataDonorPhonenum());
        holder.recDonordesc.setText(dataList.get(position).getDataDonatedDesc());
        holder.recDonorquantity.setText(dataList.get(position).getDataDonatedQuantity());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClothRecordDetail.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataDonatedImage());
                intent.putExtra("Donor's name", dataList.get(holder.getAdapterPosition()).getDataDonorname());
                intent.putExtra("Donor's Phone Number", dataList.get(holder.getAdapterPosition()).getDataDonorPhonenum());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDataDonatedDesc());
                intent.putExtra("Quantity", dataList.get(holder.getAdapterPosition()).getDataDonatedQuantity());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());

                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //search
    public void searchDataList(ArrayList<ModelClothRecord> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}
class MyAViewHolder extends RecyclerView.ViewHolder{
    ImageView recImage;
    TextView recDonorname, recDonorphonenum, recDonordesc, recDonorquantity;
    CardView recCard;
    public MyAViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage = itemView.findViewById(R.id.recImage);
        recDonorname = itemView.findViewById(R.id.recDonorname);
        recDonorphonenum = itemView.findViewById(R.id.recDonorphonenum);
        recDonordesc = itemView.findViewById(R.id.recDonordesc);
        recDonorquantity= itemView.findViewById(R.id.recDonorquantity);
        recCard = itemView.findViewById(R.id.recCard);
    }
}
