package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.R;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rsgonzalez on 17/01/18.
 */

public class EditTeamVM extends AndroidViewModel {

    private LiveData<List<Team>> mTeams;
    private AppDatabase mAppDb;


    public EditTeamVM(@NonNull Application application) {
        super(application);

        mAppDb = AppDatabase.getAppDatabase(application);
        mTeams = mAppDb.teamDao().getAllLive();
    }

    public void insertTeam(Team team){
        mAppDb.teamDao().insertTeam(team);
    }


}
