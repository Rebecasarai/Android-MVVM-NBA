package com.rebecasarai.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.rebecasarai.room.DAO.StadiumDao;
import com.rebecasarai.room.DAO.TeamDao;
import com.rebecasarai.room.models.Stadium;
import com.rebecasarai.room.models.Team;

import java.util.concurrent.Executors;

import static android.webkit.WebViewDatabase.getInstance;

/**
 * Created by rsgonzalez on 10/01/18.
 */

@Database(entities = {Team.class, Stadium.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract TeamDao teamDao();
    public abstract  StadiumDao stadiumDao();

    public synchronized static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    /**
     * Contruye la base de datos, pre cargando datos en la base de datos, cuando carga por primera vez
     * @param context
     * @return
     */
    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "nba-database")
                //TODO: remove this when lo haga todo asincrono, ya vereís:
                .allowMainThreadQueries()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        //Cuando se crea por primera vez la database
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getAppDatabase(context).stadiumDao().insertAll(Stadium.insertarStadiums());
                            }
                        });
                    }
                })
                .build();
    }

}