package com.example.appcentnews.presantation.news_screen

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appcentnews.R
import com.example.appcentnews.composables.ArticleItem
import com.example.appcentnews.composables.MainAppBar
import com.example.appcentnews.composables.WarningScreen
import com.example.appcentnews.util.NetworkUtils
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(
    navController: NavController? = null,
    context: Context = LocalContext.current,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val newsResponse = viewModel.newsResponse.value
    var articles = viewModel.articles.value
    var searchQuery by remember { mutableStateOf("besiktas") }


    var isNetworkAvailable by remember {
        mutableStateOf(NetworkUtils.isNetworkAvailable(context))
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            isNetworkAvailable = NetworkUtils.isNetworkAvailable(context)
            if(isNetworkAvailable){
                if (articles.isEmpty()) {
                    isRefreshing = true
                    viewModel.searchForNews(searchQuery)
                    articles = viewModel.articles.value
                    isRefreshing = false
                }
            }
        }
    }

    LaunchedEffect(newsResponse) {
        if (isNetworkAvailable) {
            if (newsResponse == null) {
                isRefreshing = true
                scope.launch {
                    viewModel.searchForNews(searchQuery)
                    articles = viewModel.articles.value
                    isRefreshing = false
                }
            } else {
                isRefreshing = false
            }
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
                    isNetworkAvailable = NetworkUtils.isNetworkAvailable(context)
                    if (isNetworkAvailable) {
                        isRefreshing = true
                        delay(500)
                        viewModel.searchForNews(it)
                        articles = viewModel.articles.value
                        isRefreshing = false
                        searchQuery = it
                    } else {
                        Toast.makeText(context, "No internet connection found.", Toast.LENGTH_SHORT)
                            .show()
                    }
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
            if (!isNetworkAvailable && articles.isEmpty()) {
                WarningScreen(
                    image = R.drawable.no_internet_connection,
                    message = "No internet connection found."
                )

            } else {
                LazyColumn {
                    itemsIndexed(articles) { index, article ->
                        article?.let {
                            ArticleItem(article = it, onClick = {
                                val json = Uri.encode(Gson().toJson(article))
                                navController?.navigate("detail_screen/$json")
                            })
                        }
                        if (index == articles.size - 1 && isNetworkAvailable) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                            }
                            viewModel.loadMoreNews(searchQuery)
                        }
                    }


                }
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsScreenPreview() {

}
