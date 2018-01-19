package com.rebecasarai.room.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebecasarai.room.AppDatabase;
import com.rebecasarai.room.R;
import com.rebecasarai.room.ViewModels.MainActivityVM;
import com.rebecasarai.room.Views.TeamAdaptera;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mList;
    //private List<Team> mTeams;
    private TextView mText;
    private Intent i;
    MainActivityVM mViewModel;
    TeamAdaptera a;
    int position;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = findViewById(R.id.listview);
        mText = findViewById(R.id.title);

        mViewModel = ViewModelProviders.of(this).get(MainActivityVM.class);

        mViewModel.getmTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@NonNull final List<Team> teams) {

                //mTeams = teams;
                for (int i = 0; i < teams.size(); i++) {
                    Log.v("Team: ", teams.get(i).toString());
                }
                a = new TeamAdaptera(getApplicationContext(), mViewModel, teams);
                //TODO: Set contador para solo crear TeamAdapter una vez
                mList.setAdapter(a);
            }
        });

        //Registramos la lista, la view que recibirá luego el Context Menu
        registerForContextMenu(mList);
        mList.setOnItemClickListener(this);
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(contextMenu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        position = info.position;

        contextMenu.setHeaderTitle("Opciones de " + position);
        contextMenu.add(0, R.id.edit, 0, R.string.edit);
        contextMenu.add(0, R.id.delete, 1, R.string.delete);
        contextMenu.add(0, R.id.map, 1, R.string.map);
    }


    //Metodo que del item seleccionado del menu context
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        index = info.position;

        //find out which menu item was pressed
        switch (item.getItemId()) {
            case R.id.edit:
                Toast.makeText(getApplicationContext(), "" + index,
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.delete:
                TextView txt = (TextView) findViewById(R.id.third);
                int num = Integer.parseInt((String) txt.getText());
                //AppDatabase.getAppDatabase(this).teamDao().delete((Team) mList.getAdapter().getItem(position));
                mViewModel.deleteTeam((Team) mList.getAdapter().getItem(position));
                Toast.makeText(getApplicationContext(), "Delete " + num + " index " + index,
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.map:
                    openMap(((Team) mList.getAdapter().getItem(position)).getIdStadium());
                return true;

            case R.id.help:
                Toast.makeText(getApplicationContext(), "This is help!",
                        Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }

    /**
     * Abre el mapa para ver el stadium
     * @param idStadium
     */
    public void openMap(int idStadium){

        Stadium stadium = mViewModel.getStadium(idStadium);

        Uri gmmIntentUri = Uri.parse("google.streetview:cbll="+stadium.getAdress());

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        mapIntent.setPackage("com.google.android.apps.maps");

        startActivity(mapIntent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_team:
                i = new Intent(this, AddTeamActivity.class);
                startActivity(i);
                return true;

            case R.id.add4:
                mViewModel.insertTeams();
                return true;

            case R.id.help:
                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showHelp() {

    }
}