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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rebecasarai.room.ViewModels.MainActivityVM;
import com.rebecasarai.room.models.Team;

import java.util.List;

public class AddTeamActivity extends AppCompatActivity implements View.OnClickListener{

    private AppDatabase mAppDb;
    private ListView mList;
    private Intent i;
    private MainActivityVM mViewModel;
    private TeamAdaptere a;
    private EditText mEditNameTeam;
    private Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addteam_activity);

        // Get a reference to the ViewModel
        mViewModel = ViewModelProviders.of(this).get(MainActivityVM.class);

        //mAppDb = AppDatabase.getAppDatabase(getApplicationContext());
        //mTeams = mAppDb.teamDao().getAll();
        mList = findViewById(R.id.listViewActual);
        mEditNameTeam = findViewById(R.id.editNameTeam);
        mBtnSave = findViewById(R.id.btnSave);

        mAppDb = AppDatabase.getAppDatabase(this.getApplication());

        mViewModel = ViewModelProviders.of(this).get(MainActivityVM.class);
        mViewModel.getmTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@NonNull final List<Team> mTeams) {

                for (int i = 0; i< mTeams.size(); i++){
                    Log.v("Team: ",mTeams.get(i).getName());
                }

                a = new TeamAdaptere(getApplicationContext(),mTeams);
                mList.setAdapter(a);
            }
        });

        //Registramos la lista, la view que recibirá luego el Context Menu
        registerForContextMenu(mList);

        mBtnSave.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:

            break;
        }
    }
    public class TeamAdaptere extends ArrayAdapter<Team> {
        public TeamAdaptere(Context context, List<Team> teams) {
            super(context, 0, teams);
        }


        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            ViewHoldere holder;

            // Get the data item for this position
            Team team = (Team) getItem(position);

            if(row == null){
                LayoutInflater inflater = getLayoutInflater();
                //if(getItemViewType(position) == 0){
                row= inflater.inflate(R.layout.team_row, parent, false);
                holder = new ViewHoldere( row, R.id.firstLine,R.id.secondLine, R.id.third, R.id.icon);
                /*}else{
                    row= inflater.inflate(R.layout.row, parent, false);
                    holder = new ViewHolder (row, R.id.jnombre2, R.id.japellido2, R.id.jcargo2, R.id.jedad2, R.id.jugadorImg2);
                    //holder.getImg().setImageResource(jugadores.get(position).getImagen());
                }*/
                row.setTag(holder);
            }
            else{
                holder = (ViewHoldere) row.getTag();
            }
            holder.getNombre().setText(""+team.getName());
            holder.getApellido().setText(""+team.getDescription());
            holder.getCargo().setText(""+team.getIdTeam());
            holder.getImg().setImageResource(team.getImageLogo());

            return (row);
        }
    }

    class ViewHoldere {
        TextView nombre, apellido, cargo;
        ImageView img;

        public ViewHoldere(View row, int jnombre, int japellido, int jcargo, int jugadorImg) {
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


