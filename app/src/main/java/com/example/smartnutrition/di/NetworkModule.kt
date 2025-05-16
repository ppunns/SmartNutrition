package com.example.smartnutrition.di

import android.content.Context
import android.content.SharedPreferences
import com.example.smartnutrition.data.remote.AuthApi
import com.example.smartnutrition.data.remote.NutrisionAPI
import com.example.smartnutrition.data.repository.AuthRepositoryImpl
import com.example.smartnutrition.data.repository.NutritionRepositorylmpl
import com.example.smartnutrition.domain.repository.AuthRepository
import com.example.smartnutrition.domain.repository.NutritionRepository
import com.example.smartnutrition.domain.usecases.Nutrition.GetDailyHistoryNutritionUseCase
import com.example.smartnutrition.domain.usecases.Nutrition.GetDailyNutritionUseCase
import com.example.smartnutrition.domain.usecases.Nutrition.GetFruitNutritionUseCase
import com.example.smartnutrition.domain.usecases.Nutrition.GetMonthlyHistoryNutritionUseCase
import com.example.smartnutrition.domain.usecases.Nutrition.GetMonthlyNutritionUseCase
import com.example.smartnutrition.domain.usecases.Nutrition.NutritionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl("http://34.101.71.0:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNutritionApi(): NutrisionAPI {
        return Retrofit.Builder()
            .baseUrl("http://34.101.96.119:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NutrisionAPI::class.java)
    }
    @Provides
    @Singleton
    fun provideNutritionRepository(
        api: NutrisionAPI
    ):NutritionRepository{
        return NutritionRepositorylmpl(api)
    }
    @Provides
    @Singleton
    fun provideNutritionUseCases(
        nutritionRepository: NutritionRepository
    ): NutritionUseCase {
        return NutritionUseCase(
            getDailyNutritionUseCase = GetDailyNutritionUseCase(nutritionRepository),
            getMonthlyNutritionUseCase = GetMonthlyNutritionUseCase(nutritionRepository),
            getDailyHistoryNutritionUseCase = GetDailyHistoryNutritionUseCase(nutritionRepository),
            getMonthlyHistoryNutritionUseCase = GetMonthlyHistoryNutritionUseCase(nutritionRepository),
            getFruitNutritionUseCase = GetFruitNutritionUseCase(nutritionRepository)
        )
    }
    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        prefs: SharedPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(api, prefs)
    }
}