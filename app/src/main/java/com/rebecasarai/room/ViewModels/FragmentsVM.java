package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.rebecasarai.room.Repositories.MainActivityRepository;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 5/2/18.
 */

public class FragmentsVM extends AndroidViewModel{
    private final LiveData<List<Team>> mTeams;
    private LiveData<Team> selectedTeam;
    private String texto;
    private int colorBackground;
    private int counter;
    private MainActivityRepository mRepository;

    public FragmentsVM(Application application) {
        super(application);

        mRepository = new MainActivityRepository(application);
        mTeams = mRepository.getmTeams();
    }

    public List<Team> getTeamsNotLive(){
        return mRepository.getmTeamsNoTLive();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LiveData<Team> getSelectedTeam() {
        return selectedTeam;
    }

    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam = mRepository.getTeamByIdLive(selectedTeam.getIdTeam());
    }

    public int getCounter() {
        return counter;
    }

    public int getColorBackground() {
        return colorBackground;
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


    public Team getFirstTeam(){
        return  mRepository.getFirstTeam();
    }

}