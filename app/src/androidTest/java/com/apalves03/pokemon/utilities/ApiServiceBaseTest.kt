package com.apalves03.pokemon.utilities

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source

/**
 * Base Utilitary of the Unit Tests with a mock server of the PokeAPI.
 *
 */
open class ApiServiceBaseTest {

    // This object will intercept our network requests
    val mockWebServer = MockWebServer()

    /**
     * Enqueue network requests for mock server
     */
    fun enqueue(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val buffer = inputStream.source().buffer()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(buffer.readString(Charsets.UTF_8))
        )
    }
}