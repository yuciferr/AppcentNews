package com.example.appcentnews.repository

import com.example.appcentnews.api.NewsService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsService
) {
    suspend fun getBreakingNews(countryCode: String = "tr", pageNumber: Int) =
        newsService.getBreakingNews(countryCode, pageNumber)

    suspend fun searchForNews(searchQuery: String, pageNumber: Int) =
        newsService.searchForNews(searchQuery, pageNumber)

}