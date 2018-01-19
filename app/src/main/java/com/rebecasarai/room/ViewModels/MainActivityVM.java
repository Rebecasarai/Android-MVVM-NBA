package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.R;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 15/1/18.
 */

public class MainActivityVM extends AndroidViewModel {

    private final LiveData<List<Team>> mTeams;


    private AppDatabase mAppDb;

    public MainActivityVM(Application application) {
        super(application);

        mAppDb = AppDatabase.getAppDatabase(application);
        mTeams = mAppDb.teamDao().getAllLive();
    }


    public void insertTeam(Team team){
        mAppDb.teamDao().insertTeam(team);

    }

    public void delete(Team team){
        mAppDb.teamDao().insertTeam(team);

    }

    public void deleteByID(int id){
        mAppDb.teamDao().deleteById(id);

    }

    public void insertStadium(){
        mAppDb.stadiumDao().insertStadium(new Stadium("United Center", "41.8817328,-87.6742026","Great Stadium in 1901 W Madison St"));
    }

    public void deleteAll(){
        mAppDb.teamDao().deleteAll();

    }

    public int getStadium(){
        int id = mAppDb.stadiumDao().getStadium(1).getIdStadium();
        return id;
    }

    public Stadium getStadium(int id){
        return mAppDb.stadiumDao().getStadium(id);
    }


    public void insertTeams(){
        int idInsertar = getStadium();
        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi2,  idInsertar));
    }

    public LiveData<List<Team>> getmTeams() {
        return mTeams;
    }
}

