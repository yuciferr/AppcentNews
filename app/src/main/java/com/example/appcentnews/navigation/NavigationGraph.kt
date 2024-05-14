@file:Suppress("DEPRECATION")

package com.example.appcentnews.navigation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appcentnews.composables.BottomBar
import com.example.appcentnews.model.Article
import com.example.appcentnews.model.AssetParamType
import com.example.appcentnews.presantation.detail_screen.DetailScreen
import com.example.appcentnews.presantation.favorites_screen.FavoritesScreen
import com.example.appcentnews.presantation.favorites_screen.FavoritesViewModel
import com.example.appcentnews.presantation.news_screen.NewsScreen
import com.example.appcentnews.presantation.news_screen.NewsViewModel
import com.example.appcentnews.presantation.news_source_screen.NewsSourceScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),

    ) {
    val newsViewModel : NewsViewModel = hiltViewModel()
    val favoritesViewModel : FavoritesViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screens.NewsScreen.route) {

        composable(Screens.NewsScreen.route) {
            Scaffold(
                bottomBar = { BottomBar(navController = navController) },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    NewsScreen(navController = navController, viewModel = newsViewModel)
                }
            }
        }

        composable(
            Screens.DetailScreen.route,
            arguments = listOf(
                navArgument("article") {
                    type = AssetParamType()
                }
            )
        ) {
            val article = it.arguments?.getParcelable<Article>("article")
            if (article != null) {
                DetailScreen(article = article, viewModel = favoritesViewModel ,navController = navController)
            } else {
                Log.e("NavigationGraph", "Article is null")
                Text("Article is null")
            }
        }

        composable(Screens.FavoriteScreen.route) {
            Scaffold(
                bottomBar = { BottomBar(navController = navController) },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    FavoritesScreen(navController = navController, viewModel = favoritesViewModel)
                }
            }
        }

        composable(
            Screens.NewsSourceScreen.route,
            arguments = listOf(
                navArgument("article") {
                    type = AssetParamType()
                }
            )
        ) {
            val article = it.arguments?.getParcelable<Article>("article")
            if (article != null) {
                NewsSourceScreen(article = article, navController = navController)
            } else {
                Log.e("NavigationGraph", "Article is null")
                Text("Article is null")
            }
        }

    }

}