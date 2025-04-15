package com.example.smartnutrition.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.smartnutrition.domain.model.Article
import com.example.smartnutrition.util.Constans.API_KEY

class NewsPagingSource(
    private val newsApi: NewsAPI,
    private val sources: String
):PagingSource<Int,Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1

            val response = newsApi.getNews(
                page = page,
                sources = sources,
                apiKey = API_KEY
            )
            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.articles.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}