package com.apalves03.pokemon

import com.apalves03.pokemon.data.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PokemonTest {

    private lateinit var pokemon: Pokemon

    @Before
    fun setUp() {
        val pokemonAbilityNamedApiResource1 =
            NamedApiResource("blaze", "https://pokeapi.co/api/v2/ability/66/")

        val pokemonAbility1 = PokemonAbility(false,1, pokemonAbilityNamedApiResource1)

        val pokemonAbilityNamedApiResource2 =
            NamedApiResource("solar-power", "https://pokeapi.co/api/v2/ability/94/")

        val pokemonAbility2 = PokemonAbility(true,3, pokemonAbilityNamedApiResource2)

        val abilities: List<PokemonAbility> = listOf(pokemonAbility1, pokemonAbility2)

        val pokemonTypeNamedApiResource =
            NamedApiResource("fire", "https://pokeapi.co/api/v2/type/10/")

        val pokemonType = PokemonType(pokemonTypeNamedApiResource)

        val types: List<PokemonType> = listOf(pokemonType)

        val pokemonSpritesHome =
            PokemonSpritesHome("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/4.png")

        val pokemonSpritesOther = PokemonSpritesOther(pokemonSpritesHome)

        val pokemonSprites = PokemonSprites(pokemonSpritesOther)

        pokemon = Pokemon(4, "charmander", 6, 85, abilities, types, pokemonSprites)
    }

    @Test
    fun `test_formatted_height`() {
        Assert.assertEquals("6", pokemon.getFormattedHeight())
    }

    @Test
    fun `test_formatted_weight`() {
        Assert.assertEquals("85", pokemon.getFormattedWeight())
    }

    @Test
    fun `test_formatted_id`() {
        Assert.assertEquals("#0004", pokemon.getFormattedId())
    }

    @Test
    fun `test_formatted_abilities`() {
        Assert.assertEquals("blaze | solar-power", pokemon.getFormattedAbilities())
    }

    @Test
    fun `test_formatted_types`() {
        Assert.assertEquals("fire", pokemon.getFormattedTypes())
    }

    @Test
    fun `test_toString`() {
        Assert.assertEquals("charmander", pokemon.toString())
    }
}