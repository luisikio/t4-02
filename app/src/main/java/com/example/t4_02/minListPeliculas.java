package com.example.t4_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.t4_02.entities.Peliculas;
import com.example.t4_02.services.servicesWeb;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class minListPeliculas extends AppCompatActivity {
    ImageView imgA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_list_peliculas);


        String pokemonJson = getIntent().getStringExtra("Pokemon");
        Peliculas peliculas = new Gson().fromJson(pokemonJson,Peliculas.class);

        imgA=findViewById(R.id.ivAvat);
        TextView tvnom=findViewById(R.id.tvTit);
        TextView tvmResumen=findViewById(R.id.tvSip);
        Button delete=findViewById(R.id.btnEliminar);

        tvnom.setText(peliculas.titulo);
        tvmResumen.setText(peliculas.sinopsis);
        Picasso.get().load(peliculas.url).into(imgA);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new  Retrofit.Builder()
                        .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                servicesWeb services = retrofit.create(servicesWeb.class);

                Peliculas peliculas = new Peliculas();

                services.create(peliculas);
                Call<Peliculas> call = services.delete(peliculas.id);

//                call.enqueue(new Callback<Peliculas>() {
//                    @Override
//                    public void onResponse(Call<Peliculas> call, Response<Peliculas> response) {
//                        if (response.isSuccessful()) {
//                    Log.i("APP_VJ20202", "Se elimino correctamente al contacto " + peliculas.id);
//                    Peliculas.
//                    notifyItemRemoved(position);
//                    notifyItemRangeChanged(position, contacts.size());
//
//                } else {
//                    Log.e("APP_VJ20202", "No se pudo eliminar el contacto");
//                }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Peliculas> call, Throwable t) {
//
//                    }
 //               });
            }
        });
    }
}