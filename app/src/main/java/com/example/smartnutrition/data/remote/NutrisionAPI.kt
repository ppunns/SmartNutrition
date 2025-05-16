package com.example.smartnutrition.data.remote

import com.example.smartnutrition.data.model.FruitRequest
import com.example.smartnutrition.data.model.LoginRequest
import com.example.smartnutrition.data.remote.dto.LoginResponse
import com.example.smartnutrition.data.remote.dto.NutritionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NutrisionAPI {
    @GET("api/history-harian/{userId}")
    suspend fun getHistoryHarian(
        @Path("userId") userId: String
    ): Response<NutritionResponse>
    @GET("api/nutrisi-harian/{userId}")
    suspend fun getDailyNutrition(
        @Path("userId") userId: String
    ): Response<NutritionResponse>

    @GET("api/nutrisi-bulanan/{userId}")
    suspend fun getMonthlyNutrition(
        @Path("userId") userId: String
    ): Response<NutritionResponse>

    @GET("api/history-bulanan/{userId}")
    suspend fun getMonthlyHistory(
        @Path("userId") userId: String
    ): Response<NutritionResponse>

    @GET("api/buah/nutrisi/{userId}")
    suspend fun getFruitNutrition(
        @Path("userId") userId: String
    ): Response<NutritionResponse>
    @POST("api/fruit")
    suspend fun getFruitDetail(@Body request: FruitRequest):NutritionResponse
}
