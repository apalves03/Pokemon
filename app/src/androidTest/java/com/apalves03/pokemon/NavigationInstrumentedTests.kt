package com.apalves03.pokemon

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.apalves03.pokemon.list.PokemonListFragment
import com.apalves03.pokemon.detail.PokemonDetailFragment
import com.apalves03.pokemon.utilities.BaseInstrumentedTest
import com.apalves03.pokemon.utilities.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test of the all navigation flows, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class NavigationInstrumentedTests : BaseInstrumentedTest() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<PokemonActivity>
            = ActivityScenarioRule(PokemonActivity::class.java)

    /**
     * Test navigation from [PokemonListFragment] to [PokemonDetailFragment]
     */
    @Test
    fun `navigate_to_list_from_detail`() {
        // Init nav controller
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        // Launch PokemonListFragment
        launchFragmentInHiltContainer<PokemonListFragment>(themeResId = R.style.Theme_Pokemon) {
            // Configure nav controller
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }
        // Click in pokemon of the list
        waitForView(ViewMatchers.withText("charmander")).perform(ViewActions.click())
        // Check destination is correct
        assertEquals(navController.currentDestination?.id, R.id.pokemonDetailFragment)
    }

}