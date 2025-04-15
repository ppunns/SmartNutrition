package com.example.smartnutrition.data.remote

import com.example.smartnutrition.data.remote.dto.NewsResponse
import com.example.smartnutrition.domain.model.Source
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("v2/everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}