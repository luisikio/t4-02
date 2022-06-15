package com.example.t4_02.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.t4_02.entities.Peliculas;

import java.util.List;

@Dao
public interface PeliculaDao {
    @Query("SELECT * FROM peliculas")
    List<Peliculas> getAll();

    @Query("SELECT * FROM peliculas WHERE id=:id")
    Peliculas find(int id);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void create(Peliculas pelicula);
}
