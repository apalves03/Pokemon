package com.apalves03.pokemon.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.apalves03.pokemon.databinding.FragmentPokemonDetailBinding
import com.apalves03.pokemon.viewmodels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment shows the detailed information on a particular Pokemon
 *
 * Annotating Android classes with @AndroidEntryPoint creates a dependencies container that follows
 * the Android class lifecycle.
 */
@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PokemonViewModel by activityViewModels()

    /**
     * Inflates the layout with Data Binding.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentPokemonDetailBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    /**
     * Sets its lifecycle owner to the PokemonDetailFragment to enable Data Binding
     * to observe LiveData.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the PokemonViewModel
        binding.viewModel = viewModel
    }
}