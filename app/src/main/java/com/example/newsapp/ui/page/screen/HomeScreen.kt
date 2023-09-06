package com.example.newsapp.ui.page.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.data.model.dto.Article
import com.example.newsapp.ui.navigation.Route
import com.example.newsapp.ui.page.component.ArticleList
import com.example.newsapp.ui.page.component.SearchBar
import com.example.newsapp.ui.theme.PlaceHolder
import com.example.newsapp.ui.viewmodel.HomeViewModel
import com.example.newsapp.utils.Constance

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    val title by remember {
        derivedStateOf {
            if (article.itemCount > 10) {
                article.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "\uD83d\uDFE5") { it.title }
            } else {
                ""
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Constance.MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .padding(horizontal = Constance.MediumPadding1),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(Constance.MediumPadding1))

        SearchBar(
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = navigateToSearch,
            onSearch = {},
            modifier = Modifier.padding(all = 16.dp)
        )

        Spacer(modifier = Modifier.height(Constance.MediumPadding1))

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Constance.MediumPadding1)
                .basicMarquee(),
            fontSize = 16.sp,
            color = PlaceHolder
        )
        Spacer(modifier = Modifier.height(Constance.MediumPadding1))

        ArticleList(
            modifier = Modifier.padding(horizontal = Constance.MediumPadding1),
            article = article,
            onClick = navigateToDetails
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {

}