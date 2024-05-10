@file:Suppress("UNUSED_EXPRESSION")

package com.example.appcentnews.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appcentnews.composables.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),

    ) {
    val selectedItem = remember { mutableStateOf(0) }
    val bottomBar = BottomBar(selectedItem = selectedItem, navController = navController)

    NavHost(navController = navController, startDestination = Screens.NewsScreen.route) {

        composable(Screens.NewsScreen.route) {
            Scaffold(
                bottomBar = { bottomBar },
               ){
                Box(modifier = Modifier.fillMaxSize().padding(it)) {
                    // NewsScreen(navController = navController)
                }
            }
        }

        composable(Screens.DetailScreen.route) {
            Scaffold(
                bottomBar = { bottomBar },
            ){
                Box(modifier = Modifier.fillMaxSize().padding(it)) {
                    // NewsScreen(navController = navController)
                }
            }
        }

        composable(Screens.FavoriteScreen.route) {
            // FavoriteScreen(navController = navController)
        }

        composable(Screens.NewsSourceScreen.route) {
            // NewsSourceScreen(navController = navController)
        }

    }

}