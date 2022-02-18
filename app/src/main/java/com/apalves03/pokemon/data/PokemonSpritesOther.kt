package com.apalves03.pokemon.data

/**
 * Data class that represents a other Pokemon sprites  from PokeAPI.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below. For a full list of fields, consult the API documentation
 * [here](https://pokeapi.co/docs/v2#pokemonsprites).
 */
data class PokemonSpritesOther (
    val home: PokemonSpritesHome?
)
