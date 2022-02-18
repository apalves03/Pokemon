package com.apalves03.pokemon.data.source

import com.apalves03.pokemon.data.NamedApiResourceList
import com.apalves03.pokemon.data.Pokemon

/**
 * Main entry point for accessing Pokemons data.
 */
interface PokemonDataSource {

    suspend fun getPokemonPaging(offset: Int, limit: Int): NamedApiResourceList

    suspend fun getPokemon(name: String): Pokemon
}