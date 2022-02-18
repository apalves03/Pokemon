package com.apalves03.pokemon.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.apalves03.pokemon.data.Pokemon
import com.apalves03.pokemon.data.source.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.apalves03.pokemon.list.PokemonListFragment
import com.apalves03.pokemon.detail.PokemonDetailFragment

enum class PokemonStatus { LOADING, ERROR, DONE }

/**
 * [PokemonViewModel] is used to save application data from multiple fragments into a single
 * ViewModel file.
 *
 * The [ViewModel] is shared between [PokemonListFragment] and [PokemonDetailFragment] using
 * its scope of activity.
 */
@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repositoryDefault: PokemonRepository
) : ViewModel() {

    // The internal MutableLiveData that stores the select Pokemon
    private val _pokemon = MutableLiveData<Pokemon>()
    // The external immutable LiveData for the select Pokemon
    val pokemon: LiveData<Pokemon> = _pokemon

    // The internal Flow that stores the current Pokemon list with Pagination
    private var _currentSearchResult: Flow<PagingData<Pokemon>>? = null

    // The internal MutableLiveData that stores the status of the most recent request the pagination
    private val _status = MutableLiveData<PokemonStatus>()
    // The external immutable LiveData for the request status of the pagination refresh
    val status: LiveData<PokemonStatus> = _status

    /**
     * A PagingData object is a container for a snapshot of paginated data.
     * It queries a PagingSource object and stores the result.
     */
    fun searchPokemons(): Flow<PagingData<Pokemon>> {
        var newResult: Flow<PagingData<Pokemon>>? = _currentSearchResult
        if (newResult == null) {
            /**
             * The cachedIn() operator makes the data stream shareable and caches the loaded data
             * with the provided CoroutineScope.
             */
            newResult = repositoryDefault.getSearchResultStream().cachedIn(viewModelScope)
            _currentSearchResult = newResult
        }

        return newResult
    }

    /**
     * Updates pokemons query status when entering the app for loading
     */
    fun updateLoadingStatus() {
        _status.value = PokemonStatus.LOADING
    }

    /**
     * Updates pokemons query status when entering the app for error
     */
    fun updateErrorStatus() {
        _status.value = PokemonStatus.ERROR
    }

    /**
     * Updates pokemons query status when entering the app for done
     */
    fun updateDoneStatus() {
        _status.value = PokemonStatus.DONE
    }

    /**
     * Click event when selecting a pokemon
     */
    fun onPokemonClicked(pokemon: Pokemon) {
        _pokemon.value = pokemon
    }
}
