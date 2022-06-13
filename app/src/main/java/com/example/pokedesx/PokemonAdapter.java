package com.example.pokedesx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> listaPokemon;
    private Context context;

    public PokemonAdapter(Context context) {
        this.context = context;
        listaPokemon = new ArrayList<>();
    }

    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.ViewHolder holder, int position) {
        Pokemon pokemon = listaPokemon.get(position);
        holder.nomePokemon.setText(pokemon.getName());

        Glide.with(context)
                .load("https://pokeapi.co/media/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoPokemom);


    }

    @Override
    public int getItemCount() {
        return listaPokemon.size();
    }

    public void capturarPokemon(ArrayList<Pokemon> listaRecebida) {
        listaPokemon.addAll(listaRecebida);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoPokemom;
        private TextView nomePokemon;

        public ViewHolder (View itemView) {
            super(itemView);

            fotoPokemom = (ImageView) itemView.findViewById(R.id.fotoPokemon);
            nomePokemon = (TextView) itemView.findViewById(R.id.nomePokemon);
        }
    }
}
