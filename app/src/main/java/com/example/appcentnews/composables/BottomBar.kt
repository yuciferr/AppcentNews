package com.example.appcentnews.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appcentnews.navigation.Screens

@Composable
fun BottomBar(navController: NavController? = null) {
    BottomAppBar(
        containerColor = Color(0xFFFAFAFA)
    ) {
        NavigationBarItem(
            label = {
                Text(
                    text = "Favorites",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.sp
                    )
                )
            },
            selected = navController?.currentDestination?.route == Screens.NewsScreen.route,
            onClick = {
                if (navController?.currentDestination?.route != Screens.NewsScreen.route) {
                    navController?.navigate(Screens.NewsScreen.route) {
                        popUpTo(Screens.NewsScreen.route) {
                            inclusive = true
                        }
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = if (navController?.currentDestination?.route == Screens.NewsScreen.route) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home",
                    tint = if (navController?.currentDestination?.route == Screens.NewsScreen.route) Color.White else Color.LightGray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                indicatorColor = Color.Transparent,
                unselectedTextColor = Color.LightGray
            )
        )
        NavigationBarItem(
            label = {
                Text(
                    text = "Favorites",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.sp
                    )
                )
            },
            selected = navController?.currentDestination?.route == Screens.FavoriteScreen.route,
            onClick = {
                if (navController?.currentDestination?.route != Screens.FavoriteScreen.route) {
                    navController?.navigate(Screens.FavoriteScreen.route) {
                        popUpTo(Screens.FavoriteScreen.route) {
                            inclusive = true
                        }
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = if (navController?.currentDestination?.route == Screens.FavoriteScreen.route) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = "Favorites",
                    tint = if (navController?.currentDestination?.route == Screens.FavoriteScreen.route) Color.White else Color.LightGray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                selectedTextColor = Color.Black,
                indicatorColor = Color.Transparent,
                unselectedTextColor = Color.LightGray
            )
        )
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar()
}
