package com.lessons.app_2.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "country")
public class Country implements Item {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "title")
    public String title;

    @NonNull
    @ColumnInfo(name = "location")
    public String location;

    @NonNull
    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "image")
    public String image;


    public Country(int id, @NonNull String title, @NonNull String location,
                   @NonNull String description, String image) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.description = description;
        this.image = image;
    }

    @NonNull
    @Override
    public String getTitle() {
        return title;
    }

    @NonNull
    @Override
    public String getSubtitle() {
        return location;
    }

    @NonNull
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getImage() {
        return image;
    }
}
