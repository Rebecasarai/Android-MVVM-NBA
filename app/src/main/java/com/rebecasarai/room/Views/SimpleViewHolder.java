package com.rebecasarai.room.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rebecagonzalez on 18/1/18.
 */

class SimpleViewHolder {
    TextView nombre, apellido, cargo;

    ImageView img, delete, edit;

    public SimpleViewHolder(View row, int jnombre, int japellido, int jcargo, int jugadorImg) {
        this.nombre = (TextView) row.findViewById(jnombre);
        this.apellido = (TextView) row.findViewById(japellido);
        this.cargo = (TextView) row.findViewById(jcargo);
        this.img = (ImageView) row.findViewById(jugadorImg);
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
