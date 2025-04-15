package com.example.smartnutrition.data.remote.dto

import com.example.smartnutrition.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)