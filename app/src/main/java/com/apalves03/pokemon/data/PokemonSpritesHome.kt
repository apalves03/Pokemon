package com.apalves03.pokemon.data

import com.squareup.moshi.Json

/**
 * Data class that represents a Pokemon home sprites from PokeAPI.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below. For a full list of fields, consult the API documentation
 * [here](https://pokeapi.co/docs/v2#pokemonsprites).
 */
data class PokemonSpritesHome (
    // used to map front_default from the JSON to frontDefault in our class
    @Json(name = "front_default") val frontDefault: String?
)