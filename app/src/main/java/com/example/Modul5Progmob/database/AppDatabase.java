package com.example.Modul5Progmob.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.Modul5Progmob.dao.KegiatanDao;
import com.example.Modul5Progmob.model.indexkegiatan.DataItem;
import com.example.Modul5Progmob.model.indexkegiatan.User;

@Database(entities = {DataItem.class, User.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract KegiatanDao kegiatanDao();
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "db-app")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }
}
