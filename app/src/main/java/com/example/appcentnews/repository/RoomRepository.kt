package com.example.appcentnews.repository

import com.example.appcentnews.db.ArticleDao
import com.example.appcentnews.model.Article
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val articleDao: ArticleDao
) {
    fun getFavoriteArticles(): List<Article?> {
        return articleDao.getAllArticles()
    }

    suspend fun addArticle(article: Article) {
        articleDao.addArticle(article)
    }

    suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }

    suspend fun isArticleExist(title: String, description: String): Boolean {
        return articleDao.isArticleExist(title, description)
    }
}
