package com.rebecasarai.room.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.DAO.StadiumDao;
import com.rebecasarai.room.DAO.TeamDao;
import com.rebecasarai.room.R;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 19/1/18.
 */

public class MainActivityRepository {
    private TeamDao mTeamDao;
    private StadiumDao mStadiumDao;
    private LiveData<List<Team>> mAllTeams;


    public MainActivityRepository(Application application) {
        AppDatabase db = AppDatabase.getAppDatabase(application);
        mTeamDao = db.teamDao();
        mStadiumDao = db.stadiumDao();
        mAllTeams = mTeamDao.getAllLive();
    }

    public LiveData<List<Team>> getmTeams() {
        return mAllTeams;
    }


    public void delete(Team team){
        new deleteTeamAsync(mTeamDao).execute(team);

    }

    public void deleteByID(int id){
        new deleteTeamByIdAsync(mTeamDao).execute(id);
    }

    public void insertStadium(){
        new InsertStadiumAsync(mStadiumDao).execute(new Stadium("United Center", "41.8817328,-87.6742026","Great Stadium in 1901 W Madison St"));
    }

    public int getStadium(){
        int id = mStadiumDao.getStadium(1).getIdStadium();
        return id;
    }

    public Stadium getStadium(int id){
        return  mStadiumDao.getStadium(id);
        //return  new getStadiumAsync(mStadiumDao).execute(id);
        //Stadium s = (Stadium) new getStadiumAsync(mStadiumDao).execute(id); // mStadiumDao.getStadium(id);
    }


    public void insertTeams(){
        int idInsertar = getStadium();
        Team team = new Team("Chicago Bulls", "Buen equipo", R.drawable.chi2,  idInsertar);
        new InsertTeamAsyncTask(mTeamDao).execute(team);
        //mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi2,  idInsertar));
    }



    private static class InsertTeamAsyncTask extends AsyncTask<Team, Void, Void > {

        private TeamDao mTeamDao;

        public InsertTeamAsyncTask(TeamDao teamDao) {
            mTeamDao = teamDao;
        }

        @Override
        protected Void doInBackground(Team... params) {
            mTeamDao.insertTeam(params[0]);
            return null;
        }


    }

    private static class InsertStadiumAsync extends AsyncTask<Stadium, Stadium, Void > {

        private StadiumDao mAsyncTaskDao;

        public InsertStadiumAsync(StadiumDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Stadium... params) {
            mAsyncTaskDao.insertStadium(params[0]);
            return null;
        }
    }

    private static class deleteTeamByIdAsync extends AsyncTask<Integer, Void, Void > {

        private TeamDao mTeamDao;

        public deleteTeamByIdAsync(TeamDao teamDao) {
            mTeamDao = teamDao;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            mTeamDao.deleteById(params[0]);
            return null;
        }


    }

    private static class deleteTeamAsync extends AsyncTask<Team, Stadium, Void > {

        private TeamDao mTeamDao;

        public deleteTeamAsync(TeamDao mTeam) {
            mTeamDao = mTeam;
        }

        @Override
        protected Void doInBackground(Team... params) {
            mTeamDao.delete(params[0]);
            return null;
        }


    }

    private static class getStadiumAsync extends AsyncTask<Integer, Stadium, Stadium > {

        private StadiumDao mStadiumDb;
        public Stadium s;

        public getStadiumAsync(StadiumDao stadiumDb) {
            mStadiumDb = stadiumDb;
        }

        @Override
        protected Stadium doInBackground(Integer... params) {
            //db.stadiumDao().insertStadium(params[0]);
            s = mStadiumDb.getStadium(params[0]);
            return s;
        }



    }



}

