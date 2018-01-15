package com.rebecasarai.room.Views;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebecasarai.room.R;
import com.rebecasarai.room.models.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsgonzalez on 10/01/18.
 */
public class TeamAdaptera extends ArrayAdapter<Team> {
    public TeamAdaptera(Context context, List<Team> teams) {
        super(context, 0, teams);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ViewHoldera holder;

        // Get the data item for this position
        Team team = (Team) getItem(position);

        if(row == null){
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            //if(getItemViewType(position) == 0){
            row= inflater.inflate(R.layout.team_row, parent, false);
            holder = new ViewHoldera( row, R.id.firstLine,R.id.secondLine, R.id.third, R.id.icon);
                /*}else{
                    row= inflater.inflate(R.layout.row, parent, false);
                    holder = new ViewHolder (row, R.id.jnombre2, R.id.japellido2, R.id.jcargo2, R.id.jedad2, R.id.jugadorImg2);
                    //holder.getImg().setImageResource(jugadores.get(position).getImagen());
                }*/
            row.setTag(holder);
        }
        else{
            holder = (ViewHoldera) row.getTag();
        }
        holder.getNombre().setText(""+team.getName());
        holder.getApellido().setText(""+team.getDescription());
        holder.getCargo().setText(""+team.getIdTeam());
        holder.getImg().setImageResource(team.getImageLogo());

        return (row);
    }
}

