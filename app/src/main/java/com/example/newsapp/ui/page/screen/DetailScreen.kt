package com.example.newsapp.ui.page.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.newsapp.data.model.dto.Article
import com.example.newsapp.ui.event.DetailsEvent
import com.example.newsapp.ui.page.component.DetailsTopBar
import com.example.newsapp.ui.theme.Body
import com.example.newsapp.ui.theme.TextTitle
import com.example.newsapp.utils.Constance
import com.example.newsapp.utils.Instance
import com.example.newsapp.utils.UIComponent

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    sideEffect: UIComponent?,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when (sideEffect) {
                is UIComponent.Toast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    event(DetailsEvent.RemoveSideEffect)
                }

                else -> Unit
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                setBrowser(context, article)
            },
            onShareClick = {
                setShare(context = context, article = article)
            },
            onBockMark = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = Constance.MediumPadding1,
                end = Constance.MediumPadding1,
                top = Constance.MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Constance.ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(Constance.MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = TextTitle
                )
                Spacer(modifier = Modifier.height(Constance.MediumPadding1))

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Body
                )
            }
        }
    }
}

fun setShare(context: Context, article: Article) {
    Intent(Intent.ACTION_SEND).also {
        it.putExtra(Intent.EXTRA_TEXT, article.url)
        it.type = "text/plain"
        if (it.resolveActivity(context.packageManager) != null) {
            context.startActivity(it)
        }
    }
}

@SuppressLint("QueryPermissionsNeeded")
fun setBrowser(context: Context, article: Article) {
    Intent(Intent.ACTION_VIEW).also {
        it.data = Uri.parse(article.url)
        if (it.resolveActivity(context.packageManager) != null) {
            context.startActivity(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        article = Instance.article,
        sideEffect = null,
        event = {}
    ) {

    }
}

