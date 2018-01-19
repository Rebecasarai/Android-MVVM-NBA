package com.rebecasarai.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.rebecasarai.room.DAO.StadiumDao;
import com.rebecasarai.room.DAO.StadiumDao_Impl;
import com.rebecasarai.room.DAO.TeamDao;
import com.rebecasarai.room.DAO.TeamDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class AppDatabase_Impl extends AppDatabase {
  private volatile TeamDao _teamDao;

  private volatile StadiumDao _stadiumDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `teams` (`idTeam` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `description` TEXT, `imageLogo` INTEGER NOT NULL, `idStadium` INTEGER NOT NULL, FOREIGN KEY(`idStadium`) REFERENCES `stadiums`(`idStadium`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `stadiums` (`idStadium` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `adress` TEXT, `info` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"b2c07eb35727fdddc9aaab2c16505c6d\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `teams`");
        _db.execSQL("DROP TABLE IF EXISTS `stadiums`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTeams = new HashMap<String, TableInfo.Column>(5);
        _columnsTeams.put("idTeam", new TableInfo.Column("idTeam", "INTEGER", true, 1));
        _columnsTeams.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsTeams.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsTeams.put("imageLogo", new TableInfo.Column("imageLogo", "INTEGER", true, 0));
        _columnsTeams.put("idStadium", new TableInfo.Column("idStadium", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTeams = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysTeams.add(new TableInfo.ForeignKey("stadiums", "NO ACTION", "NO ACTION",Arrays.asList("idStadium"), Arrays.asList("idStadium")));
        final HashSet<TableInfo.Index> _indicesTeams = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTeams = new TableInfo("teams", _columnsTeams, _foreignKeysTeams, _indicesTeams);
        final TableInfo _existingTeams = TableInfo.read(_db, "teams");
        if (! _infoTeams.equals(_existingTeams)) {
          throw new IllegalStateException("Migration didn't properly handle teams(com.rebecasarai.room.models.Team).\n"
                  + " Expected:\n" + _infoTeams + "\n"
                  + " Found:\n" + _existingTeams);
        }
        final HashMap<String, TableInfo.Column> _columnsStadiums = new HashMap<String, TableInfo.Column>(4);
        _columnsStadiums.put("idStadium", new TableInfo.Column("idStadium", "INTEGER", true, 1));
        _columnsStadiums.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsStadiums.put("adress", new TableInfo.Column("adress", "TEXT", false, 0));
        _columnsStadiums.put("info", new TableInfo.Column("info", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStadiums = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStadiums = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStadiums = new TableInfo("stadiums", _columnsStadiums, _foreignKeysStadiums, _indicesStadiums);
        final TableInfo _existingStadiums = TableInfo.read(_db, "stadiums");
        if (! _infoStadiums.equals(_existingStadiums)) {
          throw new IllegalStateException("Migration didn't properly handle stadiums(com.rebecasarai.room.models.Stadium).\n"
                  + " Expected:\n" + _infoStadiums + "\n"
                  + " Found:\n" + _existingStadiums);
        }
      }
    }, "b2c07eb35727fdddc9aaab2c16505c6d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "teams","stadiums");
  }

  @Override
  public TeamDao teamDao() {
    if (_teamDao != null) {
      return _teamDao;
    } else {
      synchronized(this) {
        if(_teamDao == null) {
          _teamDao = new TeamDao_Impl(this);
        }
        return _teamDao;
      }
    }
  }

  @Override
  public StadiumDao stadiumDao() {
    if (_stadiumDao != null) {
      return _stadiumDao;
    } else {
      synchronized(this) {
        if(_stadiumDao == null) {
          _stadiumDao = new StadiumDao_Impl(this);
        }
        return _stadiumDao;
      }
    }
  }
}
