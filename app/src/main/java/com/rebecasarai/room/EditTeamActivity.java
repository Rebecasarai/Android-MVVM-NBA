package com.rebecasarai.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.rebecasarai.room.ViewModels.EditTeamVM;
import com.rebecasarai.room.Views.TeamAdaptera;
import com.rebecasarai.room.models.Team;

import java.util.List;

public class EditTeamActivity extends AppCompatActivity implements View.OnClickListener {
    private Team mTeamToUpdate;
    private EditText mEditNameTeam;
    private EditText mEditDescription;
    private Button mBtnSave;
    private ImageView mEditImageView;
    private EditTeamVM mEditViewModel;
    private TeamAdaptera a;

    private Intent i;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        mTeamToUpdate = getIntent().getParcelableExtra("team");

        mEditNameTeam = (EditText) findViewById(R.id.editNameTeam);
        mEditDescription = (EditText) findViewById(R.id.editDescription);
        mEditImageView = (ImageView) findViewById(R.id.editImageView);

        mEditNameTeam.setText(mTeamToUpdate.getName());
        mEditDescription.setText(mTeamToUpdate.getDescription());
        mEditImageView.setImageResource(mTeamToUpdate.getImageLogo());

        mEditViewModel = ViewModelProviders.of(EditTeamActivity.this).get(EditTeamVM.class);

        mEditViewModel.getmTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> teams) {
                for (int i = 0; i< teams.size(); i++){
                    Log.v("Team: ",teams.get(i).getName());
                }
                a = new TeamAdaptera(getApplicationContext(),EditTeamActivity.this,teams);

            }
        });


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.editNameTeam, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.editDescription, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);


    }


    private void updateTeam() {
/*
        if( mEditNameTeam.getText().toString().length() != 0 ){
            mTeamToUpdate = new Team(mEditNameTeam.getText().toString(),mEditDescription.getText().toString(),R.drawable.chi2,1);
            mEditViewModel.updateTeam(mTeamToUpdate);

            Toast.makeText(getApplicationContext(), "Equipo editado",
                    Toast.LENGTH_LONG).show();
        }else {

            mBtnSave.setError( "El nombre es requerido" );
        }
*/
        if (awesomeValidation.validate()) {

            mTeamToUpdate = new Team(mEditNameTeam.getText().toString(),mEditDescription.getText().toString(),R.drawable.chi2,1);
            mEditViewModel.updateTeam(mTeamToUpdate);
            Toast.makeText(this, "Equipo editado correctamente", Toast.LENGTH_LONG).show();


        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                updateTeam();
            break;
        }

    }
}
