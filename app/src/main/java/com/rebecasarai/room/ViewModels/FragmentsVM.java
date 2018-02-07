package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rebecasarai.room.Repositories.MainActivityRepository;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 5/2/18.
 */

public class FragmentsVM extends AndroidViewModel{
    private final LiveData<List<Team>> mTeams;
    private MutableLiveData<Team> selectedTeam = new MutableLiveData<>();
    private String texto;
    private MainActivityRepository mRepository;

    public FragmentsVM(Application application) {
        super(application);

        mRepository = new MainActivityRepository(application);

        mTeams = mRepository.getmTeams();
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public MutableLiveData<Team> getSelectedTeam() {
        return this.selectedTeam;
    }

    public void setSelectedTeam(Team selectedTeam) {
        this.selectedTeam.setValue(selectedTeam);
    }

    public int getStadium(){
        int id = mRepository.getStadium(1).getIdStadium();
        return id;
    }


    public void insertTeams(){
        mRepository.insertTeams();
    }

    public LiveData<List<Team>> getmTeams() {
        return mTeams;
    }


}