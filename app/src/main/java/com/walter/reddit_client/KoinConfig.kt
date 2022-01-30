package com.walter.reddit_client

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.walter.reddit_client.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

fun start(myApplication: Application) {
    startKoin { androidContext(myApplication) }
    injectModules()
}

fun injectModules() = loadKoinModules

private val loadKoinModules by lazy {
    loadKoinModules(
        listOf(
            networkModule
        )
    )
}