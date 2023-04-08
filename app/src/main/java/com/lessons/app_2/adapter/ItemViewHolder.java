package com.lessons.app_2.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lessons.app_2.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    LinearLayout layout;

    ImageView image;

    TextView title, description;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.itemRow);
        image = itemView.findViewById(R.id.imageItemList);
        title = itemView.findViewById(R.id.titleItemList);
        description = itemView.findViewById(R.id.descriptionItemList);
    }
}
