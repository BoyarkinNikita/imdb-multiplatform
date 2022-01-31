package com.example.imdb.multiplatform.ui.global.movieDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha.high
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.imdb.multiplatform.res.string
import com.example.imdb.multiplatform.ui.application.appViewModel
import com.example.imdb.multiplatform.ui.composable.UrlImage

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel) {
    LaunchedEffect(Unit) {
        appViewModel.apply {
            showToolbar.value = true
            toolbarTitle.value = ""
            bottomBarNavigation.value = null
        }
    }

    val isLoading by viewModel.loading.collectAsState()
    val details by viewModel.details.collectAsState()

    val item = details

    if (isLoading || item == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            )
        }

        return
    }

    LaunchedEffect(item) {
        appViewModel.toolbarTitle.value = item.title
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(all = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UrlImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp),
                url = item.posterUrl,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .padding(start = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    item.title.trim().takeIf { it.isNotBlank() }
                        ?.let { type ->
                            Text(
                                modifier = Modifier.wrapContentSize(),
                                text = type,
                                style = typography.h6,
                                color = colors.onSurface.copy(alpha = high)
                            )
                        }

                    item.year?.trim()?.takeIf { it.isNotBlank() }
                        ?.let { year ->
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .wrapContentHeight()
                                    .padding(start = 8.dp),
                                text = year,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = typography.h6,
                                color = colors.onSurface.copy(alpha = high)
                            )
                        }
                }

                item.description.trim().takeIf { it.isNotBlank() }
                    ?.let { description ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(top = 2.dp),
                            text = description,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                            style = typography.body1,
                            color = colors.onSurface.copy(alpha = high)
                        )
                    }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 2.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = colors.primary
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight()
                            .padding(start = 16.dp),
                    ) {
                        item.rating?.trim()?.takeIf { it.isNotBlank() }
                            ?.let { string.movieDetailsRating(it) }
                            ?.let { rating ->
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    text = rating,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    style = typography.h6,
                                    color = colors.onSurface.copy(alpha = high)
                                )
                            }

                        item.votes?.trim()?.takeIf { it.isNotBlank() }
                            ?.let { votes ->
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    text = votes,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    style = typography.caption,
                                    color = colors.onSurface.copy(alpha = medium)
                                )
                            }
                    }
                }
            }
        }

        val positionWithTitle = @Composable { title: String, text: String? ->
            text?.trim()?.takeIf { it.isNotBlank() }
                ?.let {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            modifier = Modifier.wrapContentSize(),
                            text = title,
                            style = typography.h6,
                            color = colors.onSurface.copy(alpha = high)
                        )

                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentHeight()
                                .padding(top = 4.dp),
                            text = text,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 4,
                            style = typography.body1,
                            color = colors.onSurface.copy(alpha = high)
                        )
                    }
                }

        }

        positionWithTitle(string.movieDetailsDirector, item.director)
        positionWithTitle(string.movieDetailsWriters, item.writers)
        positionWithTitle(string.movieDetailsActors, item.actors)

        item.plot?.trim()?.takeIf { it.isNotBlank() }
            ?.let { plot ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 8.dp),
                    text = plot,
                    style = typography.body1,
                    color = colors.onSurface.copy(alpha = high)
                )
            }
    }
}