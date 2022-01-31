package com.example.imdb.multiplatform.ui.base

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.parcelable.Parcelable
import com.example.imdb.multiplatform.helpers.ViewModelStore

abstract class BaseDestination<T> : Parcelable {
    abstract fun createComposeFactory(
        componentContext: ComponentContext,
        viewModelStore: ViewModelStore
    ): @Composable () -> Unit

    override fun hashCode(): Int =
        this::class.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        return true
    }
}