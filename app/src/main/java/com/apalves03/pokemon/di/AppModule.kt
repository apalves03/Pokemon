package com.apalves03.pokemon.di

import com.apalves03.pokemon.data.source.DefaultPokemonRepository
import com.apalves03.pokemon.data.source.PokemonDataSource
import com.apalves03.pokemon.data.source.PokemonRepository
import com.apalves03.pokemon.data.source.remote.PokemonRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * This module provides binding information for Hilt to inject a constructor interface or
 * inject an external library class.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * You can define multiple bindings for the same type with qualifiers.
     * A qualifier is an annotation that you use to identify a specific binding for a type when
     * that type has multiple bindings defined.
     */
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemotePokemonDataSource

    /**
     * The annotated function supplies the following information to Hilt:
     *
     * The function return type tells Hilt what type the function provides instances of.
     *
     * The function parameters tell Hilt the dependencies of the corresponding type.
     *
     * The function body tells Hilt how to provide an instance of the corresponding type.
     * Hilt executes the function body every time it needs to provide an instance of that type.
     */
    @Singleton
    @RemotePokemonDataSource
    @Provides
    fun providePokemonRemoteDataSource(): PokemonDataSource {
        return PokemonRemoteDataSource.create()
    }

}

/**
 * The binding for PokemonRepository is on its own module so that we can replace it easily
 * in tests.
 */
@Module
@InstallIn(SingletonComponent::class)
object PokemonRepositoryModule {

    @Singleton
    @Provides
    fun PokemonRepository(
        /**
         * You inject the specific type you need by annotating the field or parameter
         * with the corresponding qualifier
         */
        @AppModule.RemotePokemonDataSource remotePokemonDataSource: PokemonDataSource,
    ): PokemonRepository {
        return DefaultPokemonRepository(remotePokemonDataSource)
    }
}