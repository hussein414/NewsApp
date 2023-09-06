package com.example.newsapp.ui.page.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.data.model.dto.Article
import com.example.newsapp.ui.page.screen.EmptyScreen
import com.example.newsapp.ui.page.shimmer.ArticleCardShimmer
import com.example.newsapp.utils.Constance
import com.example.newsapp.utils.Constance.ExtraSmallPadding2
import com.example.newsapp.utils.Constance.MediumPadding1

@Composable
fun ArticleList(
    modifier: Modifier = Modifier, article: LazyPagingItems<Article>, onClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(article = article)
    if (handlePagingResult) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Constance.MediumPadding1),
            contentPadding = PaddingValues(all = Constance.ExtraSmallPadding)
        ) {
            items(count = article.itemCount) { it ->
                article[it]?.let {
                    ArticleCard(article = it, onClick = { onClick(it) })
                }
            }
        }
    }
}

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    if (articles.isEmpty()){
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {
        items(
            count = articles.size,
        ) {
            articles[it]?.let { article ->
                ArticleCard(article = article, onClick = { onClick(article) })
            }
        }
    }

}
@Composable
fun handlePagingResult(article: LazyPagingItems<Article>): Boolean {
    val loadState = article.loadState
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
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Constance.MediumPadding1)) {
        repeat(10) {
            ArticleCardShimmer(
                modifier = Modifier.padding(horizontal = Constance.MediumPadding1)
            )
        }
    }
}