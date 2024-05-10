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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.example.appcentnews.presantation.news_screen.NewsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),

    ) {
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
                    NewsScreen(navController = navController)
                }
            }
        }

        composable(
            "details/{article}",
            arguments = listOf(
                navArgument("article") {
                    type = AssetParamType()
                }
            )
        ) {
            val article = it.arguments?.getParcelable<Article>("article")
            if (article != null) {
                DetailScreen(article = article, navController = navController)
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
                    FavoritesScreen(navController = navController)
                }
            }
        }

        composable(Screens.NewsSourceScreen.route) {
            // NewsSourceScreen(navController = navController)
        }

    }

}