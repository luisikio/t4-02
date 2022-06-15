package com.example.t4_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.t4_02.entities.Peliculas;
import com.example.t4_02.services.servicesWeb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class crear_pelicula extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pelicula);

        Button btns = findViewById(R.id.btnRegistrar);
        EditText etTitulo=findViewById(R.id.etTitulo);
        EditText etSinopsis=findViewById(R.id.etSinopsis);
        EditText etUrl=findViewById(R.id.etUrl);

        btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etTitulo.getText().toString().isEmpty() && !etSinopsis.getText().toString().isEmpty() && !etUrl.getText().toString().isEmpty()) {
                Retrofit retrofit = new  Retrofit.Builder()
                        .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                    servicesWeb services = retrofit.create(servicesWeb.class);

                    Peliculas peliculas = new Peliculas();


                    peliculas.titulo = String.valueOf(etTitulo.getText());
                    peliculas.sinopsis = String.valueOf(etSinopsis.getText());
                    peliculas.url = String.valueOf(etUrl.getText());

                    Toast.makeText(crear_pelicula.this,"Elemento creado",Toast.LENGTH_LONG).show();
                    services.create(peliculas);
                    Call<Peliculas> call = services.create(peliculas);

                    call.enqueue(new Callback<Peliculas>() {
                        @Override
                        public void onResponse(Call<Peliculas> call, Response<Peliculas> response) {

                        }

                        @Override
                        public void onFailure(Call<Peliculas> call, Throwable t) {

                        }
                    });


                }
                else{
                    Toast.makeText(crear_pelicula.this,"Algun campo esta vacio",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}