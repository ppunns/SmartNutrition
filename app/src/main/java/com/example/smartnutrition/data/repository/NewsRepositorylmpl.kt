package com.example.smartnutrition.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.smartnutrition.data.remote.NewsAPI
import com.example.smartnutrition.data.remote.NewsPagingSource
import com.example.smartnutrition.domain.model.Article
import com.example.smartnutrition.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositorylmpl(
    private val newsApi: NewsAPI
    ) : NewsRepository {
        override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
            return Pager(
                config = PagingConfig(pageSize = 10),
                pagingSourceFactory = {
                    NewsPagingSource(
                        newsApi = newsApi,
                        sources = sources.joinToString(separator = ",")
                    )
                }
            ).flow
        }
}