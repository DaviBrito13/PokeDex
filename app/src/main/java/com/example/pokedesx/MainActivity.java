package com.example.pokedesx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.net.URI;
import java.util.ArrayList;

import com.example.pokedesx.PokemonCapturado;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Tag;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";

    private Retrofit retrofit;

    private RecyclerView recyclerView;

    private PokemonAdapter pokemonAdapter;

    private int deslocamento;

    private boolean prontoParaCarregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.listaPokemon);
        pokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView,dx,dy);

                if (dy > 0) {
                    int contagemItensVisiveis = layoutManager.getChildCount();
                    int contagemItensTotais = layoutManager.getItemCount();
                    int contagemItensVisiveisAnteriores = layoutManager.findFirstVisibleItemPosition();

                    if(prontoParaCarregar) {
                        if( (contagemItensVisiveis + contagemItensVisiveisAnteriores) >= contagemItensTotais){
                            prontoParaCarregar = false;
                            deslocamento += 20;
                            obterDados(deslocamento);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        prontoParaCarregar = true;
        deslocamento = 0;
        obterDados(deslocamento);
    }

    private void obterDados(int deslocamento) {
        PokeApiService servico = retrofit.create(PokeApiService.class);
        Call<PokemonCapturado> pokemonCapturadoCall = servico.pegarTodosPokemon(20, deslocamento);

        pokemonCapturadoCall.enqueue(new Callback<PokemonCapturado>() {
            @Override
            public void onResponse(Call<PokemonCapturado> call, Response<PokemonCapturado> response) {
                prontoParaCarregar = true;
                if(response.isSuccessful()){
                        PokemonCapturado pokemonCapturado = response.body();
                        ArrayList<Pokemon> listaPokemon = pokemonCapturado.getResults();
                        pokemonAdapter.capturarPokemon(listaPokemon);

                }

            }

            @Override
            public void onFailure(Call<PokemonCapturado> call, Throwable t) {

            }
        });
    }
}