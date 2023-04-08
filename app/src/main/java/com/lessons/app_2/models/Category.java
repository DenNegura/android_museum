package com.lessons.app_2.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class Category implements Item {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "image")
    public String image;

    public Category(int id, @NonNull String title) {
        this.id = id;
        this.title = title;
    }

    @NonNull
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getSubtitle() {
        return "";
    }

    @Override
    public String getImage() {
        return image;
    }
}
