package com.example.appcentnews.presantation.news_screen

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appcentnews.composables.ArticleItem
import com.example.appcentnews.composables.MainAppBar
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(
    navController: NavController? = null,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(true) }
    val newsResponse = viewModel.newsResponse.value
    var articles = viewModel.articles.value
    var searchQuery by remember { mutableStateOf("besiktas") }

    LaunchedEffect(newsResponse) {
        if (newsResponse == null) {
            scope.launch {
                viewModel.searchForNews(searchQuery)
                articles = viewModel.articles.value
                isRefreshing = false
            }
        } else {
            isRefreshing = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        MainAppBar(
            title = "Appcent News",
            isSearchBarVisible = true,
            onSearch = {
                scope.launch {
                    isRefreshing = true
                    delay(500)
                    viewModel.searchForNews(it)
                    articles = viewModel.articles.value
                    isRefreshing = false
                    searchQuery = it
                }
            })


        if (isRefreshing) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                itemsIndexed(articles) { index, article ->
                    article?.let {
                        ArticleItem(article = it, onClick = {
                            val json = Uri.encode(Gson().toJson(article))
                            navController?.navigate("detail_screen/$json")
                        })
                    }
                    if (index == articles.size - 1) {
                        viewModel.loadMoreNews(searchQuery)
                    }
                }


            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsScreenPreview() {
    NewsScreen()
}
