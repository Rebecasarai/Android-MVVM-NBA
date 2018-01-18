package com.rebecasarai.room.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by rsgonzalez on 10/01/18.
 */
@Dao
public interface StadiumDao {
    @Insert
    void insertAll(Stadium... stadiums);


    @Insert(onConflict = IGNORE)
    void insertStadium(Stadium stadium);

    @Delete
    void delete(Stadium stadium);

    @Query("select * from stadiums where idStadium = :id")
    Stadium getStadium(int id);

    @Query("select * from stadiums where idStadium = :id")
    LiveData<Stadium> getStadiumLive(int id);


    @Query("SELECT * FROM stadiums")
    LiveData<List<Stadium>> getAllLive();
}
