package com.rebecasarai.room.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

/**
 * Created by rsgonzalez on 10/01/18.
 */
@Dao
public interface StadiumDao {
    @Insert
    void insertAll(Stadium... stadiums);

    @Delete
    void delete(Stadium stadium);
}
