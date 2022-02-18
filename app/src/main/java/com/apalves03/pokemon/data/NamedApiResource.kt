package com.apalves03.pokemon.data

/**
 * Data class that represents a name and resource URL from PokeAPI.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below. For a full list of fields, consult the API documentation
 * [here](https://pokeapi.co/docs/v2#namedapiresource).
 */
data class NamedApiResource (
    val name: String,
    val url: String
)