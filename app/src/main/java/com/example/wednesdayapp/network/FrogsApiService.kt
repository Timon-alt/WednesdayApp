package com.example.wednesdayapp.network

import com.example.wednesdayapp.model.FrogsData
import retrofit2.http.GET

/**
 * A public interface that exposes the [getFrogs] method
 */
interface FrogsApiService {
    /**
     * Rerurns [List] of [FrogsData] and this method can be called from a coroutine
     * The @GET annotation indicates that the "amphibians" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("amphibians")
    suspend fun getFrogs(): List<FrogsData>
}