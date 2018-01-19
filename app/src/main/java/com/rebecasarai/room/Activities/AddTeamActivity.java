package com.rebecasarai.room.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.R;
import com.rebecasarai.room.ViewModels.AddTeamVM;
import com.rebecasarai.room.Views.SimpleTeamAdapter;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

public class AddTeamActivity extends AppCompatActivity implements View.OnClickListener, android.text.TextWatcher {

    private AppDatabase mAppDb;
    private ListView mTeamListView;
    private AddTeamVM mViewModel;

    private SimpleTeamAdapter a;
    private ArrayAdapter<Stadium> mStadiumAdapter;

    private EditText mEditNameTeam;
    private EditText mEditDescription;
    private Button mBtnSave;
    private Team mTeamToAdd;
    private Spinner mSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addteam_activity);

        mTeamListView = (ListView) findViewById(R.id.listViewActual);
        mEditNameTeam = (EditText) findViewById(R.id.editNameTeam);
        mBtnSave = (Button) findViewById(R.id.btnSave);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mEditDescription = (EditText) findViewById(R.id.editDescription);

        mEditNameTeam.addTextChangedListener(this);

        mViewModel = ViewModelProviders.of(AddTeamActivity.this).get(AddTeamVM.class);

        mViewModel.getmStadiums().observe(this, new Observer<List<Stadium>>() {

            public void onChanged(@NonNull final List<Stadium> stadiums) {

                for (int i = 0; i< stadiums.size(); i++){
                    Log.v("Stadium: ",""+stadiums.get(i).getIdStadium());
                }

                mStadiumAdapter = new ArrayAdapter<Stadium>(getApplicationContext(), android.R.layout.simple_spinner_item, stadiums);
                mStadiumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(mStadiumAdapter);
            }
        });


        mViewModel.getmTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@NonNull final List<Team> teams) {
                for (int i = 0; i< teams.size(); i++){
                    Log.v("Team: ",teams.get(i).getName());
                }
                a = new SimpleTeamAdapter(getApplicationContext(), teams);
                mTeamListView.setAdapter(a);


            }
        });

        mBtnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                if( mEditNameTeam.getText().toString().length() != 0 ){
                    mTeamToAdd = new Team(mEditNameTeam.getText().toString(),mEditDescription.getText().toString(),R.drawable.chi2,1);
                    mViewModel.insertTeam(mTeamToAdd);

                    Toast.makeText(getApplicationContext(), "Equipo añadido",
                            Toast.LENGTH_LONG).show();
                }else {

                    mBtnSave.setError( "El nombre es requerido" );
                }


            break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


    }

    @Override
    public void afterTextChanged(Editable s) {
        if( mEditNameTeam.getText().toString().length() == 0 ){
            mEditNameTeam.setError( "El nombre es requerido" );
        }

    }
}