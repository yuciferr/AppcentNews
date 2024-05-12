package com.example.appcentnews.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.example.appcentnews.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = databaseBuilder(
        context,
        ArticleDatabase::class.java,
        "article_db"
    ).build()

    @Singleton
    @Provides
    fun provideArticleDao(db: ArticleDatabase) = db.articleDao()


}