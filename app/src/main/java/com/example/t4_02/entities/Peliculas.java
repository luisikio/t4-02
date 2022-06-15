package com.example.t4_02.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "peliculas")
public class Peliculas {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "titulo")
    public String titulo;
    @ColumnInfo(name = "sinopsis")
    public String sinopsis;
    @ColumnInfo(name = "url")
    public String url;

//    public int id;
//    public String titulo;
//    public String sinopsis;
//    public String url;


    public Peliculas() {
    }

    public Peliculas(int id, String titulo, String sinopsis, String url) {
        this.id = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.url = url;
    }
}
