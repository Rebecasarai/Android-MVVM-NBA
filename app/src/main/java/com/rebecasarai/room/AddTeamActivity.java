package com.rebecasarai.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.rebecasarai.room.ViewModels.TeamInfoWithAllTeamsViewModel;
import com.rebecasarai.room.models.Team;

import java.util.ArrayList;
import java.util.List;

public class AddTeamActivity extends AppCompatActivity {

    public AppDatabase mAppDb;
    private ListView mList;
    private Intent i;
    TeamInfoWithAllTeamsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addteam_activity);

        // Get a reference to the ViewModel
        mViewModel = ViewModelProviders.of(this).get(TeamInfoWithAllTeamsViewModel.class);

        //mAppDb = AppDatabase.getAppDatabase(getApplicationContext());
        //mTeams = mAppDb.teamDao().getAll();


        mList = findViewById(R.id.listViewActual);


        mAppDb = AppDatabase.getAppDatabase(this.getApplication());

        mViewModel = ViewModelProviders.of(this).get(TeamInfoWithAllTeamsViewModel.class);

        mViewModel.mTeams.observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@NonNull final List<Team> mTeams) {


                for (int i = 0; i< mTeams.size(); i++){
                    Log.v("Team: ",mTeams.get(i).getName());
                }
/*
                a = new MainActivity.TeamAdapter(getApplicationContext(),mTeams);
                mList.setAdapter(a);*/
            }
        });

        //Registramos la lista, la view que recibirá luego el Context Menu
        registerForContextMenu(mList);
        //mList.setOnItemClickListener(this);



        //Spinner
        /*
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.stadiums_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/
        //End of spinner


    }
}
