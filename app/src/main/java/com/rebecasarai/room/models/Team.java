package com.rebecasarai.room.models;

import android.arch.persistence.room.*;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by rsgonzalez on 8/1/17.
 */

@Entity(tableName = "teams")
public class Team  {
    @PrimaryKey(autoGenerate = true)
    private int idTeam;
    private String name;
    private String description;
    private int imageLogo;
    private int idStadium;

    public Team( int idTeam, String name, String description, int imageLogo, int idStadium) {
        this.idTeam = idTeam;
        this.name = name;
        this.description = description;
        this.imageLogo = imageLogo;
        this.idStadium = idStadium;
    }

    public Team() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(int imageLogo) {
        this.imageLogo = imageLogo;
    }

    public int getIdStadium() {
        return idStadium;
    }

    public void setIdStadium(int idStadium) {
        this.idStadium = idStadium;
    }
}
