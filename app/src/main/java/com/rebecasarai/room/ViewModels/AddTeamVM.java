package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.Repositories.MainActivityRepository;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 18/1/18.
 */

public class AddTeamVM extends AndroidViewModel {

    private LiveData<List<Team>> mTeams;
    private LiveData<List<Stadium>> mStadiums;
    private MainActivityRepository mRespository;
    //private AppDatabase mAppDb;

    public AddTeamVM(Application application) {
        super(application);

        mRespository = new MainActivityRepository(application);
        mTeams = mRespository.getmTeams();
        mStadiums = mRespository.getStadiums();
    }

    public void insertTeam(Team team){
        mRespository.insertTeam(team);
    }


    public LiveData<List<Team>> getmTeams() {
        return mTeams;
    }

    public LiveData<List<Stadium>> getmStadiums() {
        return mStadiums;
    }




}

