package com.rebecasarai.room.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by rsgonzalez on 10/01/18.
 */
@Entity(foreignKeys = @ForeignKey(entity = Team.class,
                                    parentColumns = "idTeam",
                                    childColumns = "idTeam"), tableName = "players")
public class Player {
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
}
