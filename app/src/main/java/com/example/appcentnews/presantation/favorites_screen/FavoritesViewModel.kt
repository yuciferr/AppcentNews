package com.example.appcentnews.presantation.favorites_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcentnews.model.Article
import com.example.appcentnews.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    val articles = MutableLiveData<List<Article?>>()

    suspend fun getFavoriteArticles(): List<Article?> {
        articles.value = roomRepository.getFavoriteArticles()
        return articles.value!!
    }

    suspend fun addArticle(article: Article) {
        roomRepository.addArticle(article)
    }

    suspend fun deleteArticle(article: Article) {
        roomRepository.deleteArticle(article)
    }

    suspend fun isFavorite(title: String, description: String): Boolean {
        return roomRepository.isArticleExist(title, description)
    }
}