package com.lessons.app_2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.lessons.app_2.dao.CategoryMuseumsDao;
import com.lessons.app_2.dao.CountryDao;
import com.lessons.app_2.dao.MuseumDao;
import com.lessons.app_2.models.Category;
import com.lessons.app_2.models.Country;
import com.lessons.app_2.models.Museum;

@Database(
        // версия бд
        version = 1,
        // сущности
        entities = {Country.class, Museum.class, Category.class})
public abstract class AppDatabase extends RoomDatabase {

    // имеем ссылку на самого себя, так как подключение к базе данных дорогое удовольствие.
    private static AppDatabase INSTANCE;

    public synchronized static AppDatabase getInstance(Context context) {
        // если нет ещё подключения
        if(INSTANCE == null) {
            // Собираем базу данных
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "database.sql.db")
                    // читаем из локальной базы данные
                    .createFromAsset("database/database.sql.db")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    // Dto
    public abstract CountryDao countryDao();

    public abstract MuseumDao museumDao();

    public abstract CategoryMuseumsDao categoryWithMuseumsDao();
}
