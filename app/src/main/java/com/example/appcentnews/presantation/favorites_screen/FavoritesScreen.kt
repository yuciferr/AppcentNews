package com.example.appcentnews.presantation.favorites_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.appcentnews.composables.ArticleItem
import com.example.appcentnews.composables.MainAppBar

@Composable
fun FavoritesScreen(
    navController: NavController? = null,
    //viewModel: FavoritesViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        MainAppBar(title = "Favorites", onSearch = {})
        ArticleItem(
            title = "Study: The Maya blessed their ball courts in rituals with hallucinogenic plants",
            imageUrl = "https://phandroid.com/wp-content/uploads/2024/04/oneplus-watch2-blue.png",
            description = "eDNA analysis found traces of xtabentum, as well as lancewood, chili peppers, and joolchaje, in the soil of a ball court in Mexico."
        )
        ArticleItem(
            title = "Study: The Maya blessed their ball courts in rituals with hallucinogenic plants",
            imageUrl = "https://phandroid.com/wp-content/uploads/2024/04/oneplus-watch2-blue.png",
            description = "eDNA analysis found traces of xtabentum, as well as lancewood, chili peppers, and joolchaje, in the soil of a ball court in Mexico."
        )
        ArticleItem(
            title = "Study: The Maya blessed their ball courts in rituals with hallucinogenic plants",
            imageUrl = "https://phandroid.com/wp-content/uploads/2024/04/oneplus-watch2-blue.png",
            description = "eDNA analysis found traces of xtabentum, as well as lancewood, chili peppers, and joolchaje, in the soil of a ball court in Mexico."
        )
        ArticleItem(
            title = "Study: The Maya blessed their ball courts in rituals with hallucinogenic plants",
            imageUrl = "https://phandroid.com/wp-content/uploads/2024/04/oneplus-watch2-blue.png",
            description = "eDNA analysis found traces of xtabentum, as well as lancewood, chili peppers, and joolchaje, in the soil of a ball court in Mexico."
        )


    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FavoritesScreenPreview() {
    FavoritesScreen()
}
