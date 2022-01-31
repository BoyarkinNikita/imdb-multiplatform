package com.example.imdb.multiplatform.ui.bottom.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ContentAlpha.high
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.imdb.multiplatform.data.ImdbContentType.MOVIE
import com.example.imdb.multiplatform.data.ImdbContentType.SERIES
import com.example.imdb.multiplatform.data.search.ImdbShortItem
import com.example.imdb.multiplatform.res.strings.Strings.Companion.strings
import com.example.imdb.multiplatform.ui.application.appViewModel
import com.example.imdb.multiplatform.ui.composable.UrlImage
import com.example.imdb.multiplatform.ui.global.navigateToMovieDetails

@Composable
fun MoviesScreen(viewModel: MoviesViewModel) {
    val title = strings.mainMenuMovies

    LaunchedEffect(Unit) {
        appViewModel.apply {
            showToolbar.value = true
            toolbarTitle.value = title
        }
    }

    val stateLazyList = rememberLazyListState()
    val isLoading by viewModel.loading.collectAsState()
    val movies by viewModel.movies.collectAsState()

    val scrollNearToEnd by remember(stateLazyList, movies) {
        derivedStateOf {
            if (movies.isNotEmpty()) {
                stateLazyList.layoutInfo.visibleItemsInfo.maxOfOrNull { it.index }
                    .let { it != null && it >= movies.size - 3 }
            } else false
        }
    }

    if (scrollNearToEnd) viewModel.loadMovies()

    if (isLoading && movies.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            )
        }

        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = stateLazyList,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = movies,
            key = { it.imdbId }
        ) { item ->
            ImdbListItem(
                viewModel = viewModel,
                item = item
            )
        }

        if (isLoading && movies.isNotEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(all = 8.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun ImdbListItem(
    viewModel: MoviesViewModel,
    item: ImdbShortItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { viewModel.navigateToMovieDetails(item.imdbId) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UrlImage(
                modifier = Modifier
                    .width(67.dp)
                    .height(100.dp),
                url = item.posterUrl,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .padding(start = 8.dp)
            ) {
                item.title.trim().takeIf { it.isNotBlank() }
                    ?.let { title ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            text = title,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            style = typography.h6,
                            color = colors.onSurface.copy(alpha = high)
                        )
                    }

                item.type.let {
                    when (it) {
                        SERIES -> strings.imdbTypeSeries
                        MOVIE -> strings.imdbTypeMovie
                    }
                }.let { type ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 4.dp),
                        text = type,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = typography.caption,
                        color = colors.onSurface.copy(alpha = medium)
                    )
                }

                item.year?.trim()?.takeIf { it.isNotBlank() }
                    ?.let { year ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            text = year,
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