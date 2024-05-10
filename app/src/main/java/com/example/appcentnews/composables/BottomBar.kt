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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.appcentnews.navigation.Screens

@Composable
fun BottomBar(selectedItem: MutableState<Int>, navController: NavController? = null) {
    BottomAppBar(
        containerColor = Color(0xFFFAFAFA)
    ) {
        NavigationBarItem(
            label = { Text("Home") },
            selected = selectedItem.value == 0,
            onClick = {
                selectedItem.value = 0
                navController?.navigate(Screens.NewsScreen.route)
            },
            icon = {
                Icon(
                    imageVector = if (selectedItem.value == 0) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = "Home",
                    tint = if (selectedItem.value == 0) Color.White else Color.LightGray
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
            label = { Text("Favorites") },
            selected = selectedItem.value == 1,
            onClick = {
                selectedItem.value = 1
                navController?.navigate(Screens.FavoriteScreen.route)
            },
            icon = {
                Icon(
                    imageVector = if (selectedItem.value == 1) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = "Favorites",
                    tint = if (selectedItem.value == 1) Color.White else Color.LightGray
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
    val selectedItem = remember { mutableStateOf(1) }
    BottomBar(selectedItem = selectedItem)
}
