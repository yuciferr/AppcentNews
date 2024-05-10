package com.example.appcentnews.presantation.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.appcentnews.R
import com.example.appcentnews.composables.DetailAppBar
import com.example.appcentnews.model.Article
import com.example.appcentnews.ui.theme.NewsReader

@Composable
fun DetailScreen(
    article: Article,
    navController: NavController? = null
) {

    val painter = rememberImagePainter(
        data = article.urlToImage,
        builder = {
            crossfade(true)
            placeholder(R.drawable.placeholder)
            error(R.drawable.error_placeholder)
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    )
    {
        DetailAppBar(
            title = "News Detail",
            onBack = { navController?.popBackStack() },
            onFavorite = { /*TODO*/ },
            onShare = { /*TODO*/ },
            isFavorite = false
        )

        if (article.urlToImage != null) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = article.author!!,
                style = TextStyle(
                    fontFamily = NewsReader,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp
                ),
                color = Color.Gray,
                modifier = Modifier.padding(end = 8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = article.publishedAt!!,
                style = TextStyle(
                    fontFamily = NewsReader,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp
                ),
                color = Color.Gray,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.title!!,
            style = TextStyle(
                fontFamily = NewsReader,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.sp
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (article.content?.isNotEmpty() == true) {
            Text(
                text = article.content,
                modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF131313),
                contentColor = Color.White,
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(
                text = "Read More",
                style = TextStyle(
                    fontFamily = NewsReader,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp
                ),
                color = Color.White,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            )

        }
        Spacer(modifier = Modifier.height(16.dp))

    }

}


@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        article = Article(
            title = "Study: The Maya blessed their ball courts in rituals with hallucinogenic plants",
            urlToImage = "https://phandroid.com/wp-content/uploads/2024/04/oneplus-watch2-blue.png",
            description = "eDNA analysis found traces of xtabentum, as well as lancewood, chili peppers, and joolchaje, in the soil of a ball court in Mexico.",
            author = "John Doe",
            publishedAt = "April 30, 2024",
            content = "The Maya civilization was a Mesoamerican civilization developed by the Maya peoples, and noted for its logosyllabic script—the most sophisticated and highly developed writing system in pre-Columbian Americas—as well as for its art, architecture, mathematics, calendar, and astronomical system. The Maya civilization developed in an area that encompasses southeastern Mexico, all of Guatemala and Belize, and the western portions of Honduras and El Salvador. This region consists of the northern lowlands encompassing the Yucatán Peninsula, and the highlands of the Sierra Madre, running from the Mexican state of Chiapas, across southern Guatemala and onwards into El Salvador, and the southern lowlands of the Pacific littoral plain.",
            url = "https://phandroid.com/wp-content/uploads/2024/04/oneplus-watch2-blue.png",
            source = null
        )
    )
}