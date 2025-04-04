package com.example.wednesdayapp.data

import com.example.wednesdayapp.model.FrogsData
import com.example.wednesdayapp.network.FrogsApiService

/**
 * Repository that fetch frogs list from FrogsApi
 */
interface FrogsRepository {
    /**Fetches list of frogs from frogsApi**/
    suspend fun getFrogs(): List<FrogsData>
}

/**
 * Network Implementation of Repository that fetch frogs list from frogsApi
 */
class NetworkFrogsRepository(
    private val frogsApiService: FrogsApiService
): FrogsRepository {
    /** Fetches list of frogs from frogsApi**/
    override suspend fun getFrogs(): List<FrogsData> = frogsApiService.getFrogs()
}