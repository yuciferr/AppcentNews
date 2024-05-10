package com.example.appcentnews.navigation

import com.example.appcentnews.model.Article

sealed class Screens(val route: String) {
    object NewsScreen : Screens("news_screen")
    object DetailScreen : Screens("detail_screen/{article}")
    object FavoriteScreen : Screens("favorite_screen")
    object NewsSourceScreen : Screens("news_source_screen")


}
