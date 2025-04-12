package com.example.smartnutrition.di

import android.app.Application
import com.example.smartnutrition.data.manager.LocalUserMengerlmpl
import com.example.smartnutrition.domain.manger.LocalUserManger
import com.example.smartnutrition.domain.usecases.app_entry.AppEntryUseCases
import com.example.smartnutrition.domain.usecases.app_entry.ReadAppEntry
import com.example.smartnutrition.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
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