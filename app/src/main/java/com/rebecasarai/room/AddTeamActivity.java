package com.rebecasarai.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.rebecasarai.room.ViewModels.AddTeamVM;
import com.rebecasarai.room.ViewModels.MainActivityVM;
import com.rebecasarai.room.Views.SimpleTeamAdapter;
import com.rebecasarai.room.Views.TeamAdaptera;
import com.rebecasarai.room.models.Team;

import java.util.List;

public class AddTeamActivity extends AppCompatActivity implements View.OnClickListener, android.text.TextWatcher {

    private AppDatabase mAppDb;
    private ListView mList;
    private Intent i;
    private AddTeamVM mViewModel;
    private SimpleTeamAdapter a;
    private EditText mEditNameTeam;
    private Button mBtnSave;
    private Team mTeamToAdd;

    private List<Team> mTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addteam_activity);


        mList = findViewById(R.id.listViewActual);
        mEditNameTeam = findViewById(R.id.editNameTeam);
        mBtnSave = findViewById(R.id.btnSave);

        mEditNameTeam.addTextChangedListener(this);

        mViewModel = ViewModelProviders.of(AddTeamActivity.this).get(AddTeamVM.class);

        mViewModel.getmTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@NonNull final List<Team> teams) {

                for (int i = 0; i< teams.size(); i++){
                    Log.v("Team: ",teams.get(i).getName());
                }
                a = new SimpleTeamAdapter(getApplicationContext(), teams);
                mList.setAdapter(a);
            }
        });

        //Registramos la lista, la view que recibirá luego el Context Menu
        registerForContextMenu(mList);

        mBtnSave.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                if( mEditNameTeam.getText().toString().length() != 0 ){
                    mTeamToAdd = new Team(mEditNameTeam.getText().toString(),"",R.drawable.chi2,1);
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