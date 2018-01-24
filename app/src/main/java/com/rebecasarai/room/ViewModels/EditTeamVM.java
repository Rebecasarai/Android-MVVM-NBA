package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rebecasarai.room.Activities.MainActivity;
import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.Repositories.MainActivityRepository;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rsgonzalez on 17/01/18.
 */

public class EditTeamVM extends AndroidViewModel {

    private LiveData<List<Team>> mTeams;
    //private AppDatabase mAppDb;
    private MainActivityRepository mRepo;

    public EditTeamVM(@NonNull Application application) {
        super(application);

        mRepo = new MainActivityRepository(application);
        mTeams = mRepo.getmTeams();
    }

    public void updateTeam(Team team){
        mRepo.updateTeam(team);
    }

    public LiveData<List<Team>> getmTeams() {
        return mTeams;
    }


}


