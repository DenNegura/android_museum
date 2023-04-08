package com.lessons.app_2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lessons.app_2.R;
import com.lessons.app_2.clicker.ItemClickListener;
import com.lessons.app_2.models.Item;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    Context context;

    List<? extends Item> items;

    ItemClickListener clickListener;

    public ItemListAdapter(Context context, List<? extends Item> items, ItemClickListener clickListener) {
        this.context = context;
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_item, parent, false));
    }

    // метод вставляет данные в item представления
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Я использую интерфейс Item, для того, что бы сделать список универсальным
        Item item = items.get(position);
        // Очень важно проверить, если ли изображение в папке asssets.
        try (InputStream imageStream = context.getAssets().open(item.getImage())) {
            Drawable drawable = Drawable.createFromStream(imageStream, null);
            holder.image.setImageDrawable(drawable);
            holder.image.setScaleType(ImageView.ScaleType.FIT_XY);
        } catch (IOException e) {
            // Если его нет, крепиться иконка "отсутствует"
            holder.image.setImageResource(R.drawable.missing);
        }
        // в holder я получаю объекты с представления с помощью метода findById(...)
        // перекладываю в виджет текста название, описание и прослушиватель нажатия
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getSubtitle());
        holder.layout.setOnClickListener(
                v -> clickListener.onClick(items.get(holder.getAdapterPosition())));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterList(List<? extends Item> filteredList) {
        items = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
