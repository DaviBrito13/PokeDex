package com.example.pokedesx;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.example.pokedesx.PokemonCapturado;

public interface PokeApiService {

    @GET("pokemon")
    Call<PokemonCapturado> pegarTodosPokemon(@Query("limit") int limite, @Query("offset") int deslocamento);

}
