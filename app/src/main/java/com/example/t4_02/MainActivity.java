package com.example.t4_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.t4_02.DataBase.AppDatabase;
import com.example.t4_02.adapters.peliculaAdapter;
import com.example.t4_02.dao.PeliculaDao;
import com.example.t4_02.entities.Peliculas;
import com.example.t4_02.services.servicesWeb;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public RecyclerView rv;
    FloatingActionButton btn;
    List<Peliculas> peliculas= new ArrayList<>();
   AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getDatabase(getApplicationContext());

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sincronizar(peliculas);

        servicesWeb services = retrofit.create(servicesWeb.class);
        Call<List<Peliculas>> call=services.getContacts();

        call.enqueue(new Callback<List<Peliculas>>() {
            @Override
            public void onResponse(Call<List<Peliculas>> call, Response<List<Peliculas>> response) {
                if (!response.isSuccessful()){
                    Log.e("asd1234", "error");
                }else{
                    Log.i("asdasd12312", new Gson().toJson(response.body()));
                    Log.i("asd32", "Respuesta correcta");

                    peliculas=response.body();

                    saveInDatabase(peliculas);

                    peliculaAdapter adapter=new peliculaAdapter(peliculas);


                    rv= findViewById(R.id.rvPelicula);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Peliculas>> call, Throwable t) {
                Log.e("asd1234", "no hay conexion");
            }
        });

        btn=findViewById(R.id.btnCrear);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this,crear_pelicula.class);
               startActivity(intent);
           }
       });
    }
    private void saveInDatabase(List<Peliculas> peliculas) {
        PeliculaDao dao = db.userDao();
        for (Peliculas pelicula : peliculas) {
            dao.create(pelicula);
        }
    }
    private void sincronizar(List<Peliculas> peliculas) {
        PeliculaDao dao = db.userDao();
        List<Peliculas> peliculaa = dao.getAll();
        Log.i("holamundo", new Gson().toJson(peliculaa));
    }
}