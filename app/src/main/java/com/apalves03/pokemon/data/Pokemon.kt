package com.apalves03.pokemon.data

/**
 * Data class that represents a  Pokemon from PokeAPI.
 *
 * Not all of the fields returned from the API are represented here; only the ones used in this
 * project are listed below. For a full list of fields, consult the API documentation
 * [here](https://pokeapi.co/docs/v2#pokemon).
 */
data class Pokemon (
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>,
    val types: List<PokemonType>,
    val sprites: PokemonSprites
) {
    /**
     * Returns the height in text format.
     */
    fun getFormattedHeight(): String = height.toString()

    /**
     * Returns the weight in text format.
     */
    fun getFormattedWeight(): String = weight.toString()

    /**
     * Returns the id in text format.
     */
    fun getFormattedId(): String = "#%0${4}d".format(id)

    /**
     * Returns the abilitys in text format.
     */
    fun getFormattedAbilities(): String = abilities.map { pokemonAbility ->
        pokemonAbility.ability.name
    }.joinToString(" | ")


    /**
     * Returns the types in text format.
     */
    fun getFormattedTypes(): String = types.map { pokemonType ->
        pokemonType.type.name
    }.joinToString(" | ")

    override fun toString() = name
}

