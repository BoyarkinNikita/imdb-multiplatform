package com.example.imdb.multiplatform.ui.global.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.pop
import com.example.imdb.multiplatform.res.icons.Movies
import com.example.imdb.multiplatform.res.icons.News
import com.example.imdb.multiplatform.res.strings.Strings.Companion.strings
import com.example.imdb.multiplatform.ui.application.appViewModel
import com.example.imdb.multiplatform.ui.bottom.BottomDestination
import com.example.imdb.multiplatform.ui.bottom.movies.MoviesDestination
import com.example.imdb.multiplatform.ui.bottom.navigateToMovies
import com.example.imdb.multiplatform.ui.bottom.navigateToNews
import com.example.imdb.multiplatform.ui.bottom.news.NewsDestination
import com.example.imdb.multiplatform.ui.global.GlobalDestination
import com.example.imdb.multiplatform.ui.navigation.BottomNavigation
import com.example.imdb.multiplatform.ui.navigation.NavigationViewModel
import com.example.imdb.multiplatform.ui.navigation.subscribeAsState

class MainViewModel(
    componentContext: ComponentContext,
    destination: MainDestination
) : NavigationViewModel<BottomDestination, GlobalDestination>(
    componentContext = componentContext,
    destination = destination,
    routerDestinationKlass = BottomDestination::class,
    startDestination = MoviesDestination(),
    parentViewModel = appViewModel
), BottomNavigation {
    override fun handleBackPressed(): Boolean = with(router.state.value) {
        when {
            backStack.isEmpty() && activeChild.configuration is MoviesDestination -> false

            backStack.isEmpty() && activeChild.configuration is NewsDestination -> {
                router.navigate { listOf(MoviesDestination()) }
                true
            }

            else -> {
                router.pop()
                true
            }
        }
    }

    override fun canGoBack(): Boolean =
        router.state.value.run { backStack.isNotEmpty() || activeChild.configuration is NewsDestination }

    @Composable
    override fun BottomItems(rowScope: RowScope) = with(rowScope) {
        val bottomRouter by router.state.subscribeAsState()

        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Movies, null) },
            label = { Text(text = strings.mainMenuMovies) },
            selected = bottomRouter.activeChild.configuration is MoviesDestination,
            onClick = { navigateToMovies() }
        )

        BottomNavigationItem(
            icon = { Icon(Icons.Filled.News, null) },
            label = { Text(text = strings.mainMenuNews) },
            selected = bottomRouter.activeChild.configuration is NewsDestination,
            onClick = { navigateToNews() }
        )
    }
}