package com.example.smartnutrition.di

import android.app.Application
import android.content.Context
import com.example.smartnutrition.data.manager.LocalUserMengerlmpl
import com.example.smartnutrition.data.manager.TokenManager
import com.example.smartnutrition.domain.manger.LocalUserManger
import com.example.smartnutrition.domain.usecases.app_entry.AppEntryUseCases
import com.example.smartnutrition.domain.usecases.app_entry.ReadAppEntry
import com.example.smartnutrition.domain.usecases.app_entry.SaveAppEntry
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
object AppModule {
    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManager(context)
    }
    @Provides
    @Singleton
    fun provideLocalUserManger(application: Application):LocalUserManger = LocalUserMengerlmpl(context = application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManger: LocalUserManger):AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )
}