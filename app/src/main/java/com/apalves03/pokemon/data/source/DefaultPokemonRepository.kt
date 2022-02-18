package com.apalves03.pokemon.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apalves03.pokemon.data.Pokemon
import kotlinx.coroutines.flow.Flow

/**
 * Data layer implementation for searching for Pokemons on the network.
 */
class DefaultPokemonRepository (
    private val pokemonRemoteDataSource: PokemonDataSource
    ) : PokemonRepository {

    /**
     * A PagingData object is a container for a snapshot of paginated data.
     * It queries a PagingSource object and stores the result.
     */
    override fun getSearchResultStream(): Flow<PagingData<Pokemon>> {
        /**
         * The Pager component provides a public API for constructing instances of PagingData that
         * are exposed in reactive streams, based on a PagingSource object and
         * a PagingConfig configuration object.
         */
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { PokemonPagingSource(pokemonRemoteDataSource) }
        ).flow
    }

    /**
     *  A companion object is similar to other objects, such as instances of a class.
     *
     *  A single instance of a companion object will exist throughout your program,
     *  so called a singleton pattern.
     */
    companion object {
        // Create a property for the pagination size
        const val NETWORK_PAGE_SIZE = 12
    }

}