package com.rebecasarai.room;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.rebecasarai.room.models.Team;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView mList;
    private AppDatabase mAppDb;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = findViewById(R.id.listview);
        mText = findViewById(R.id.text);
        mAppDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        mAppDb.teamDao().insertTeam(new Team(1, "Chicago Bulls", "Buen equipo", R.drawable.ic_launcher_background,1));

        Log.v("team: ", mAppDb.teamDao().getAll().toString());


    }


    private void instaTeams() {
        //DataBaseInitializer.rellenarSync(mAppDb);

        }
}