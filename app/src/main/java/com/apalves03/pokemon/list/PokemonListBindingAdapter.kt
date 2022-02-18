package com.apalves03.pokemon.list

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.apalves03.pokemon.R
import com.apalves03.pokemon.viewmodels.PokemonStatus
import com.google.android.material.button.MaterialButton

/**
 * Uses the Coil library to load an image by URL into an [ImageView]
 */
@BindingAdapter("app:imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

/**
 * Uses status of the pagination refresh for image status control.
 */
@BindingAdapter("app:pokeApiStatus")
fun bindStatus(statusImageView: ImageView, status: PokemonStatus?) {
    when(status) {
        PokemonStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.scaleType = ImageView.ScaleType.FIT_CENTER
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PokemonStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.scaleType = ImageView.ScaleType.CENTER
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        PokemonStatus.DONE -> statusImageView.visibility = View.GONE
    }
}

/**
 * Uses status of the pagination refresh for button view control.
 */
@BindingAdapter("app:pokeApiStatus")
fun bindStatus(retryMaterialButton: MaterialButton, status: PokemonStatus?) {
    when(status) {
        PokemonStatus.LOADING -> {
            retryMaterialButton.visibility = View.GONE
        }
        PokemonStatus.ERROR -> {
            retryMaterialButton.visibility = View.VISIBLE
        }
        PokemonStatus.DONE -> retryMaterialButton.visibility = View.GONE
    }
}
