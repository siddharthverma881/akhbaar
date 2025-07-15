package com.example.akhbaar.data.api

import com.example.akhbaar.data.model.TopHeadlinesResponse
import com.example.akhbaar.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @Headers("X-Api-Key: $API_KEY", "User-Agent: abc")
    @GET("/v2/top-headlines")
    suspend fun getHeadLines(
        @Query("country") country: String
    ): TopHeadlinesResponse
}