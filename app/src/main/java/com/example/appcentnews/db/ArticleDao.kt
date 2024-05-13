package com.example.appcentnews.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appcentnews.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: Article): Long

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<Article?>

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT EXISTS(SELECT 1 FROM articles WHERE title = :title AND description = :description)")
    suspend fun isArticleExist(title: String, description: String): Boolean

}