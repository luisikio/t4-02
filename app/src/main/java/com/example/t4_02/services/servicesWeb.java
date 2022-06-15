package com.example.t4_02.services;

import com.example.t4_02.entities.Peliculas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface servicesWeb {


    @GET("Peliculas/")
    Call<List<Peliculas>> getContacts();

    @GET("Peliculas/{id}")
    Call<Peliculas> findContact(@Path("id") int id);

    @POST("Peliculas")
    Call<Peliculas> create(@Body Peliculas peliculas);
    @DELETE("Peliculas/{id}")
    Call<Peliculas> delete(@Path("id") int id);
}
