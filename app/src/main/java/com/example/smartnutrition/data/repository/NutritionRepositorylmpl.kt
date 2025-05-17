package com.example.smartnutrition.data.repository

import com.example.smartnutrition.data.model.AddNutritionRequest
import com.example.smartnutrition.data.model.FruitRequest
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
    override suspend fun getNutritionByLabel(label: String): Flow<Result<NutritionResponse>> = flow {
        try {
            val response = api.getFruitDetail(FruitRequest(label = label))
            if (response.data != null) {
                emit(Result.success(response)) // Return the entire response, not just response.data
            } else {
                emit(Result.failure(Exception("Data nutrisi tidak ditemukan")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun addNutrition(userId: String, fruitLabel: String, quantity: Int): Flow<Result<NutritionResponse>> = flow {
        try {
            val request = api.addNutrition(AddNutritionRequest(
                fruitLabel = fruitLabel,
                quantity = quantity,
                userId = userId
            ))
//            val response = api.addNutrition(request)
            if (request.isSuccessful) {
                emit(Result.success(request.body()!!))
            } else {
                emit(Result.failure(Exception("Gagal menambahkan data nutrisi")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}