package com.apalves03.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import com.apalves03.pokemon.list.PokemonListFragment

/**
 * PokemonActivity sets the content view activity_pokemon, a fragment container that contains
 * [PokemonListFragment].
 *
 * If you annotate an Android class with @AndroidEntryPoint, then you also must annotate Android
 * classes that depend on it.
 * Hilt needs to be aware of the Activity that hosts the Fragment in order to work.
 * We need to use @AndroidEntryPoint.
 */
@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}