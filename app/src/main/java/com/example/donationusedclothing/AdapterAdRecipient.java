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

import java.util.List;

public class AdapterAdRecipient extends RecyclerView.Adapter<AdapterAdRecipient.ReViewHolder> {

    private List<ModelAdminRecipient> relist;
    private Activity activityRe;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterAdRecipient(List<ModelAdminRecipient>relist, Activity activityRe){
        this.relist = relist;
        this.activityRe = activityRe;
    }


    @NonNull
    @Override
    public ReViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //to show recyclerview in layout_item
        View viewItem = inflater.inflate(R.layout.layout_admin_recipient,parent,false);

        return new ReViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ReViewHolder holder, int position) {
        final ModelAdminRecipient data4 = relist.get(position);
        holder.lay_recipientname.setText("Recipient name:" + data4.getRecipientname());
        holder.lay_recipientphonenum.setText("Recipient phone number: " + data4.getRecipientphonenumber());
        holder.lay_recipientcategory.setText("Recipient category: " + data4.getRecipientcategory());
        holder.lay_recipientdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //activity declared at the top
                AlertDialog.Builder builder = new AlertDialog.Builder(activityRe);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        database.child("Recipientlist").child(data4.getRkey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                //activity declared at the top
                                Toast.makeText(activityRe,"Data has been deleted",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activityRe,"Unable to delete",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).setMessage("Do you want to delete the "+data4.getRecipientname());
                builder.show();
            }
        });
        holder.card_adRecipient.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                FragmentManager manager = ((AppCompatActivity)activityRe).getSupportFragmentManager();
                DialogFormAdRecipient dialog = new DialogFormAdRecipient(
                        data4.getRecipientname(),
                        data4.getRecipientphonenumber(),
                        data4.getRecipientcategory(),
                        data4.getRkey(),
                        "Change"
                );
                dialog.show(manager, "form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return relist.size();
    }

    public class ReViewHolder extends RecyclerView.ViewHolder {

        TextView lay_recipientname, lay_recipientphonenum, lay_recipientcategory;
        ImageView lay_recipientdelete;
        CardView card_adRecipient;

        public ReViewHolder(@NonNull View itemView) {
            super(itemView);

            lay_recipientname = itemView.findViewById(R.id.lay_recipientname);
            lay_recipientphonenum = itemView.findViewById(R.id.lay_recipientphonenum);
            lay_recipientcategory = itemView.findViewById(R.id.lay_recipientcategory);
            lay_recipientdelete = itemView.findViewById(R.id.lay_recipientdelete);
            card_adRecipient = itemView.findViewById(R.id.card_adRecipient);

        }
    }
}
