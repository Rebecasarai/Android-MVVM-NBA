package com.rebecasarai.room.DAO;

import android.arch.persistence.room.RoomDatabase;

public class PlayerDao_Impl implements PlayerDao {
  private final RoomDatabase __db;

  public PlayerDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }
}
