package com.apalves03.pokemon.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apalves03.pokemon.data.Pokemon
import com.apalves03.pokemon.databinding.ItemPokemonBinding

/**
 * This class implements a [RecyclerView] [PagingDataAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 *
 * The primary Paging library component in the UI layer is PagingDataAdapter,
 * a RecyclerView adapter that handles paginated data.
 */
class PokemonListPagingAdapter(val clickListener: PokemonListener) :
    PagingDataAdapter<Pokemon, PokemonListPagingAdapter.PokemonViewHolder>(DiffCallback) {

    /**
     * The PokemonViewHolder constructor takes the binding variable from the associated
     * ItemPokemonBinding, which nicely gives it access to the full [Pokemon] information.
     */
    class PokemonViewHolder(
        private var binding: ItemPokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: PokemonListener, pokemon: Pokemon) {
            binding.pokemon = pokemon
            binding.clickListener = clickListener

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [Pokemon] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {

        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
        return PokemonViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        if (pokemon != null) {
            holder.bind(clickListener, pokemon)
        }
    }

}

/**
 * Event listener required to use PokemonListPagingAdapter
 */
class PokemonListener(val clickListener: (pokemon: Pokemon) -> Unit) {
    fun onClick(pokemon: Pokemon) = clickListener(pokemon)
}
