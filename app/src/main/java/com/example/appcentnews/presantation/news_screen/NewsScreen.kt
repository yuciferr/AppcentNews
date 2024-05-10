package com.example.appcentnews.presantation.news_screen

import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.appcentnews.composables.ArticleItem
import com.example.appcentnews.composables.MainAppBar
import com.example.appcentnews.model.Article
import com.example.appcentnews.navigation.Screens
import com.google.gson.Gson

@Composable
fun NewsScreen(
    navController: NavController? = null,
    //viewModel: NewsViewModel = hiltViewModel()
) {
    val article = Article(
        title = "Study: The Maya blessed their ball courts in rituals with hallucinogenic plants",
        urlToImage = "https://phandroid.com/wp-content/uploads/2024/04/oneplus-watch2-blue.png",
        description = "eDNA analysis found traces of xtabentum, as well as lancewood, chili peppers, and joolchaje, in the soil of a ball court in Mexico.",
        author = "John Doe",
        publishedAt = "April 30, 2024",
        content = "The Maya civilization was a Mesoamerican civilization developed by the Maya peoples, and noted for its logosyllabic script—the most sophisticated and highly developed writing system in pre-Columbian Americas—as well as for its art, architecture, mathematics, calendar, and astronomical system. The Maya civilization developed in an area that encompasses southeas littoral plain.",
        url = "https://phandroid.com/wp-content/uploads/2024/04/oneplus-watch2-blue.png",
        source = null
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        MainAppBar(title = "Appcent News", isSearchBarVisible = true, onSearch = {})
        ArticleItem(
            article = article,
            onClick = {
                val json = Uri.encode(Gson().toJson(article))
                navController?.navigate("details/$json")
            }
        )


    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsScreenPreview() {
    NewsScreen()
}
