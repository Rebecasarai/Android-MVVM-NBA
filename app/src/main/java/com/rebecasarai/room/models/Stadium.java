package com.rebecasarai.room.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rsgonzalez on 10/01/18.
 */

@Entity(tableName = "stadiums")
public class Stadium implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int idStadium;
    private String name;
    private String adress;
    private String info;

    public Stadium(int idStadium, String name, String adress, String info) {
        this.idStadium = idStadium;
        this.name = name;
        this.adress = adress;
        this.info = info;
    }

    public int getIdStadium() {
        return idStadium;
    }

    public void setIdStadium(int idStadium) {
        this.idStadium = idStadium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static Creator<Stadium> getCREATOR() {
        return CREATOR;
    }

    protected Stadium(Parcel in) {
        idStadium = in.readInt();
        name = in.readString();
        adress = in.readString();
        info = in.readString();
    }

    public static final Creator<Stadium> CREATOR = new Creator<Stadium>() {
        @Override
        public Stadium createFromParcel(Parcel in) {
            return new Stadium(in);
        }

        @Override
        public Stadium[] newArray(int size) {
            return new Stadium[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idStadium);
        dest.writeString(name);
        dest.writeString(adress);
        dest.writeString(info);
    }
}
