package com.example.smartnutrition.data.remote

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NutrisionAPI {
//    Mengambil History Harian
    @GET("api/history-harian/{userId}")
    suspend fun getHistory(
        @Path("userId") userId: String
    ): Response<HistoryReponse>
//    Mengambil Nutrisi Harian
    @GET("api/nutrisi-harian/{userId}")
    suspend fun getDailyHistory(
        @Path("userId") userId: String
    ): Response<HistoryResponse>

    @GET("api/history-bulanan/{userId}")
    suspend fun getMonthlyHistory(
        @Path("userId") userId: String
    ): Response<HistoryResponse>

    @GET("api/history-tahunan/{userId}")
    suspend fun getYearlyHistory(
        @Path("userId") userId: String
    ): Response<HistoryResponse>



}
