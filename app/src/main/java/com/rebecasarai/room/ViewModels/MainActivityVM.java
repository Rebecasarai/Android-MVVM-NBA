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
    private Team selectedTeam;
    private int counter;

    private MainActivityRepository mRepository;


    public MainActivityVM(Application application) {
        super(application);

        mRepository = new MainActivityRepository(application);
        mTeams = mRepository.getmTeams();
    }

    public int getCounter() {
        return counter;
    }

    public void deleteTeam(Team team){
        mRepository.delete(team);
    }

    public void deleteByID(int id){
        mRepository.deleteByID(id);
    }

    public void insertStadium(){
        mRepository.insertStadium();
    }

    public int getStadium(){
        int id = mRepository.getStadium(1).getIdStadium();
        return id;
    }

    public Stadium getStadium(int id){
        return mRepository.getStadium(id);
    }


    public void insertTeams(){
        mRepository.insertTeams();
    }

    public LiveData<List<Team>> getmTeams() {
        return mTeams;
    }



}