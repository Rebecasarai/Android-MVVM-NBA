package com.rebecasarai.room.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by rsgonzalez on 10/01/18.
 */
@Entity(foreignKeys = @ForeignKey(entity = Team.class,
                                    parentColumns = "idTeam",
                                    childColumns = "idTeam")
)
public class Player {
    @PrimaryKey(autoGenerate = true)
    private int idPlayer;
    private String namePlayer;
    private String directionPlayer;
    private String descriptionPlayer;
    private int imagePlayer;

    private int idTeam;

}
