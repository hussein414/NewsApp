package com.example.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.data.local.db.NewsDao
import com.example.newsapp.data.model.dto.Article
import com.example.newsapp.data.source.NewsPagingSource
import com.example.newsapp.data.source.SearchNewsPagingSource
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(source: List<String>): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    source = source.joinToString(separator = ",")
                )
            }
        ).flow

    override fun searchNews(searchQuery: String, source: List<String>): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    searchQuery,
                    source = source.joinToString(separator = ",")
                )
            }
        ).flow

    override suspend fun upsertArticle(article: Article) = newsDao.upsert(article)

    override suspend fun deleteArticle(article: Article) = newsDao.delete(article)

    override fun getArticles(): Flow<List<Article>> = newsDao.getArticles()

    override suspend fun getArticle(url: String): Article? = newsDao.getArticle(url = url)
}