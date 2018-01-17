package com.rebecasarai.room;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rebecasarai.room.models.Team;

public class EditTeamActivity extends AppCompatActivity {
    private Team mTeam;
    private EditText editNameTeam;
    private EditText editDescription;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mTeam = getIntent().getParcelableExtra("team");
        editNameTeam = (EditText) findViewById(R.id.editNameTeam);
        editDescription = (EditText) findViewById(R.id.editDescription);
        btnSave = (Button) findViewById(R.id.btnSave);

        editNameTeam.setText(mTeam.getName());
        editDescription.setText(mTeam.getDescription());
    }

}
