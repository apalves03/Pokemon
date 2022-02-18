package com.apalves03.pokemon.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.apalves03.pokemon.R
import com.apalves03.pokemon.databinding.FragmentPokemonListBinding
import com.apalves03.pokemon.viewmodels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * This fragment shows a list of pokemon using Paging.
 *
 * Annotating Android classes with @AndroidEntryPoint creates a dependencies container that follows
 * the Android class lifecycle.
 */
@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private var searchJob: Job? = null
    private var statusSearchJob: Job? = null

    private var adapterList: PokemonListPagingAdapter? = null

    private val viewModel: PokemonViewModel by activityViewModels()

    /**
     * Inflates the layout with Data Binding.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Sets its lifecycle owner to the PokemonListFragment to enable Data Binding to observe LiveData,
     * and sets up the RecyclerView with an adapter.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = viewLifecycleOwner

        // Giving the binding access to the PokemonViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the PokemonPagingAdapter RecyclerView
        adapterList = PokemonListPagingAdapter(PokemonListener { pokemon ->
            viewModel.onPokemonClicked(pokemon)
            findNavController()
                .navigate(R.id.action_pokemonListFragment_to_pokemonDetailFragment)
        })

        adapterList?.let {
            binding.pokemonGrid.adapter = it.withLoadStateHeaderAndFooter(
                header = PokemonListLoadStatePagingAdapter { it.retry() },
                footer = PokemonListLoadStatePagingAdapter { it.retry() }
            )
        }

        binding.retryButton.setOnClickListener {
            adapterList?.retry()
        }

        searchPokemons()
    }

    private fun searchPokemons() {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            // update pokemon list when new data exists
            viewModel.searchPokemons().collectLatest {
                adapterList?.submitData(it)
            }
        }

        // Make sure we cancel the previous job before creating a new one
        statusSearchJob?.cancel()
        statusSearchJob = lifecycleScope.launch {
            // updates pokemons query status when entering the app
            adapterList?.loadStateFlow?.collectLatest { loadStates ->
                if (loadStates.refresh is LoadState.Loading) {
                    viewModel.updateLoadingStatus()
                    return@collectLatest
                }

                if (loadStates.refresh is LoadState.Error) {
                    viewModel.updateErrorStatus()
                    return@collectLatest
                }

                viewModel.updateDoneStatus()
            }
        }
    }
}
