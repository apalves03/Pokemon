package com.apalves03.pokemon.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apalves03.pokemon.data.source.PokemonRepository
import com.apalves03.pokemon.utilities.MainCoroutineRule
import com.apalves03.pokemon.utilities.runBlockingTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.rules.RuleChain
import javax.inject.Inject
import kotlin.jvm.Throws

/**
 * @HiltAndroidTest is responsible for generating the Hilt components for each test
 */
@HiltAndroidTest
@ExperimentalCoroutinesApi
class PokemonViewModelTest {

    private lateinit var viewModel: PokemonViewModel
    // HiltAndroidRule manages the components state and is used to perform injection on your test.
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Inject
    lateinit var defaultPokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        hiltRule.inject()

        viewModel = PokemonViewModel(defaultPokemonRepository)
    }

    @Test
    @Throws(InterruptedException::class)
    fun `list_pokemon`() = coroutineRule.runBlockingTest {
        Assert.assertNotNull(viewModel.searchPokemons())
    }


}