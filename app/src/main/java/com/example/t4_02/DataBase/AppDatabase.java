package com.example.t4_02.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.t4_02.dao.PeliculaDao;
import com.example.t4_02.entities.Peliculas;

@Database(entities = {Peliculas.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PeliculaDao userDao();

    public static AppDatabase getDatabase(Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "com.example.t4_01.DataBase")
                .allowMainThreadQueries()
                .build();
    }
}
