package com.example.appcentnews.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcentnews.ui.theme.NewsReader

@Composable
fun MainAppBar(
    title: String,
    isSearchBarVisible: Boolean = false,
    onSearch: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = title,
            style = TextStyle(
                fontFamily = NewsReader,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
            )
        )
        if (isSearchBarVisible) {
            SearchBar(onSearch = onSearch)
            Spacer(modifier = Modifier.padding(8.dp))
        }


    }

}

@Composable
fun DetailAppBar(
    title: String,
    onBack: () -> Unit,
    onFavorite: () -> Unit,
    isFavorite: Boolean,
    onShare: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBack() }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
        }
        Text(
            text = title,
            style = TextStyle(
                fontFamily = NewsReader,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { onShare() }) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
        }
        IconButton(onClick = { onFavorite() }) {
            if (isFavorite) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
            }
        }


    }

}

@Preview
@Composable
fun MainAppBarPreview() {
    DetailAppBar(
        title = "News Detail",
        onBack = {},
        onFavorite = {},
        isFavorite = true,
        onShare = {}
    )
}