package com.my.bielik.beercatalog.database.dao;

import com.my.bielik.beercatalog.database.entity.Beer;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BeerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Beer beer);

    @Query("SELECT * FROM beer_table ORDER BY name")
    LiveData<List<Beer>> getFavouriteBeerList();
}
