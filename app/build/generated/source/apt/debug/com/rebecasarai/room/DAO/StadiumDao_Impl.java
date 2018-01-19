package com.rebecasarai.room.DAO;

import android.arch.lifecycle.ComputableLiveData;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.InvalidationTracker.Observer;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import android.support.annotation.NonNull;
import com.rebecasarai.room.models.Stadium;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StadiumDao_Impl implements StadiumDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStadium;

  private final EntityInsertionAdapter __insertionAdapterOfStadium_1;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfStadium;

  public StadiumDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStadium = new EntityInsertionAdapter<Stadium>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `stadiums`(`idStadium`,`name`,`adress`,`info`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Stadium value) {
        stmt.bindLong(1, value.getIdStadium());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getAdress() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAdress());
        }
        if (value.getInfo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getInfo());
        }
      }
    };
    this.__insertionAdapterOfStadium_1 = new EntityInsertionAdapter<Stadium>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `stadiums`(`idStadium`,`name`,`adress`,`info`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Stadium value) {
        stmt.bindLong(1, value.getIdStadium());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getAdress() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAdress());
        }
        if (value.getInfo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getInfo());
        }
      }
    };
    this.__deletionAdapterOfStadium = new EntityDeletionOrUpdateAdapter<Stadium>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `stadiums` WHERE `idStadium` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Stadium value) {
        stmt.bindLong(1, value.getIdStadium());
      }
    };
  }

  @Override
  public void insertAll(Stadium... stadiums) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStadium.insert(stadiums);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertStadium(Stadium stadium) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStadium_1.insert(stadium);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Stadium stadium) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfStadium.handle(stadium);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Stadium getStadium(int id) {
    final String _sql = "select * from stadiums where idStadium = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIdStadium = _cursor.getColumnIndexOrThrow("idStadium");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfAdress = _cursor.getColumnIndexOrThrow("adress");
      final int _cursorIndexOfInfo = _cursor.getColumnIndexOrThrow("info");
      final Stadium _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpAdress;
        _tmpAdress = _cursor.getString(_cursorIndexOfAdress);
        final String _tmpInfo;
        _tmpInfo = _cursor.getString(_cursorIndexOfInfo);
        _result = new Stadium(_tmpName,_tmpAdress,_tmpInfo);
        final int _tmpIdStadium;
        _tmpIdStadium = _cursor.getInt(_cursorIndexOfIdStadium);
        _result.setIdStadium(_tmpIdStadium);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<Stadium> getStadiumLive(int id) {
    final String _sql = "select * from stadiums where idStadium = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<Stadium>() {
      private Observer _observer;

      @Override
      protected Stadium compute() {
        if (_observer == null) {
          _observer = new Observer("stadiums") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfIdStadium = _cursor.getColumnIndexOrThrow("idStadium");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfAdress = _cursor.getColumnIndexOrThrow("adress");
          final int _cursorIndexOfInfo = _cursor.getColumnIndexOrThrow("info");
          final Stadium _result;
          if(_cursor.moveToFirst()) {
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpAdress;
            _tmpAdress = _cursor.getString(_cursorIndexOfAdress);
            final String _tmpInfo;
            _tmpInfo = _cursor.getString(_cursorIndexOfInfo);
            _result = new Stadium(_tmpName,_tmpAdress,_tmpInfo);
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

  @Override
  public LiveData<List<Stadium>> getAllLive() {
    final String _sql = "SELECT * FROM stadiums";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Stadium>>() {
      private Observer _observer;

      @Override
      protected List<Stadium> compute() {
        if (_observer == null) {
          _observer = new Observer("stadiums") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfIdStadium = _cursor.getColumnIndexOrThrow("idStadium");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfAdress = _cursor.getColumnIndexOrThrow("adress");
          final int _cursorIndexOfInfo = _cursor.getColumnIndexOrThrow("info");
          final List<Stadium> _result = new ArrayList<Stadium>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Stadium _item;
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpAdress;
            _tmpAdress = _cursor.getString(_cursorIndexOfAdress);
            final String _tmpInfo;
            _tmpInfo = _cursor.getString(_cursorIndexOfInfo);
            _item = new Stadium(_tmpName,_tmpAdress,_tmpInfo);
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
}
