package com.lessons.app_2.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.lessons.app_2.models.CategoryMuseums;

import java.util.List;

@Dao
public interface CategoryMuseumsDao {

    @Transaction
    @Query("SELECT * FROM category")
    List<CategoryMuseums> getCategoriesWithMuseums();

    @Transaction
    @Query("SELECT c.id, c.title, c.image, " +
            "m.id, m.title, m.location, m.description, m.image, " +
            "m.country_id, m.category_id FROM category AS c " +
            "JOIN museum AS m ON c.id = m.category_id " +
            "WHERE m.country_id = :id GROUP BY c.title")
    List<CategoryMuseums> getCategoriesWithMuseumsByCountryId(int id);
}
