package com.example.imdb.multiplatform.android

import androidx.multidex.MultiDexApplication
import com.example.imdb.multiplatform.di.androidModule
import com.example.imdb.multiplatform.onApplicationCreated
import org.koin.android.ext.koin.androidContext

class Application : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        onApplicationCreated {
            androidContext(applicationContext)
            modules(androidModule)
        }
    }
}