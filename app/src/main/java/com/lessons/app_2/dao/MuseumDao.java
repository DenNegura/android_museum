package com.lessons.app_2.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.lessons.app_2.models.Museum;

import java.util.List;

@Dao
public interface MuseumDao {

    @Query("SELECT * FROM museum")
    List<Museum> getAll();
}
