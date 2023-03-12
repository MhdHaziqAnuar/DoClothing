package com.example.donationusedclothing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdapterAdDonor extends RecyclerView.Adapter<AdapterAdDonor.DViewHolder> {

    private List <ModelAdminDonor> dlist;
    private Activity activityD;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterAdDonor(List<ModelAdminDonor>dlist, Activity activityD){
        this.dlist = dlist;
        this.activityD = activityD;
    }

    @NonNull
    @Override
    public DViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //to show recyclerview in layout_item
        View viewItem = inflater.inflate(R.layout.layout_admin_donor,parent,false);

        return new DViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DViewHolder holder, int position) {
        final ModelAdminDonor data3 = dlist.get(position);
        holder.lay_donorname.setText("Donor name:" + data3.getDonorname());
        holder.lay_donorphonenum.setText("Donor phone number: " + data3.getDonorphonenumber());
        holder.lay_donorcategory.setText("Donor category: " + data3.getDonorcategory());
        holder.lay_donordelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //activity declared at the top
                AlertDialog.Builder builder = new AlertDialog.Builder(activityD);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        database.child("Donorlist").child(data3.getDkey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                //activity declared at the top
                                Toast.makeText(activityD,"Data has been deleted",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activityD,"Unable to delete",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).setMessage("Do you want to delete the "+data3.getDonorname());
                builder.show();
            }
        });
        holder.card_adDonor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                FragmentManager manager = ((AppCompatActivity)activityD).getSupportFragmentManager();
                DialogFormAdDonor dialog = new DialogFormAdDonor(
                        data3.getDonorname(),
                        data3.getDonorphonenumber(),
                        data3.getDonorcategory(),
                        data3.getDkey(),
                        "Change"
                );
                dialog.show(manager, "form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dlist.size();
    }

    //search
    /*public void searchDataDonor(ArrayList<ModelAdminDonor> searchListDonor){
        this.dlist = searchListDonor;
        notifyDataSetChanged();
    }*/

    public class DViewHolder extends RecyclerView.ViewHolder {

        TextView lay_donorname, lay_donorphonenum, lay_donorcategory;
        ImageView lay_donordelete;
        CardView card_adDonor;

        public DViewHolder(@NonNull View itemView) {
            super(itemView);
            lay_donorname = itemView.findViewById(R.id.lay_donorname);
            lay_donorphonenum = itemView.findViewById(R.id.lay_donorphonenum);
            lay_donorcategory = itemView.findViewById(R.id.lay_donorcategory);
            lay_donordelete = itemView.findViewById(R.id.lay_donordelete);
            card_adDonor = itemView.findViewById(R.id.card_adDonor);
        }
    }
}


