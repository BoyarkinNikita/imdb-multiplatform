package com.example.imdb.multiplatform.ui.application

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.example.imdb.multiplatform.res.theme.AppTheme
import com.example.imdb.multiplatform.ui.navigation.NavigationContainer
import com.example.imdb.multiplatform.ui.navigation.subscribeAsState

@Composable
fun ApplicationEntry(componentContext: ComponentContext) {
    ApplicationViewModel.initAppViewModel(componentContext)

    AppTheme {
        val scaffoldState = rememberScaffoldState()

        val appRouter by appViewModel.router.state.subscribeAsState()
        val bottomBarNavigation by appViewModel.bottomBarNavigation.collectAsState()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                val showToolbar by appViewModel.showToolbar.collectAsState()
                val toolbarTitle by appViewModel.toolbarTitle.collectAsState()
                val canNavigateBack by appViewModel.canNavigateBack.collectAsState()

                if (showToolbar) {
                    TopAppBar {
                        if (canNavigateBack) {
                            IconButton(onClick = { appViewModel.navigateBack() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }

                        toolbarTitle?.trim()?.takeIf { it.isNotBlank() }
                            ?.let { title ->
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .wrapContentHeight()
                                        .padding(start = 16.dp),
                                    text = title,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1,
                                    style = typography.h6.copy(fontWeight = FontWeight.Medium)
                                )
                            }
                    }
                }
            },
            bottomBar = {
                bottomBarNavigation?.let { bottomNavigation ->
                    BottomNavigation(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) { bottomNavigation.BottomItems(rowScope = this) }
                }
            }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { NavigationContainer(appRouter) }

                bottomBarNavigation?.let {
                    Spacer(modifier = Modifier.height(56.dp))
                }
            }
        }
    }
}