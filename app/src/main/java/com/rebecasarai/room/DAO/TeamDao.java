package com.rebecasarai.room.DAO;

import android.arch.persistence.room.*;

import com.rebecasarai.room.models.Team;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by rsgonzalez on 10/01/18.
 */
@Dao
public interface TeamDao {
    @Query("SELECT * FROM teams")
    List<Team> getAll();

    @Query("SELECT * FROM teams WHERE idTeam IN (:teamsIds)")
    List<Team> loadAllByIds(int[] teamsIds);

    @Query("DELETE FROM teams")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Team... teams);

    @Insert(onConflict = IGNORE)
    void insertTeam(Team team);

    @Delete
    void delete(Team team);

    @Update
    public void updateUsers(Team... teams);


}
