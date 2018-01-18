package com.rebecasarai.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.*;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.rebecasarai.room.DAO.PlayerDao;
import com.rebecasarai.room.DAO.StadiumDao;
import com.rebecasarai.room.DAO.TeamDao;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

/**
 * Created by rsgonzalez on 10/01/18.
 */

@Database(entities = {Team.class, Stadium.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract TeamDao teamDao();
    public abstract StadiumDao stadiumDao();


    public static AppDatabase getAppDatabase(Context context) {

        RoomDatabase.Callback populate = new RoomDatabase.Callback(){
            public void onCreate (SupportSQLiteDatabase db){
                //at first time running
               /* String sqlCreateTable = "CREATE TABLE stadiums" +
                "(" +
                "idStadium INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "adress TEXT, " +
                "info TEXT)";
                db.execSQL(sqlCreateTable);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "United Center");
                contentValues.put("adress", "41.8817328,-87.6742026");
                contentValues.put("info", "Good stadium");
                db.insert("categories", OnConflictStrategy.IGNORE, contentValues);
                Log.d("db create ","table created when db is empty and app running for the first time");*/
            }
            public void onOpen (SupportSQLiteDatabase db){
                //when is normally running
                Stadium s = new Stadium("United Center", "", "Good team");
//              INSTANCE.stadiumDao().insertStadium(s);
                /*
                ContentValues contentValues = new ContentValues();
                db.insert("dbusage", OnConflictStrategy.IGNORE, contentValues);
                Log.d("db open ","adding db open date record");*/
            }
        };

        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            //TODO: remove this:
                            .allowMainThreadQueries()
                            .addCallback(populate)
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
