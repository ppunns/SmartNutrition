package com.example.smartnutrition.domain.repository

import androidx.paging.PagingData
import com.example.smartnutrition.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}