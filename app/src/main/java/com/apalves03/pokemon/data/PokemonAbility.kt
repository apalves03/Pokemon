package com.apalves03.pokemon.data

import com.squareup.moshi.Json

/**
 * Data class that represents a ability the Pokémon from PokeAPI.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below. For a full list of fields, consult the API documentation
 * [here](https://pokeapi.co/docs/v2#namedapiresource).
 */
data class PokemonAbility (
    // used to map is_hidden from the JSON to isHidden in our class
    @Json(name = "is_hidden") val isHidden: Boolean,
    val slot: Int,
    val ability: NamedApiResource
)