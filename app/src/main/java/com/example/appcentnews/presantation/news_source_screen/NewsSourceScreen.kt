package com.example.appcentnews.presantation.news_source_screen

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.appcentnews.composables.DetailAppBar
import com.example.appcentnews.model.Article


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsSourceScreen(
    article : Article,
    navController: NavController? = null
) {
    Scaffold(
        topBar = {
            DetailAppBar(
                title = "Source",
                onBack = { navController?.popBackStack() },
                hideButtons = true
            )
        }

    ) {
        Box(Modifier.padding(it)) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        webViewClient = WebViewClient()

                        settings.loadWithOverviewMode = true
                        settings.useWideViewPort = true
                        settings.setSupportZoom(true)
                    }
                },
                update = { webView ->
                    article.url?.let { it1 -> webView.loadUrl(it1) }
                }
            )
        }
    }
}