package com.apalves03.pokemon.data

/**
 * Data class that represents a types the Pokémon from PokeAPI.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below. For a full list of fields, consult the API documentation
 * [here](https://pokeapi.co/docs/v2#namedapiresource).
 */
data class PokemonType (
    val type: NamedApiResource
)