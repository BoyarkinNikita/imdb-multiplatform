package com.example.imdb.multiplatform.ui.bottom.news

import com.arkivanov.decompose.ComponentContext
import com.example.imdb.multiplatform.ui.base.BaseViewModel

class NewsViewModel(
    componentContext: ComponentContext,
    destination: NewsDestination
) : BaseViewModel<NewsDestination>(componentContext, destination)