package com.rebecasarai.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;

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

        RoomDatabase.Callback populateDatabase = new RoomDatabase.Callback() {
            public void onCreate(SupportSQLiteDatabase db) {
                //at first time running
            }

            public void onOpen(SupportSQLiteDatabase db) {
                //when is normally running
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "United Center");
                contentValues.put("adress", "41.8817328,-87.6742026");
                contentValues.put("info", "Good stadium");
                db.insert("stadiums", OnConflictStrategy.IGNORE, contentValues);
            }
        };

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                    //TODO: remove this:
                    .allowMainThreadQueries()
                    .addCallback(populateDatabase)
                    .build();
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }
}
