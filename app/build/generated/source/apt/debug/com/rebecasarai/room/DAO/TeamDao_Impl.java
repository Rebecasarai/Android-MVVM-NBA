package com.rebecasarai.room.DAO;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.arch.persistence.room.util.StringUtil;
import android.database.Cursor;
import android.support.annotation.NonNull;
import com.rebecasarai.room.models.Team;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TeamDao_Impl implements TeamDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTeam;

  private final EntityInsertionAdapter __insertionAdapterOfTeam_1;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTeam;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfTeam;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public TeamDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTeam = new EntityInsertionAdapter<Team>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `teams`(`idTeam`,`name`,`description`,`imageLogo`,`idStadium`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Team value) {
        stmt.bindLong(1, value.getIdTeam());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getImageLogo());
        stmt.bindLong(5, value.getIdStadium());
      }
    };
    this.__insertionAdapterOfTeam_1 = new EntityInsertionAdapter<Team>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `teams`(`idTeam`,`name`,`description`,`imageLogo`,`idStadium`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Team value) {
        stmt.bindLong(1, value.getIdTeam());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getImageLogo());
        stmt.bindLong(5, value.getIdStadium());
      }
    };
    this.__deletionAdapterOfTeam = new EntityDeletionOrUpdateAdapter<Team>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `teams` WHERE `idTeam` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Team value) {
        stmt.bindLong(1, value.getIdTeam());
      }
    };
    this.__updateAdapterOfTeam = new EntityDeletionOrUpdateAdapter<Team>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `teams` SET `idTeam` = ?,`name` = ?,`description` = ?,`imageLogo` = ?,`idStadium` = ? WHERE `idTeam` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Team value) {
        stmt.bindLong(1, value.getIdTeam());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        stmt.bindLong(4, value.getImageLogo());
        stmt.bindLong(5, value.getIdStadium());
        stmt.bindLong(6, value.getIdTeam());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM teams where idTeam = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM teams";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Team... teams) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTeam.insert(teams);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertTeam(Team team) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTeam_1.insert(team);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Team team) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfTeam.handle(team);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int updateTeam(Team team) {
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfTeam.handle(team);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteById(int id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteById.release(_stmt);
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Team> getAll() {
    final String _sql = "SELECT * FROM teams";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIdTeam = _cursor.getColumnIndexOrThrow("idTeam");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfImageLogo = _cursor.getColumnIndexOrThrow("imageLogo");
      final int _cursorIndexOfIdStadium = _cursor.getColumnIndexOrThrow("idStadium");
      final List<Team> _result = new ArrayList<Team>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Team _item;
        _item = new Team();
        final int _tmpIdTeam;
        _tmpIdTeam = _cursor.getInt(_cursorIndexOfIdTeam);
        _item.setIdTeam(_tmpIdTeam);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final int _tmpImageLogo;
        _tmpImageLogo = _cursor.getInt(_cursorIndexOfImageLogo);
        _item.setImageLogo(_tmpImageLogo);
        final int _tmpIdStadium;
        _tmpIdStadium = _cursor.getInt(_cursorIndexOfIdStadium);
        _item.setIdStadium(_tmpIdStadium);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Team>> getAllLive() {
    final String _sql = "SELECT * FROM teams";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Team>>() {
      private Observer _observer;

      @Override
      protected List<Team> compute() {
        if (_observer == null) {
          _observer = new Observer("teams") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfIdTeam = _cursor.getColumnIndexOrThrow("idTeam");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final int _cursorIndexOfImageLogo = _cursor.getColumnIndexOrThrow("imageLogo");
          final int _cursorIndexOfIdStadium = _cursor.getColumnIndexOrThrow("idStadium");
          final List<Team> _result = new ArrayList<Team>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Team _item;
            _item = new Team();
            final int _tmpIdTeam;
            _tmpIdTeam = _cursor.getInt(_cursorIndexOfIdTeam);
            _item.setIdTeam(_tmpIdTeam);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item.setDescription(_tmpDescription);
            final int _tmpImageLogo;
            _tmpImageLogo = _cursor.getInt(_cursorIndexOfImageLogo);
            _item.setImageLogo(_tmpImageLogo);
            final int _tmpIdStadium;
            _tmpIdStadium = _cursor.getInt(_cursorIndexOfIdStadium);
            _item.setIdStadium(_tmpIdStadium);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public List<Team> loadAllByIds(int[] teamsIds) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM teams WHERE idTeam IN (");
    final int _inputSize = teamsIds.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int _item : teamsIds) {
      _statement.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIdTeam = _cursor.getColumnIndexOrThrow("idTeam");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfImageLogo = _cursor.getColumnIndexOrThrow("imageLogo");
      final int _cursorIndexOfIdStadium = _cursor.getColumnIndexOrThrow("idStadium");
      final List<Team> _result = new ArrayList<Team>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Team _item_1;
        _item_1 = new Team();
        final int _tmpIdTeam;
        _tmpIdTeam = _cursor.getInt(_cursorIndexOfIdTeam);
        _item_1.setIdTeam(_tmpIdTeam);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item_1.setName(_tmpName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item_1.setDescription(_tmpDescription);
        final int _tmpImageLogo;
        _tmpImageLogo = _cursor.getInt(_cursorIndexOfImageLogo);
        _item_1.setImageLogo(_tmpImageLogo);
        final int _tmpIdStadium;
        _tmpIdStadium = _cursor.getInt(_cursorIndexOfIdStadium);
        _item_1.setIdStadium(_tmpIdStadium);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<Team> getTeamByIdLive(int id) {
    final String _sql = "select * from teams where idTeam = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<Team>() {
      private Observer _observer;

      @Override
      protected Team compute() {
        if (_observer == null) {
          _observer = new Observer("teams") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfIdTeam = _cursor.getColumnIndexOrThrow("idTeam");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final int _cursorIndexOfImageLogo = _cursor.getColumnIndexOrThrow("imageLogo");
          final int _cursorIndexOfIdStadium = _cursor.getColumnIndexOrThrow("idStadium");
          final Team _result;
          if(_cursor.moveToFirst()) {
            _result = new Team();
            final int _tmpIdTeam;
            _tmpIdTeam = _cursor.getInt(_cursorIndexOfIdTeam);
            _result.setIdTeam(_tmpIdTeam);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _result.setName(_tmpName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _result.setDescription(_tmpDescription);
            final int _tmpImageLogo;
            _tmpImageLogo = _cursor.getInt(_cursorIndexOfImageLogo);
            _result.setImageLogo(_tmpImageLogo);
            final int _tmpIdStadium;
            _tmpIdStadium = _cursor.getInt(_cursorIndexOfIdStadium);
            _result.setIdStadium(_tmpIdStadium);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
