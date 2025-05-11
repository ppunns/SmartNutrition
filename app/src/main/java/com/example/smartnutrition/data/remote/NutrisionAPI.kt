package com.example.smartnutrition.data.remote

import com.example.smartnutrition.data.remote.dto.DailyNutritionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NutrisionAPI {
    @GET("api/history-harian/{userId}")
    suspend fun getHistoryHarian(
        @Path("userId") userId: String
    ): Response<DailyNutritionResponse>
    @GET("api/nutrisi-harian/{userId}")
    suspend fun getDailyNutrition(
        @Path("userId") userId: String
    ): Response<DailyNutritionResponse>

    @GET("api/nutrisi-bulanan/{userId}")
    suspend fun getMonthlyNutrition(
        @Path("userId") userId: String
    ): Response<DailyNutritionResponse>

    @GET("api/history-bulanan/{userId}")
    suspend fun getMonthlyHistory(
        @Path("userId") userId: String
    ): Response<DailyNutritionResponse>

    @GET("api/buah/nutrisi/{userId}")
    suspend fun getFruitNutrition(
        @Path("userId") userId: String
    ): Response<DailyNutritionResponse>
}
