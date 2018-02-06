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

    /**
     * Obtiene todos los equipos
     * @return Lista de equipos
     */
    public LiveData<List<Team>> getmTeams() {
        return mAllTeams;
    }

    public Team getFirstTeam() {
        return  mTeamDao.getAll().get(0);
    }

    public LiveData<Team> getTeamByIdLive(int id){
        return mTeamDao.getTeamByIdLive(id);
    }


    /**
     * Elimina team recibiendo el objeto team, en TeamDao con la anotation Delete
     * @param team
     */
    public void delete(Team team){
        new deleteTeamAsync(mTeamDao).execute(team);
    }

    /**
     * Elimina equipo por id
     * @param id del equipo a eliminar
     */
    public void deleteByID(int id){
        new deleteTeamByIdAsync(mTeamDao).execute(id);
    }

    /**
     * Inserta 1 Stadium
     */
    public void insertStadium(){
        new InsertStadiumAsync(mStadiumDao).execute(new Stadium("United Center", "41.8817328,-87.6742026","Great Stadium in 1901 W Madison St"));
    }

    /**
     * Obtiene el id del stadium
     * TODO: Hacer esto asincrono
     * @return
     */
    public int getStadiumPrimero(){

        getStadiumPrimeroAsync g = new getStadiumPrimeroAsync(mStadiumDao);
        g.execute();

        return 1;//mStadiumDao.getStadium(1).getIdStadium();
    }



    /**
     * Obtiene stadium por id
     * @return Stadium
     */
    public LiveData<List<Stadium>> getStadiums(){

        /*getStadiumAsync n = new getStadiumAsync(mStadiumDao);
        n.execute(id);*/
        return  mStadiumDao.getAllLive();
        //getStadiumAsync n = new getStadiumAsync(id) ;
        //return  new getStadiumAsync(mStadiumDao).execute(id);
        //Stadium s = (Stadium) new getStadiumAsync(mStadiumDao).execute(id); // mStadiumDao.getStadium(id);
    }

    /**
     * Obtiene stadium por id
     * @param id
     * @return Stadium
     */
    public Stadium getStadium(int id){

        /*getStadiumAsync n = new getStadiumAsync(mStadiumDao);
        n.execute(id);*/
        return  mStadiumDao.getStadium(id);
        //getStadiumAsync n = new getStadiumAsync(id) ;
        //return  new getStadiumAsync(mStadiumDao).execute(id);
        //Stadium s = (Stadium) new getStadiumAsync(mStadiumDao).execute(id); // mStadiumDao.getStadium(id);
    }

    /**
     * Inserta un equipo intantaneamente, con valores predeterminados.
     * Siempre Chicago Bulls, con primer Id de stadium
     */
    public void insertTeams(){
        int idInsertar = getStadiumPrimero();
        Team team = new Team("Chicago Bulls", "Buen equipo", R.drawable.chi2,  idInsertar);
        new InsertTeamAsyncTask(mTeamDao).execute(team);
    }

    public void updateTeam(Team team){
        mTeamDao.updateTeam(team);
    }


    public void insertTeam(Team team){
        new InsertTeamAsyncTask(mTeamDao).execute(team);
    }


    /**
     * Tarea asincrona que inserta el equipo recibiendolo por parametro
     */
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


    /**
     * Tarea asincrona que inserta un estadio recibiendolo como parametro
     */
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

    /**
     * Tarea asincrona que elimina un equipo por id.
     * Recibe @param Integer
     */
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

    /**
     * Tarea asincrona que elimina un equipo reibiendo el objeto team
     */
    private static class deleteTeamAsync extends AsyncTask<Team, Void, Void > {

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

    private static class getStadiumAsync extends AsyncTask<Integer, Void, Stadium > {

        private StadiumDao mStadiumDb;
        public Stadium s;

        public getStadiumAsync(StadiumDao stadiumDb) {
            mStadiumDb = stadiumDb;
        }
/*
        @Override
        protected Stadium doInBackground(Integer... params) {
            //db.stadiumDao().insertStadium(params[0]);
            s = mStadiumDb.getStadium(params[0]);
            return s;
        }*/

        @Override
        protected Stadium doInBackground(Integer... params) {

            Stadium s = mStadiumDb.getStadium(params[0]);
            return s;
        }



    }

    private static class getStadiumPrimeroAsync extends AsyncTask<Void, Void, Integer > {

        private StadiumDao mStadiumDb;
        public Integer id;

        public getStadiumPrimeroAsync(StadiumDao stadiumDb) {
            mStadiumDb = stadiumDb;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            //db.stadiumDao().insertStadium(params[0]);
            id = mStadiumDb.getStadiumPimero().getIdStadium();
            return id;
        }

    }




}


