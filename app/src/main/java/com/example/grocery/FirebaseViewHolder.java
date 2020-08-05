package com.example.grocery;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    public TextView txtname,txtqnt,txtrate;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);
        txtname=(TextView)itemView.findViewById(R.id.productName);
        txtqnt=(TextView)itemView.findViewById(R.id.productQnt);
        txtrate=(TextView)itemView.findViewById(R.id.productPrice);
    }
}
