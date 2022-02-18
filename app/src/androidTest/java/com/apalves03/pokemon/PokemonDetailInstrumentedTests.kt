package com.apalves03.pokemon

import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.apalves03.pokemon.utilities.BaseInstrumentedTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test of the Pokemon detail, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class PokemonDetailInstrumentedTests : BaseInstrumentedTest() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<PokemonActivity>
            = ActivityScenarioRule(PokemonActivity::class.java)

    @Test
    fun `detail_content`() {
        // Test of theredirected to a detail screen
        waitForView(ViewMatchers.withText("charmander"))
            .perform(ViewActions.click())
        waitForView(ViewMatchers.withText("charmander"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}