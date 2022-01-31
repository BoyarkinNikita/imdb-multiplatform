package com.example.imdb.multiplatform.ui.global.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.imdb.multiplatform.res.icons.ImdbLogo
import com.example.imdb.multiplatform.ui.application.appViewModel

@Composable
fun SplashScreen(viewModel: SplashViewModel) {
    LaunchedEffect(Unit) {
        appViewModel.apply {
            showToolbar.value = false
            bottomBarNavigation.value = null
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.width(150.dp)
                .height(73.dp)
                .align(Alignment.Center),
            imageVector = Icons.Filled.ImdbLogo,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}