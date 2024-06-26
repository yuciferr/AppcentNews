package com.example.appcentnews.presantation.favorites_screen

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
import com.example.appcentnews.R
import com.example.appcentnews.composables.ArticleItem
import com.example.appcentnews.composables.MainAppBar
import com.example.appcentnews.composables.WarningScreen
import com.example.appcentnews.model.Article
import com.google.gson.Gson
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(
    navController: NavController? = null,
    viewModel: FavoritesViewModel = hiltViewModel()
) {

    var articles: List<Article?> = viewModel.articles.value ?: emptyList()
    var isRefreshing by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel.articles.value) {
        scope.launch {
            articles = viewModel.getFavoriteArticles()
            isRefreshing = false
        }
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        MainAppBar(title = "Favorites", onSearch = {})
        if (isRefreshing) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            if (articles.isEmpty()) {

                WarningScreen(
                    image = R.drawable.add_favorite,
                    message = "You don't have any favorite articles yet"
                )

            } else {
                LazyColumn {
                    itemsIndexed(articles) { index, article ->
                        if (article != null) {
                            ArticleItem(
                                article = article,
                                onClick = {
                                    val json = Uri.encode(Gson().toJson(article))
                                    navController?.navigate("detail_screen/$json")
                                }
                            )
                        }
                    }
                }
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen()
}
