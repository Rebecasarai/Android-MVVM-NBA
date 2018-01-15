package com.rebecasarai.room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 15/1/18.
 */

public class TeamInfoWithAllTeamsViewModel extends AndroidViewModel {

    public final LiveData<List<Team>> mTeams;

    private AppDatabase mDb;

    public TeamInfoWithAllTeamsViewModel(Application application) {
        super(application);

        // Books is a LiveData object so updates are observed.
        mTeams = mDb.teamDao().getAllLive();
    }

}
