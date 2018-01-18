package com.rebecasarai.room.Views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.rebecasarai.room.R;
import com.rebecasarai.room.models.Team;

import java.util.List;

/**
 * Created by rebecagonzalez on 18/1/18.
 */

public class SimpleTeamAdapter extends ArrayAdapter<Team> {

    Team team;
    private List<Team> teams;

    public SimpleTeamAdapter(Context context, List<Team> teams) {
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
        SimpleViewHolder holder;

        // Get the data item for this position
        team = (Team) getItem(position);
        if (row == null) {
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.simple_team_row, parent, false);
            holder = new SimpleViewHolder(row, R.id.firstLine, R.id.secondLine, R.id.third, R.id.icon);

            row.setTag(holder);
        } else {
            holder = (SimpleViewHolder) row.getTag();
        }
        holder.getNombre().setText(team.getName());
        holder.getApellido().setText(team.getDescription());
        holder.getCargo().setText("" + team.getIdTeam());
        holder.getImg().setImageResource(team.getImageLogo());

        return (row);
    }
}