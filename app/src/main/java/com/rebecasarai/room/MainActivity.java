package com.rebecasarai.room;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rebecasarai.room.models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mList;
    private AppDatabase mAppDb;
    private TextView mText;
    private List<Team> mTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = findViewById(R.id.listview);
        mText = findViewById(R.id.text);
        mAppDb = AppDatabase.getAppDatabase(getApplicationContext());

        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi,1));
        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi,1));
        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi,1));
        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi,1));
        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi,1));
        mAppDb.teamDao().insertTeam(new Team("Chicago Bulls", "Buen equipo", R.drawable.chi,1));

        Log.v("teams: ", mAppDb.teamDao().getAll().toString());
        mTeams = mAppDb.teamDao().getAll();

        TeamAdapter a = new TeamAdapter<Object>(this,R.layout.team_row, R.id.firstLine, mTeams);
        mList.setAdapter(a);
        mList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    private class TeamAdapter<T> extends ArrayAdapter {

        public TeamAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull java.util.List objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            int tipo = -1;

            if(mTeams.get(position) instanceof Team){
                tipo=0;

            }else{
                tipo=1;
            }
            return tipo;
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;

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
            holder.getNombre().setText(""+mTeams.get(position).getName());
            holder.getApellido().setText(""+mTeams.get(position).getDescription());
            holder.getCargo().setText(""+mTeams.get(position).getIdStadium());
            holder.getImg().setImageResource(mTeams.get(position).getImageLogo());
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


