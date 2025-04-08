package com.example.wednesdayapp.data

import com.example.wednesdayapp.network.FrogsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Dependency Injection container at the application level
 */
interface AppContainer {
    val frogsRepository: FrogsRepository
}

/**
 * Implementation for the Dependency Injection container at the application level
 *
 * Variables are initialized lazily and tne same instance is shared across whole app
 */
class DefaultAppContainer : AppContainer {
    private val baseUrl = " https://android-kotlin-fun-mars-server.appspot.com/"

    /**
     * Use Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: FrogsApiService by lazy {
        retrofit.create(FrogsApiService::class.java)
    }

    /**
     * DI implementation for Frogs data
     */
    override val frogsRepository: FrogsRepository by lazy {
        NetworkFrogsRepository(retrofitService)
    }
}