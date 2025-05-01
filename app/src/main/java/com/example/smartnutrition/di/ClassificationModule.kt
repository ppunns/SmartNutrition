package com.example.smartnutrition.di

import android.content.Context
import com.example.smartnutrition.data.repository.FruitClassificationRepositoryImpl
import com.example.smartnutrition.domain.repository.FruitClassificationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassificationModule {

    @Provides
    @Singleton
    fun provideFruitClassificationRepository(
        @ApplicationContext context: Context
    ): FruitClassificationRepository {
        return FruitClassificationRepositoryImpl(context)
    }
}