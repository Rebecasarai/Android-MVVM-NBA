package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 18/1/18.
 */

public class AddTeamVM extends AndroidViewModel {

    private LiveData<List<Team>> mTeams;
    private LiveData<List<Stadium>> mStadiums;

    private AppDatabase mAppDb;

    public AddTeamVM(Application application) {
        super(application);

        mAppDb = AppDatabase.getAppDatabase(application);
        mTeams = mAppDb.teamDao().getAllLive();
        mStadiums = mAppDb.stadiumDao().getAllLive();
    }

    public void insertTeam(Team team){
        new InsertTeamAsyncTask(mAppDb).execute(team);
    }


    public LiveData<List<Team>> getmTeams() {
        return mTeams;
    }

    public LiveData<List<Stadium>> getmStadiums() {
        return mStadiums;
    }


    private static class InsertTeamAsyncTask extends AsyncTask<Team, Void, Void > {

        private AppDatabase db;

        public InsertTeamAsyncTask(AppDatabase database) {
            db = database;
        }

        @Override
        protected Void doInBackground(Team... params) {
            db.teamDao().insertTeam(params[0]);
            return null;
        }


    }


}

