package com.apalves03.pokemon.utilities

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * Instrumented tests using Hilt need to be executed in an Application that supports Hilt.
 * The library already comes with HiltTestApplication that we can use to run our UI tests.
 * Specifying the Application to use in tests is done by creating a new test runner in the project.
 */
class MainTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}