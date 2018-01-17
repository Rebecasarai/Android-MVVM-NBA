package com.rebecasarai.room;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.rebecasarai.room.models.Team;

public class EditTeamActivity extends AppCompatActivity implements View.OnClickListener {
    private Team mTeam;
    private EditText editNameTeam;
    private EditText editDescription;
    private Button btnSave;
    private ImageView editImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        mTeam = getIntent().getParcelableExtra("team");
        editNameTeam = (EditText) findViewById(R.id.editNameTeam);
        editDescription = (EditText) findViewById(R.id.editDescription);
        editImageView = (ImageView) findViewById(R.id.editImageView);

        editNameTeam.setText(mTeam.getName());
        editDescription.setText(mTeam.getDescription());
        editImageView.setImageResource(mTeam.getImageLogo());

    }

    @Override
    public void onClick(View v) {
        Snackbar.make(v, "Guardado", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();


    }
}
