package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 18/1/18.
 */

public class AddTeamVM extends AndroidViewModel {

    private final LiveData<List<Team>> mTeams;

    private AppDatabase mAppDb;

    public AddTeamVM(Application application) {
        super(application);

        mAppDb = AppDatabase.getAppDatabase(application);
        mTeams = mAppDb.teamDao().getAllLive();
    }

    public void insertTeam(Team team){
        mAppDb.teamDao().insertTeam(team);
    }




    public LiveData<List<Team>> getmTeams() {
        return mTeams;
    }
}

