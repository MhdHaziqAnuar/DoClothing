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

public class AdapterRequest extends RecyclerView.Adapter<AdapterRequest.RViewHolder> {
    private List<ModelRequest> rList;
    private Activity activityR;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public AdapterRequest(List<ModelRequest> rList, Activity activityR){
        this.rList = rList;
        this.activityR = activityR;
    }
    //connect activity_main w layout_item
    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //to show recyclerview in layout_item
        View viewItem = inflater.inflate(R.layout.layout_item_request,parent,false);

        return new RViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        final ModelRequest data2 = rList.get(position);
        holder.tv_itemrequest.setText("Requested item:" + data2.getItemRequested());
        holder.tv_requestname.setText("Request name: " + data2.getRequestedUser());
        holder.tv_requestquantity.setText("Requested quantity: " + data2.getRequestedQuantity());
        holder.tv_requestphone.setText("Contact number: " + data2.getRequestPhone());
        holder.rDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //activity declared at the top
                AlertDialog.Builder builder = new AlertDialog.Builder(activityR);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        database.child("Request").child(data2.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                //activity declared at the top
                                Toast.makeText(activityR,"Data has been deleted",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activityR,"Unable to delete",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).setMessage("Do you want to delete the "+data2.getRequestedUser());
                builder.show();
            }
        });
        holder.card_request.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                FragmentManager manager = ((AppCompatActivity)activityR).getSupportFragmentManager();
                DialogFormRequest dialog = new DialogFormRequest(
                        data2.getItemRequested(),
                        data2.getRequestedUser(),
                        data2.getRequestedQuantity(),
                        data2.getRequestPhone(),
                        data2.getKey(),
                        "Change"
                );
                dialog.show(manager, "form");
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return rList.size();
    }

    public class RViewHolder extends RecyclerView.ViewHolder {
        TextView tv_itemrequest, tv_requestname, tv_requestquantity, tv_requestphone;
        ImageView rDelete;
        CardView card_request;

        public RViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_itemrequest = itemView.findViewById(R.id.tv_itemrequest);
            tv_requestname = itemView.findViewById(R.id.tv_requestname);
            tv_requestquantity = itemView.findViewById(R.id.tv_requestquantity);
            tv_requestphone = itemView.findViewById(R.id.tv_requestphone);
            rDelete = itemView.findViewById(R.id.rDelete);
            card_request = itemView.findViewById(R.id.card_request);
        }
    }
}
