package com.apalves03.pokemon.utilities

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.TreeIterables
import com.apalves03.pokemon.data.source.DefaultPokemonRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import java.lang.Exception
import java.lang.IllegalStateException

/**
 * Base Utilitary of the Instrumented Tests for search for view matcher.
 */
open class BaseInstrumentedTest {

    val sizeList = (DefaultPokemonRepository.NETWORK_PAGE_SIZE * 3)

    companion object {

        /**
         * Search for matcher
         */
        fun lookFor(matcher: Matcher<View>): ViewAction {

            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return ViewMatchers.isRoot()
                }

                override fun getDescription(): String {
                    return "Looking for $matcher"
                }

                override fun perform(uiController: UiController?, view: View?) {
                    var attempts = 0
                    val childViews: Iterable<View> = TreeIterables.breadthFirstViewTraversal(view)
                    childViews.forEach {
                        attempts++
                        if (matcher.matches(it)) {
                            return
                        }
                    }

                    throw NoMatchingViewException.Builder()
                        .withRootView(view)
                        .withViewMatcher(matcher)
                        .build()
                }
            }
        }
    }

    /**
     * Wait for view to for search matcher
     */
    fun waitForView(matcher: Matcher<View>,
                    timeoutMillis: Int = 5000,
                    attemptTimeoutMillis: Long = 100
    ): ViewInteraction {
        val maxAttempts = timeoutMillis / attemptTimeoutMillis.toInt()
        var attempts = 0
        for (i in 0..maxAttempts) {
            try {
                attempts++
                Espresso.onView(ViewMatchers.isRoot()).perform(lookFor(matcher))
                return Espresso.onView(matcher)
            } catch (e: Exception) {
                if (attempts == maxAttempts) {
                    throw e
                }
                Thread.sleep(attemptTimeoutMillis)
            }
        }
        throw Exception("Could not find a view matching $matcher")
    }

    /**
     * Custom matcher with RecyclerView.
     */
    object RecyclerViewAssertion {

        /**
         * Invokes the [RecyclerViewAssertion] to check the RecyclerView has the correct count
         *
         * @param count The expected number of items in the RecyclerView adapter
         */
        fun hasItemCount(count: Int): ViewAssertion {
            return RecyclerViewAssertion(count)
        }

        /**
         * Custom view assertion to check:
         * The RecyclerView exists
         * The RecyclerView has an adapter
         * The adapter contains the expected number of items
         *
         * @param count The expected number of adapter items
         */
        private class RecyclerViewAssertion(private val count: Int) : ViewAssertion {
            override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }

                if (view !is RecyclerView) {
                    throw IllegalStateException("The view is not a RecyclerView")
                }

                if (view.adapter == null) {
                    throw IllegalStateException("No adapter assigned to RecyclerView")
                }

                // Check item count
                ViewMatchers.assertThat(
                    "RecyclerView item count",
                    view.adapter?.itemCount,
                    CoreMatchers.equalTo(count)
                )
            }
        }
    }
}