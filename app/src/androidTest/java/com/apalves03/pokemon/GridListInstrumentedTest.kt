package com.apalves03.pokemon

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.apalves03.pokemon.utilities.BaseInstrumentedTest
import com.apalves03.pokemon.utilities.BaseInstrumentedTest.RecyclerViewAssertion.hasItemCount
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test of the GridLayout with Recyclerview, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class GridListInstrumentedTest : BaseInstrumentedTest() {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityRule: ActivityScenarioRule<PokemonActivity>
            = ActivityScenarioRule(PokemonActivity::class.java)

    @Test
    fun `grid_list_content_at_first_position`() {
        /// Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        waitForView(ViewMatchers.withText("bulbasaur"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        waitForView(ViewMatchers.withContentDescription(
            appContext.resources.getString(R.string.pokemon_item_image, "bulbasaur")
        )).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `grid_list_content_on_first_page`() {
        waitForView(ViewMatchers.withText("bulbasaur"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        waitForView(ViewMatchers.withText("charmander"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `grid_list_content_at_last_position`() {
        waitForView(ViewMatchers.withText("bulbasaur"))
        waitForView(withId(R.id.pokemon_grid))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(sizeList - 1))
        waitForView(ViewMatchers.withText("clefable"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `vertical_scrolling`() {
        waitForView(ViewMatchers.withText("bulbasaur"))
        waitForView(withId(R.id.pokemon_grid))
            .perform(ViewActions.swipeUp())
        waitForView(ViewMatchers.withText("raticate"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `recycler_view_item_count`() {
        waitForView(ViewMatchers.withText("bulbasaur"))
        waitForView(withId(R.id.pokemon_grid)).check(hasItemCount(sizeList))
    }
}