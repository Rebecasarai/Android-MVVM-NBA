package com.rebecasarai.room.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rsgonzalez on 10/01/18.
 */
@Entity(foreignKeys = @ForeignKey(entity = Team.class,
                                    parentColumns = "idTeam",
                                    childColumns = "idTeam"), tableName = "players")
public class Player implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    private int idPlayer;
    private String namePlayer;
    private String directionPlayer;
    private String descriptionPlayer;
    private int imagePlayer;

    private int idTeam;

    public Player( String namePlayer, String directionPlayer, String descriptionPlayer, int imagePlayer, int idTeam) {
        this.namePlayer = namePlayer;
        this.directionPlayer = directionPlayer;
        this.descriptionPlayer = descriptionPlayer;
        this.imagePlayer = imagePlayer;
        this.idTeam = idTeam;
    }

    protected Player(Parcel in) {
        idPlayer = in.readInt();
        namePlayer = in.readString();
        directionPlayer = in.readString();
        descriptionPlayer = in.readString();
        imagePlayer = in.readInt();
        idTeam = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getDirectionPlayer() {
        return directionPlayer;
    }

    public void setDirectionPlayer(String directionPlayer) {
        this.directionPlayer = directionPlayer;
    }

    public String getDescriptionPlayer() {
        return descriptionPlayer;
    }

    public void setDescriptionPlayer(String descriptionPlayer) {
        this.descriptionPlayer = descriptionPlayer;
    }

    public int getImagePlayer() {
        return imagePlayer;
    }

    public void setImagePlayer(int imagePlayer) {
        this.imagePlayer = imagePlayer;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPlayer);
        dest.writeString(namePlayer);
        dest.writeString(directionPlayer);
        dest.writeString(descriptionPlayer);
        dest.writeInt(imagePlayer);
        dest.writeInt(idTeam);
    }
}
