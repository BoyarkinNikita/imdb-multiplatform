package com.example.imdb.multiplatform.android

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.arkivanov.decompose.defaultComponentContext
import com.example.imdb.multiplatform.onApplicationDestroyed
import com.example.imdb.multiplatform.ui.application.ApplicationEntry
import com.example.imdb.multiplatform.ui.application.exitApplicationFlow
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            exitApplicationFlow.collectLatest {
                onApplicationDestroyed()
                finishAndRemoveTask()
            }
        }

        setContent {
            ApplicationEntry(defaultComponentContext())
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        recreate()
    }
}