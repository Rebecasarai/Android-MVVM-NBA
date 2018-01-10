package com.rebecasarai.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.*;
import android.content.Context;
import android.support.annotation.NonNull;

import com.rebecasarai.room.DAO.TeamDao;
import com.rebecasarai.room.models.Team;

/**
 * Created by rsgonzalez on 10/01/18.
 */

@Database(entities = {Team.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TeamDao teamDao();

    private static AppDatabase INSTANCE;
    /**
    Método que crea la base de datos
     */
    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                            /*
                            * Primero se asegura de que no exite o no se ha creado la Base de Datos.
                            * Al llamar este metodo, se puede cargar datos de forma predefinitda
                            * Añadir callback como buena practica
                            * */
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    Team team = new Team();
                                    team.setName("a");
                                    team.setDescription("a");
                                    team.setImageLogo(R.drawable.ic_launcher_background);
                                    team.setIdStadium(1);

                                    super.onCreate(db);
                                }
                            })
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }



    private static Team addTeam(final AppDatabase db, final String name,
                                final String description, final int imgTeam, final int idStadium ) {
        Team team = new Team();
        team.setName(name);
        team.setDescription(description);
        team.setImageLogo(imgTeam);
        team.setIdStadium(idStadium);
        db.teamDao().insertTeam(team);
        return team;
    }


    public static void rellenarSync(@NonNull final AppDatabase db) {
        //rellenarWithPreloadedData(db);
    }

    private static void rellenarWithPreloadedData() {

        //Team team1 = addTeam(db,"ChicagoBulls","Buen equipo", R.drawable.ic_launcher_background,1);

    }

}

