package com.rebecasarai.room.models;

import android.arch.persistence.room.*;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by rsgonzalez on 8/1/17.
 */

@Entity(foreignKeys = @ForeignKey(entity = Stadium.class,
        parentColumns = "idStadium",
        childColumns = "idStadium"), tableName = "teams")
public class Team  implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int idTeam;
    private String name;
    private String description;
    private int imageLogo;
    private int idStadium;

    public Team(String name, String description, int imageLogo, int idStadium) {
        this.name = name;
        this.description = description;
        this.imageLogo = imageLogo;
        this.idStadium = idStadium;
    }

    public Team() {

    }

    protected Team(Parcel in) {
        idTeam = in.readInt();
        name = in.readString();
        description = in.readString();
        imageLogo = in.readInt();
        idStadium = in.readInt();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idTeam);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(imageLogo);
        dest.writeInt(idStadium);
    }
}
