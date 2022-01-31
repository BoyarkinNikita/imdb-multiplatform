package com.example.imdb.multiplatform.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

interface BottomNavigation {
    @Composable
    fun BottomItems(rowScope: RowScope)
}