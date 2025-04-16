package com.example.smartnutrition.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import com.example.smartnutrition.domain.model.Article

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.smartnutrition.presentation.Dimens.MediumPadding1
import com.example.smartnutrition.presentation.common.FruitsCardList
import com.example.smartnutrition.presentation.home.components.CalorieProgressCard



@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate:(String) -> Unit
) {
    val titles = remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        CalorieProgressCard(
            modifier = Modifier.padding(horizontal = MediumPadding1)
        )

        FruitsCardList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            fruits = articles,
            onFruitClick = { article ->
                navigate(article.url)
            }
        )
    }
}




