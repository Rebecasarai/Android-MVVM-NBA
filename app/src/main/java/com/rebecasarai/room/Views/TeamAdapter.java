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

public class TeamAdapter<T> extends ArrayAdapter {

    ArrayList<Team> teams;
    public TeamAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row= inflater.inflate(R.layout.team_row, parent, false);
                holder = new ViewHolder( row, R.id.firstLine,R.id.secondLine, R.id.third, R.id.icon);

            row.setTag(holder);
        }
        else{
            holder = (ViewHolder) row.getTag();
        }
        holder.getname().setText(""+teams.get(position).getName());
        holder.getImg().setImageResource(teams.get(position).getImageLogo());
        holder.getdescription().setText(""+teams.get(position).getDescription());
        holder.getidStadium().setText(""+teams.get(position).getIdStadium());
        return (row);
    }
}