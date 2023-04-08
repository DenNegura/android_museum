package com.lessons.app_2.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.lessons.app_2.models.Country;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM country")
    List<Country> getAll();
}
