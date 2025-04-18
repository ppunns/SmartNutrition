package com.example.smartnutrition.di

import android.app.Application
import android.content.Context
import androidx.camera.core.ImageCapture
import com.example.smartnutrition.data.manager.LocalUserMengerlmpl
import com.example.smartnutrition.data.remote.NewsAPI
import com.example.smartnutrition.data.repository.NewsRepositorylmpl
import com.example.smartnutrition.domain.manger.LocalUserManger
import com.example.smartnutrition.domain.repository.NewsRepository
import com.example.smartnutrition.domain.usecases.app_entry.AppEntryUseCases
import com.example.smartnutrition.domain.usecases.app_entry.ReadAppEntry
import com.example.smartnutrition.domain.usecases.app_entry.SaveAppEntry
import com.example.smartnutrition.domain.usecases.news.GetNews
import com.example.smartnutrition.domain.usecases.news.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsAPI
    ): NewsRepository = NewsRepositorylmpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsApi(): NewsAPI {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }
}