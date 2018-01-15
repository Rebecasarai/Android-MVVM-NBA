package com.rebecasarai.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.view.ContextMenu.ContextMenuInfo;

import com.rebecasarai.room.ViewModels.TeamInfoWithAllTeamsViewModel;
import com.rebecasarai.room.models.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mList;
    private TextView mText;
    private List<Team> mTeams;
    private Intent i;
    TeamInfoWithAllTeamsViewModel mViewModel;
    TeamAdapter a;
    private ArrayList<String> listItems;
    public AppDatabase mAppDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = findViewById(R.id.listview);
        mText = findViewById(R.id.title);


        //mAppDb = AppDatabase.getAppDatabase(this.getApplication());

        mViewModel = ViewModelProviders.of(this).get(TeamInfoWithAllTeamsViewModel.class);

        mViewModel.mTeams.observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@NonNull final List<Team> mTeams) {

                mText.setText(mTeams.get(0).getName());

                for (int i = 0; i< mTeams.size(); i++){
                    Log.v("Team: ",mTeams.get(i).getName());
                }

                a = new TeamAdapter(getApplicationContext(),mTeams);
                mList.setAdapter(a);
            }
        });

        //Registramos la lista, la view que recibirá luego el Context Menu
        registerForContextMenu(mList);
        mList.setOnItemClickListener(this);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        //find out which menu item was pressed
        switch (item.getItemId()) {
            case R.id.edit:
                Toast.makeText(getApplicationContext(), "This is edit!",
                        Toast.LENGTH_LONG).show();

                return true;

            case R.id.delete:
                Toast.makeText(getApplicationContext(), "This is edit!",
                        Toast.LENGTH_LONG).show();

                return true;

            case R.id.option2:
                Toast.makeText(getApplicationContext(), "This is my Toast message!",
                        Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
                i= new Intent(this, AddTeamActivity.class);
                startActivity(i);

                return true;
            case R.id.help:
                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showHelp(){

    }

    /*private class TeamAdapter<T> extends ArrayAdapter {

        public TeamAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull java.util.List objects) {
            super(context, resource, textViewResourceId, objects);
        }*/
    public class TeamAdapter extends ArrayAdapter<Team> {
        public TeamAdapter(Context context, List<Team> users) {
            super(context, 0, users);
        }


        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;

            // Get the data item for this position
            Team team = (Team) getItem(position);

            if(row == null){
                LayoutInflater inflater = getLayoutInflater();
                //if(getItemViewType(position) == 0){
                    row= inflater.inflate(R.layout.team_row, parent, false);
                    holder = new ViewHolder( row, R.id.firstLine,R.id.secondLine, R.id.third, R.id.icon);
                /*}else{
                    row= inflater.inflate(R.layout.row, parent, false);
                    holder = new ViewHolder (row, R.id.jnombre2, R.id.japellido2, R.id.jcargo2, R.id.jedad2, R.id.jugadorImg2);
                    //holder.getImg().setImageResource(jugadores.get(position).getImagen());
                }*/
                row.setTag(holder);
            }
            else{
                holder = (ViewHolder) row.getTag();
            }
            holder.getNombre().setText(""+team.getName());
            holder.getApellido().setText(""+team.getDescription());
            holder.getCargo().setText(""+team.getIdStadium());
            holder.getImg().setImageResource(team.getImageLogo());

            return (row);
        }
    }

    class ViewHolder {
        TextView nombre, apellido, cargo;
        ImageView img;

        public ViewHolder(View row, int jnombre, int japellido, int jcargo, int jugadorImg) {
            this.nombre = (TextView) row.findViewById(jnombre);
            this.apellido = (TextView) row.findViewById(japellido);
            this.cargo = (TextView) row.findViewById(jcargo);
            this.img = (ImageView) row.findViewById(jugadorImg);
        }

        public TextView getNombre() {
            return nombre;
        }

        public void setNombre(TextView nombre) {
            this.nombre = nombre;
        }

        public TextView getApellido() {
            return apellido;
        }

        public void setApellido(TextView apellido) {
            this.apellido = apellido;
        }

        public TextView getCargo() {
            return cargo;
        }

        public void setCargo(TextView cargo) {
            this.cargo = cargo;
        }


        public void setImg(ImageView img) {
            this.img = img;
        }

        public ImageView getImg (){
            return this.img;
        }
    }


}


