package com.rebecasarai.room.Views;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rebecasarai.room.EditTeamActivity;
import com.rebecasarai.room.MainActivity;
import com.rebecasarai.room.R;
import com.rebecasarai.room.ViewModels.MainActivityVM;
import com.rebecasarai.room.models.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsgonzalez on 10/01/18.
 */


public class TeamAdaptera extends ArrayAdapter<Team> {
    private List<Team> teams;
    Team team;
    MainActivityVM mViewModel;
    private Intent i;

    public TeamAdaptera(Context context, FragmentActivity activity, List<Team> teams) {
        super(context, 0, teams);
        this.teams = teams;
        mViewModel = ViewModelProviders.of(activity).get(MainActivityVM.class);
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
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
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

                Toast.makeText(getContext(), "Delete "+mTeam.getIdTeam(),
                        Toast.LENGTH_LONG).show();

                mViewModel.deleteByID(mTeam.getIdTeam());

            }
        });

        holder.getEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Team mTeam = teams.get(position);

                Toast.makeText(getContext(), "Edit "+mTeam.getIdTeam(),
                        Toast.LENGTH_LONG).show();

                i= new Intent(getContext(), EditTeamActivity.class);
                i.putExtra("team",mTeam);
                getContext().startActivity(i);

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




