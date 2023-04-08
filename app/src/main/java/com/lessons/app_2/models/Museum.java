package com.lessons.app_2.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

// Entity - говорит о том, что на основе класса
// должена быть создана таблица
@Entity(tableName = "museum",
        foreignKeys = {@ForeignKey(
                entity = Country.class,
                parentColumns = "id",
                childColumns = "country_id")})
public class Museum implements Item {

    // Первичный кюч
    @PrimaryKey(autoGenerate = true)
    public int id;

    // Наименование полей таблицы
    @ColumnInfo(name = "country_id")
    public int countryId;

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

    @ColumnInfo(name = "category_id")
    public int categoryId;

    // Конструктор, геттеры и сеттеры.
    public Museum(int id, int countryId, @NonNull String title, @NonNull String location,
                  @NonNull String description, String image, int categoryId) {
        this.id = id;
        this.countryId = countryId;
        this.title = title;
        this.location = location;
        this.description = description;
        this.image = image;
        this.categoryId = categoryId;
    }

    @NonNull
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubtitle() {
        return "";
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
