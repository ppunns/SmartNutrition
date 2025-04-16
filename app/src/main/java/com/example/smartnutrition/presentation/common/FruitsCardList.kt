package com.example.smartnutrition.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.smartnutrition.domain.model.Article
import com.example.smartnutrition.presentation.Dimens.ExtraSmallPadding
import com.example.smartnutrition.presentation.Dimens.ExtraSmallPadding2
import com.example.smartnutrition.presentation.Dimens.MediumPadding1
import com.example.smartnutrition.presentation.home.components.FruitsCard
import com.example.smartnutrition.util.Constans
import com.loc.newsapp.presentation.common.FruitsCardShimmerEffect

@Composable
fun FruitsCardList(
    modifier: Modifier = Modifier,
    fruits: LazyPagingItems<Article>,
    onFruitClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(fruits = fruits)

    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            contentPadding = PaddingValues(all = ExtraSmallPadding2),
            verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Top)

        ) {
            items(count = fruits.itemCount){
                fruits[it]?.let { article ->
                    FruitsCard(article = article, onClick = {onFruitClick(article)})
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    fruits: LazyPagingItems<Article>
): Boolean {
    val loadState = fruits.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {
            EmptyScreen(error = error)
            false
        }
        else -> true
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)){
        repeat(10){
            FruitsCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}