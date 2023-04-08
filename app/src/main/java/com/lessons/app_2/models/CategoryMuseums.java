package com.lessons.app_2.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

// Связь один ко многим
public class CategoryMuseums {
    @Embedded public Category category;

    @Relation(
            parentColumn = "id",
            entityColumn = "category_id"
    )
    public List<Museum> museums;
}
