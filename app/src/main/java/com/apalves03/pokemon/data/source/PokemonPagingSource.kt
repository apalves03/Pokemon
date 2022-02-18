package com.apalves03.pokemon.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apalves03.pokemon.data.Pokemon

private const val UNSPLASH_STARTING_PAGE_INDEX = 0

/**
 * PagingSource object defines a source of data and how to retrieve data from that source.
 * This PagingSource object load data from network sources.
 *
 * This PagingSource loads pages of items by page number.
 * The Key type is Int and the Value type is Pokemon.
 */
class PokemonPagingSource (
    private val pokemonRemoteDataSource: PokemonDataSource
) : PagingSource<Int, Pokemon>() {

    /**
     * This method is override to indicate how to retrieve paged data from the corresponding
     * data source.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = pokemonRemoteDataSource.getPokemonPaging(page, params.loadSize)
            val pokemons = response.results.map { result ->
                pokemonRemoteDataSource.getPokemon(result.name)
            }
            LoadResult.Page(
                data = pokemons,
                prevKey = if (page == UNSPLASH_STARTING_PAGE_INDEX) {
                    null
                } else if (page == (params.loadSize * 3)) {
                    params.loadSize * 3
                } else {page},
                nextKey = if (response.next == null) null else (page + params.loadSize)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            // This loads starting from previous page, but since PagingConfig.initialLoadSize spans
            // multiple pages, the initial load will still load items centered around
            // anchorPosition. This also prevents needing to immediately launch prepend due to
            // prefetchDistance.
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

}