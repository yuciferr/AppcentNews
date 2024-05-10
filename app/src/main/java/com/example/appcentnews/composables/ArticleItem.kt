package com.example.appcentnews.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.appcentnews.R
import com.example.appcentnews.ui.theme.NewsReader

@Composable
fun ArticleItem(
    title: String,
    imageUrl: String? = null,
    description: String = "",
) {

    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            transformations(
                RoundedCornersTransformation(50f)
            )
            crossfade(true)
            placeholder(R.drawable.placeholder)
            error(R.drawable.error_placeholder)

        }
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp, 4.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            if (imageUrl != null) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(142.dp)
                        .weight(0.4f)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = NewsReader,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        maxLines = 3, // Show only 2 lines of content
                        overflow = TextOverflow.Ellipsis, // Add ellipsis (...) if content overflows
                        modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun ArticleItemPreview() {
    Column {
        ArticleItem(
            title = "Study: The Maya blessed their ball courts in rituals with hallucinogenic plants",
            imageUrl = "https://phandroid.com/wp-content/uploads/2024/04/oneplus-watch2-blue.png",
            description = "eDNA analysis found traces of xtabentum, as well as lancewood, chili peppers, and joolchaje, in the soil of a ball court in Mexico."
        )
    }
}