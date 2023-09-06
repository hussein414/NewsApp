package com.example.newsapp.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.data.model.dto.Article
import retrofit2.http.Query
import java.lang.Exception

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val source: String,
) : PagingSource<Int, Article>() {
    private var totalCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.searchNews(searchQuery, page, source)
            totalCount += newsResponse.articles.size
            val article = newsResponse.articles.distinctBy { it.title }//remove duplicated
            LoadResult.Page(
                data = article,
                nextKey = if (totalCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }
}