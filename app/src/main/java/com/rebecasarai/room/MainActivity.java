package com.rebecasarai.room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
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

import com.rebecasarai.room.ViewModels.MainActivityVM;
import com.rebecasarai.room.Views.TeamAdaptera;
import com.rebecasarai.room.models.Team;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mList;
    private List<Team> mTeams;
    private TextView mText;
    private Intent i;
    MainActivityVM mViewModel;
    TeamAdaptera a;
    int position;
    int index;
    Team team;


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

                for (int i = 0; i< teams.size(); i++){
                    Log.v("Team: ",teams.get(i).getName());
                }
                a = new TeamAdaptera(getApplication(),MainActivity.this,teams);
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

        contextMenu.setHeaderTitle("Opciones de "+position);
        contextMenu.add(0, R.id.edit, 0, R.string.edit);
        contextMenu.add(0, R.id.delete, 1, R.string.delete);

        /*Funciona super bien, sencillo. Solo que probaré de la otra forma: Programatica
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, contextMenu);*/
    }

    //Metodo que del item seleccionado del menu context
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        index = info.position;

        //find out which menu item was pressed
        switch (item.getItemId()) {
            case R.id.edit:
                Toast.makeText(getApplicationContext(), ""+index,
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.delete:
                TextView txt = (TextView) findViewById(R.id.third);
                int num = Integer.parseInt((String) txt.getText());
                Toast.makeText(getApplicationContext(), "Delete "+ num +" index " + index,
                        Toast.LENGTH_LONG).show();
                return true;

            case R.id.help:
                Toast.makeText(getApplicationContext(), "This is help!",
                        Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
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
                i= new Intent(this, AddTeamActivity.class);
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

    public void showHelp(){

    }

    public class TeamAdapter extends ArrayAdapter<Team> {
        private List<Team> teams;

        public TeamAdapter(Context context, List<Team> teams) {
            super(context, 0, teams);
            this.teams = teams;
        }


        @Override
        public Team getItem(int pos) {
            return teams.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return teams.get(pos).getIdTeam();
            //just return 0 if your list items do not have an Id variable.
        }

        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;

            // Get the data item for this position
            team = (Team) getItem(position);
            if(row == null){
                LayoutInflater inflater = getLayoutInflater();
                    row= inflater.inflate(R.layout.team_row, parent, false);
                    holder = new ViewHolder( row, R.id.firstLine,R.id.secondLine, R.id.third, R.id.icon, R.id.deleteImage, R.id.editImage);

                row.setTag(holder);
            }
            else{
                holder = (ViewHolder) row.getTag();
            }
            holder.getNombre().setText(team.getName());
            holder.getApellido().setText(team.getDescription());
            holder.getCargo().setText(""+team.getIdTeam());
            holder.getImg().setImageResource(team.getImageLogo());
            holder.getDelete().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Team mTeam = teams.get(position);

                    Toast.makeText(getApplicationContext(), "Delete "+mTeam.getIdTeam(),
                            Toast.LENGTH_LONG).show();

                    mViewModel.deleteByID(mTeam.getIdTeam());

                }
            });

            holder.getEdit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Team mTeam = teams.get(position);

                    Toast.makeText(getApplicationContext(), "Edit "+mTeam.getIdTeam(),
                            Toast.LENGTH_LONG).show();

                    i= new Intent(getApplicationContext(), EditTeamActivity.class);
                    i.putExtra("team",mTeam);
                    startActivity(i);

                }
            });

            return (row);
        }
    }

    class ViewHolder {
        TextView nombre, apellido, cargo;

        ImageView img, delete, edit;

        public ViewHolder(View row, int jnombre, int japellido, int jcargo, int jugadorImg, int delete, int edit) {
            this.nombre = (TextView) row.findViewById(jnombre);
            this.apellido = (TextView) row.findViewById(japellido);
            this.cargo = (TextView) row.findViewById(jcargo);
            this.img = (ImageView) row.findViewById(jugadorImg);
            this.delete = (ImageView) row.findViewById(delete);
            this.edit = (ImageView) row.findViewById(edit);
        }

        public ImageView getEdit() {
            return edit;
        }

        public void setEdit(ImageView edit) {
            this.edit = edit;
        }

        public ImageView getDelete() {
            return delete;
        }

        public void setDelete(ImageView delete) {
            this.delete = delete;
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


