package com.apalves03.pokemon.data.source.remote

import com.apalves03.pokemon.data.NamedApiResourceList
import com.apalves03.pokemon.data.Pokemon
import com.apalves03.pokemon.data.source.PokemonDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Used to connect to the PokeAPI to fetch data of the Pokemons.
 *
 */
interface PokemonRemoteDataSource : PokemonDataSource {

    @GET("pokemon")
    override suspend fun getPokemonPaging(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): NamedApiResourceList

    @GET("pokemon/{name}")
    override suspend fun getPokemon(@Path("name") name: String): Pokemon

    /**
     *  A companion object is similar to other objects, such as instances of a class.
     *
     *  A single instance of a companion object will exist throughout your program,
     *  so called a singleton pattern.
     */
    companion object {

        // Create a property for the base URL
        const val BASE_URL = "https://pokeapi.co/api/v2/"

        /**
         * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter
         * for full Kotlin compatibility.
         */
        private val mochi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        /**
         * Use the Retrofit builder to build a retrofit object using a Moshi converter with our
         * Moshi object.
         */
        fun create(): PokemonRemoteDataSource {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(mochi))
                .build()
                .create(PokemonRemoteDataSource::class.java)
        }

    }

}