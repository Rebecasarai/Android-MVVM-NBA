package com.rebecasarai.room.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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

    @Query("SELECT * FROM teams")
    LiveData<List<Team>> getAllLive();

    @Query("SELECT * FROM teams WHERE idTeam IN (:teamsIds)")
    List<Team> loadAllByIds(int[] teamsIds);

    @Query("select * from teams where idTeam = :id")
    Team getTeamById(int id);

    @Query("select * from teams where idTeam = :id")
    LiveData<Team> getTeamByIdLive(int id);


    @Query("DELETE FROM teams where idTeam = :id")
    void deleteById(int id);

    @Query("DELETE FROM teams")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Team... teams);

    @Insert(onConflict = IGNORE)
    void insertTeam(Team team);

    @Delete
    void delete(Team team);

    @Update
    public void updateTeams(Team... teams);

    @Update
    public void updateTeam(Team team);

    /* Ejemplo
    @Query("select * from Book where id = :id")
    Book loadBookById(int id);
    */
}
