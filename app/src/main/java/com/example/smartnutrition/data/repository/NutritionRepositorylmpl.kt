package com.example.smartnutrition.data.repository

import com.example.smartnutrition.data.remote.NutrisionAPI
import com.example.smartnutrition.data.remote.dto.NutritionResponse
import com.example.smartnutrition.domain.repository.NutritionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NutritionRepositorylmpl @Inject constructor(
    private val api: NutrisionAPI
) : NutritionRepository {
    override suspend fun getDailyNutrition(userId: String): Flow<Result<NutritionResponse>> = flow {
        try {
            val response = api.getDailyNutrition(userId)
            if (response.isSuccessful) {
                emit(Result.success(response.body()!!))
            } else {
                emit(Result.failure(Exception("Gagal mengambil data harian")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getMonthlyNutrition(userId: String): Flow<Result<NutritionResponse>> = flow {
        try {
            val response = api.getMonthlyNutrition(userId)
            if (response.isSuccessful){
                emit(Result.success(response.body()!!))
            }else{
                emit(Result.failure(Exception("Gagal mengambil data bulanan")))
            }
        }catch (e:Exception){
            emit(Result.failure(e))
        }
    }

    override suspend fun getDailyHistory(userId: String): Flow<Result<NutritionResponse>> = flow{
        try {
            val response = api.getHistoryHarian(userId)
            if (response.isSuccessful){
                emit(Result.success(response.body()!!))
            }else{
                emit(Result.failure(Exception("Gagal mengambil history data harian")))
            }
        }catch (e:Exception){
            emit(Result.failure(e))
        }
    }

    override suspend fun getMonthlyHistory(userId: String): Flow<Result<NutritionResponse>> = flow {
        try {
            val response = api.getMonthlyHistory(userId)
            if (response.isSuccessful){
                emit(Result.success(response.body()!!))
            }else{
                emit(Result.failure(Exception("Gagal mengambil history data bulanan")))
            }
        }catch (e:Exception){
            emit(Result.failure(e))
        }
    }

    override suspend fun getFruitNutrition(userId: String): Flow<Result<NutritionResponse>> {
        TODO("Not yet implemented")
    }
}