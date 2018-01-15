package com.rebecasarai.room;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.rebecasarai.room.ViewModels.TeamInfoWithAllTeamsViewModel;
import com.rebecasarai.room.models.Team;

import java.util.ArrayList;
import java.util.List;

public class AddTeamActivity extends AppCompatActivity {
    private List<Team> mTeams;
    private AppDatabase mAppDb;
    private TeamInfoWithAllTeamsViewModel mTeamVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        // Get a reference to the ViewModel
        mTeamVM = ViewModelProviders.of(this).get(TeamInfoWithAllTeamsViewModel.class);

        mAppDb = AppDatabase.getAppDatabase(getApplicationContext());
        mTeams = mAppDb.teamDao().getAll();

        //Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.stadiums_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //End of spinner


    }

    private ArrayList<Integer> getStadiums(){
        ArrayList<Integer> stadiums = new ArrayList<>();
        for (int i= 0; i< mTeams.size(); i++){
            stadiums.add(mTeams.get(i).getIdStadium());
        }
        return stadiums;
    }
}
