package com.apalves03.pokemon.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apalves03.pokemon.R
import com.apalves03.pokemon.databinding.ItemPokemonLoadStateBinding

class PokemonListLoadStatePagingAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PokemonListLoadStatePagingAdapter.ReposLoadStateViewHolder>() {

    class ReposLoadStateViewHolder(
        private val binding: ItemPokemonLoadStateBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): ReposLoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_pokemon_load_state, parent, false)
                val binding = ItemPokemonLoadStateBinding.bind(view)
                return ReposLoadStateViewHolder(binding, retry)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ReposLoadStateViewHolder {
        return ReposLoadStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: ReposLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

}
