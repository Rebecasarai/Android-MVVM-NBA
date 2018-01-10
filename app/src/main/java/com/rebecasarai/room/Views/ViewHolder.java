package com.rebecasarai.room.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rsgonzalez on 10/01/18.
 */

public
class ViewHolder {
    TextView name, description, idStadium;
    ImageView img;

    public ViewHolder(View row, int jname, int jdescription, int jidStadium, int jugadorImg) {
        this.name = (TextView) row.findViewById(jname);
        this.description = (TextView) row.findViewById(jdescription);
        this.idStadium = (TextView) row.findViewById(jidStadium);
        this.img = (ImageView) row.findViewById(jugadorImg);
    }

    public TextView getname() {
        return name;
    }

    public void setname(TextView name) {
        this.name = name;
    }

    public TextView getdescription() {
        return description;
    }

    public void setdescription(TextView description) {
        this.description = description;
    }

    public TextView getidStadium() {
        return idStadium;
    }

    public void setidStadium(TextView idStadium) {
        this.idStadium = idStadium;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public ImageView getImg (){
        return this.img;
    }
}


