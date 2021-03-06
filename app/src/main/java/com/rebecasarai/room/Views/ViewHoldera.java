package com.rebecasarai.room.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rsgonzalez on 10/01/18.
 */

class ViewHoldera {
    TextView nombre, apellido, cargo;

    ImageView img, delete, edit;

    public ViewHoldera(View row, int jnombre, int japellido, int jcargo, int jugadorImg, int delete, int edit) {
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





