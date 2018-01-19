package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.R;
import com.rebecasarai.room.Repositories.MainActivityRepository;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 15/1/18.
 */

public class MainActivityVM extends AndroidViewModel {

    private final LiveData<List<Team>> mTeams;

    private MainActivityRepository mRepository;


    public MainActivityVM(Application application) {
        super(application);

        mRepository = new MainActivityRepository(application);
        mTeams = mRepository.getmTeams();

    }

    public void deleteTeam(Team team){
        mRepository.delete(team);
    }

    public void deleteByID(int id){
        //new deleteTeamAsync(mAppDb).execute(id);
        mRepository.deleteByID(id);
    }

    public void insertStadium(){
        mRepository.insertStadium();
       // new InsertStadiumAsync(mAppDb).execute(new Stadium("United Center", "41.8817328,-87.6742026","Great Stadium in 1901 W Madison St"));
    }

    public int getStadium(){
        int id = mRepository.getStadium(1).getIdStadium();
        return id;
    }

    public Stadium getStadium(int id){
        //Stadium s = new getStadiumAsync(mAppDb).execute(id);
        //return  new getStadiumAsync(mAppDb).execute(id);
        return mRepository.getStadium(id);
    }


    public void insertTeams(){
        //int idInsertar = getStadium();
        //Team team = new Team("Chicago Bulls", "Buen equipo", R.drawable.chi2,  idInsertar);
        //new InsertTeamAsyncTask(mAppDb).execute(team);
        mRepository.insertTeams();
        //mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi2,  idInsertar));
    }

    public LiveData<List<Team>> getmTeams() {
        return mTeams;
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

    private static class InsertStadiumAsync extends AsyncTask<Stadium, Stadium, Void > {

        private AppDatabase db;

        public InsertStadiumAsync(AppDatabase database) {
            db = database;
        }

        @Override
        protected Void doInBackground(Stadium... params) {
            db.stadiumDao().insertStadium(params[0]);
            return null;
        }
    }

    private static class deleteTeamAsync extends AsyncTask<Integer, Stadium, Void > {

        private AppDatabase db;

        public deleteTeamAsync(AppDatabase database) {
            db = database;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            db.teamDao().deleteById(params[0]);
            return null;
        }


    }

    private static class getStadiumAsync extends AsyncTask<Integer, Stadium, Stadium > {

        private AppDatabase db;
        public Stadium s;

        public getStadiumAsync(AppDatabase database) {
            db = database;
        }

        @Override
        protected Stadium doInBackground(Integer... params) {
            //db.stadiumDao().insertStadium(params[0]);
            s = db.stadiumDao().getStadium(params[0]);
            return s;
        }


    }


}