package com.example.appcentnews.navigation

sealed class Screens(val route: String) {
    object NewsScreen : Screens("news_screen")
    object DetailScreen : Screens("detail_screen")
    object FavoriteScreen : Screens("favorite_screen")
    object NewsSourceScreen : Screens("news_source_screen")

}
