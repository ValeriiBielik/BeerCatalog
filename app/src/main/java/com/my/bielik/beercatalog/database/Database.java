package com.my.bielik.beercatalog.database;

import android.content.Context;

import com.my.bielik.beercatalog.database.dao.BeerDao;
import com.my.bielik.beercatalog.database.entity.Beer;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Beer.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract BeerDao beerDao();

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
