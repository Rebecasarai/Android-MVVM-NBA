package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.R;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 15/1/18.
 */

public class TeamInfoWithAllTeamsViewModel extends AndroidViewModel {

    public final LiveData<List<Team>> mTeams;

    private AppDatabase mAppDb;
    private LiveData<Team> team;

    private final MutableLiveData<Team> selected = new MutableLiveData<Team>();

    public TeamInfoWithAllTeamsViewModel(Application application) {
        super(application);

        mAppDb = AppDatabase.getAppDatabase(this.getApplication());

        // Books is a LiveData object so updates are observed.
        mTeams = mAppDb.teamDao().getAllLive();
    }

    public LiveData<Team> getTeam() {
        return team;
    }

    public List<Team> getTeams(){
        List<Team> mTeams = mAppDb.teamDao().getAll();
        return mTeams;
    }

    public void insertTeam(Team team){
        mAppDb.teamDao().insertTeam(team);

    }

    public void delete(Team team){
        mAppDb.teamDao().insertTeam(team);

    }


    public Team getTeam(int id){
        Team team = mAppDb.teamDao().getTeamById(id);
        return team;

    }

    public void deleteAll(){
        mAppDb.teamDao().deleteAll();

    }

    public void select(Team team) {
        selected.setValue(team);
    }

    public LiveData<Team> getSelected() {
        return selected;
    }


    public void insertTeams(){
        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi, 1));
        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi, 1));
        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi, 1));
        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi, 1));


    }
}
