package com.apalves03.pokemon.data.source

import androidx.paging.PagingData
import com.apalves03.pokemon.data.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * Interface to the data layer.
 */
interface PokemonRepository {

    fun getSearchResultStream(): Flow<PagingData<Pokemon>>
}