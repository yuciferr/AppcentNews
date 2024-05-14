package com.example.appcentnews.presantation.news_screen


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appcentnews.model.Article
import com.example.appcentnews.model.NewsResponse
import com.example.appcentnews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val newsResponse: MutableLiveData<NewsResponse> = MutableLiveData()
    val articles = mutableStateOf<List<Article?>>(listOf())
    var pageNumber = 1

    suspend fun searchForNews(searchQuery: String) {
        newsResponse.value = newsRepository.searchForNews(searchQuery, pageNumber).body()
        articles.value = newsResponse.value?.articles!!
        //filterRemovedArticles()
    }

    private fun filterRemovedArticles() {
        val filteredArticles = articles.value.filter { it?.title != "[Removed]" }
        articles.value = filteredArticles
    }

    fun loadMoreNews(searchQuery: String) {
        pageNumber++
        viewModelScope.launch {
            val response = newsRepository.searchForNews(searchQuery, pageNumber)
            if (response.isSuccessful) {
                newsResponse.value = response.body()
                delay(500)
                articles.value += newsResponse.value?.articles!!
                //filterRemovedArticles()
            }
        }
    }


}