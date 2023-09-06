package com.example.newsapp.module

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.api.NewsApi
import com.example.newsapp.data.local.db.NewsDao
import com.example.newsapp.data.local.db.NewsDatabase
import com.example.newsapp.data.local.db.NewsTypeConvertor
import com.example.newsapp.data.local.pref.manager.LocalUserManager
import com.example.newsapp.data.local.pref.manager.LocalUserManagerImpl
import com.example.newsapp.data.local.pref.state.ReadAppEntry
import com.example.newsapp.data.local.pref.state.SaveAppEntry
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.data.usecase.dto.AppEntryUseCases
import com.example.newsapp.data.usecase.dto.GetNews
import com.example.newsapp.data.usecase.NewsUseCases
import com.example.newsapp.data.usecase.db.DeleteArticle
import com.example.newsapp.data.usecase.db.GetSavedArticle
import com.example.newsapp.data.usecase.db.GetSavedArticles
import com.example.newsapp.data.usecase.db.UpsertArticle
import com.example.newsapp.data.usecase.dto.SearchNews
import com.example.newsapp.utils.Constance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesLocalManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Singleton
    @Provides
    fun provideAppEntryUsesCases(localUserManager: LocalUserManager) =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )


    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi =
        Retrofit.Builder().baseUrl(Constance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)

    @Provides
    @Singleton
    fun providesRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository =
        NewsRepositoryImpl(newsApi, newsDao)

    @Singleton
    @Provides
    fun provideAppNewsUsesCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases =
        NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            getSavedArticle = GetSavedArticle(newsDao),
            getSavedArticles = GetSavedArticles(newsDao)
        )


    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase = Room.databaseBuilder(
        context = application,
        klass = NewsDatabase::class.java,
        name = "news_db"
    ).addTypeConverter(NewsTypeConvertor())
        .fallbackToDestructiveMigration()
        .build()



    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}

