package com.example.t4_02.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t4_02.R;
import com.example.t4_02.entities.Peliculas;
import com.example.t4_02.minListPeliculas;
import com.example.t4_02.services.servicesWeb;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class peliculaAdapter extends RecyclerView.Adapter<peliculaAdapter.peliculaViewHolder> {

    List<Peliculas> peliculas;

    public peliculaAdapter(List<Peliculas> peliculas) {
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public peliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula,parent,false);
        peliculaAdapter.peliculaViewHolder vh= new peliculaAdapter.peliculaViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull peliculaViewHolder holder, int position) {
        View vw=holder.itemView;

        Peliculas pelicula = peliculas.get(position);

        TextView itemTitulo=holder.itemView.findViewById(R.id.tvTitulo);
        TextView itemSinopsis=holder.itemView.findViewById(R.id.tvSinopsis);
        ImageView itemImg=holder.itemView.findViewById(R.id.ivAvatar);


        itemTitulo.setText(pelicula.titulo);
        itemSinopsis.setText(pelicula.sinopsis);

        Picasso.get().load(pelicula.url).into(itemImg);
        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new  Retrofit.Builder()
                        .baseUrl("https://6284e8f8a48bd3c40b77c373.mockapi.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                servicesWeb services = retrofit.create(servicesWeb.class);
                Call<Peliculas> call=services.findContact(pelicula.id);
                call.enqueue(new Callback<Peliculas>() {
                    @Override
                    public void onResponse(Call<Peliculas> call, Response<Peliculas> response) {
                        if (!response.isSuccessful()) {
                            Log.e("asd1234", "error");
                        } else {

                            Log.i("123holq", new Gson().toJson(response.body()));
                            Log.i("asd3243", "Respuesta correcta por id");

                            Intent intent = new Intent(vw.getContext(), minListPeliculas.class);


                            //                           Log.i("asd32", "Respuesta correcta por id------------ ");
//                            intent.putExtra("img",pokemon.img);
//                            intent.putExtra("name",pokemon.name);
//                            intent.putExtra("number",pokemon.number);
//                            intent.putExtra("region",pokemon.region);
//                            intent.putExtra("tipo",pokemon.tipo);
                            String pokemonJson = new Gson().toJson(pelicula);
                            intent.putExtra("Pokemon", pokemonJson);

                            vw.getContext().startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<Peliculas> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class peliculaViewHolder extends RecyclerView.ViewHolder {
        public peliculaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
