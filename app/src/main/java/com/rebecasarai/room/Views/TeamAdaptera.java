package com.rebecasarai.room.Views;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.rebecasarai.room.Activities.EditTeamActivity;
import com.rebecasarai.room.R;
import com.rebecasarai.room.ViewModels.MainActivityVM;
import com.rebecasarai.room.models.Team;
import java.util.List;

/**
 * Created by rsgonzalez on 10/01/18.
 */


public class TeamAdaptera extends ArrayAdapter<Team> {
    private Context context;
    private List<Team> teams;
    Team team;
    MainActivityVM mViewModel;
    private Intent i;

    public TeamAdaptera(Context context, MainActivityVM mViewModel, List<Team> teams) {
        super(context, 0, teams);
        this.context = context;
        this.teams = teams;
        //Que el view model de este adapatador sea el de la actividad que recibe como parametro al construir:
        //mViewModel = ViewModelProviders.of(activity).get(MainActivityVM.class);
        this.mViewModel = mViewModel;
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
        ViewHoldera holder;

        // Get the data item for this position
        team = (Team) getItem(position);
        if(row == null){
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row= inflater.inflate(R.layout.team_row, parent, false);
            holder = new ViewHoldera( row, R.id.firstLine,R.id.secondLine, R.id.third, R.id.icon, R.id.deleteImage, R.id.editImage);

            row.setTag(holder);
        }
        else{
            holder = (ViewHoldera) row.getTag();
        }
        holder.getNombre().setText(team.getName());
        holder.getApellido().setText(team.getDescription());
        holder.getCargo().setText(""+team.getIdTeam());
        holder.getImg().setImageResource(team.getImageLogo());
        holder.getDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Team mTeam = teams.get(position);

                Toast.makeText(context, "Delete "+mTeam.getIdTeam(),
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

                i= new Intent(context, EditTeamActivity.class);
                i.putExtra("team",mTeam);
                //Necesario para llamar a startActivity desde una clase y no una actividad
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

        return (row);
    }
}